package com.mvc.servlet.v1;



import com.mvc.annotion.CobraAutowired;
import com.mvc.annotion.CobraController;
import com.mvc.annotion.CobraRequestMapping;
import com.mvc.annotion.CobraService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * 1、配置web.ml
 * spring 手写dispatcher
 */
public class CobraDispatcherServlet extends HttpServlet
{
    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList();
    private Map<String,Object> ioc = new HashMap<String, Object>();// ioc容器

    //
    private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            doDispatcher(req,resp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resp.getWriter().println("500 Exception detail "+Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        // 1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2、解析并扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        // 3、实例化相关的类并保存到IOC容器中
        doInstance();

        // 4、依赖注入
        doAutowired();

        // 5、初始化handlerMapping
        initHandlerMapping();

        System.out.println("初始化完成......");



    }

    /**
     * handlerMapping 只找public方法
     * 扫描@Controller 中的requestMapping+方法中的requestMapping的url
     */
    private void initHandlerMapping()
    {
        if(ioc.isEmpty()){return;}

        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(CobraController.class)){continue;}// 只正对@Controller
            String baseUrl = "";
            // 获取Controller的url
            if(clazz.isAnnotationPresent(CobraRequestMapping.class)){
                CobraRequestMapping requestMapping = clazz.getAnnotation(CobraRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            // 获取的是public方法上的url配置
           Method[] methods = clazz.getMethods();
            for (Method method:methods){
                // 没有添加requestMapping注解的自动忽略
                if(!method.isAnnotationPresent(CobraRequestMapping.class)){continue;}

                CobraRequestMapping requestMapping =  method.getAnnotation(CobraRequestMapping.class);

                // / + /demo + / + /query
                String url = ("/"+baseUrl+"/"+requestMapping.value()).replaceAll("/+","/");// 多个/替换
                handlerMapping.put(url,method);

                System.out.println("Mapped "+url+","+method);

            }

        }


    }

    /**
     * 依赖注入
     */
    private void doAutowired()
    {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String,Object> entry:ioc.entrySet())
        {
            // 拿到对象中的所有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field:fields){
                // 对注解判断
                if(!field.isAnnotationPresent(CobraAutowired.class)){continue;}

                CobraAutowired autowired = field.getAnnotation(CobraAutowired.class);
                String beanName = autowired.value();// @CobraAutowired("bbb)中的bbb
                if("".equals(beanName.trim())){
                    beanName = field.getType().getName();
                }
                // private也能被外界访问
                field.setAccessible(true);// 设置私有属性发访问权限
                try
                {
                    // 执行注入动作
                    field.set(entry.getValue(),ioc.get(beanName));
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                    continue;
                }

            }


        }

        System.out.println("======容器");
        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            System.out.print("key="+entry.getKey()+",value="+entry.getValue());
        }
        System.out.println("======容器");

    }

    /**
     * 扫描出包下的类 包名+文件名
     * @param scanPackage
     */
    private void doScanner(String scanPackage)
    {
        // 找classpath下的 .class文件 去掉后缀.class即为类名称
        URL url = this.getClass()
                        .getClassLoader()
                        .getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classpath = new File(url.getFile());
        for (File file:classpath.listFiles())
        {
            // 文件夹
            if(file.isDirectory())
            {
                doScanner(scanPackage+"."+file.getName());
            }else {
                // 找.class结尾的文件
                if(!file.getName().endsWith(".class")){continue;}
                // 类的全名
                String className = (scanPackage+"."+file.getName().replace(".class",""));

                classNames.add(className);
            }

        }

    }

    /**
     * 实例化ioc容器中的类
     */
    private void doInstance()
    {
        if(classNames.isEmpty()){return;}
        try
        {
            for (String className:classNames){
                // 反射加载
                Class<?> clazz = Class.forName(className);
                // 注解
                if(clazz.isAnnotationPresent(CobraController.class)){
                   // 初始化 只初始化带有注解的
                    Object instance = clazz.newInstance();
                    String beanName = toLowerFirstClass(clazz.getSimpleName());
                    ioc.put(beanName,instance);

                }else if(clazz.isAnnotationPresent(CobraService.class)){
                    Object instance = clazz.newInstance();
                    // 1、 默认类名的首字母小写
                    String beanName = toLowerFirstClass(clazz.getSimpleName());

                    // 2、自定义命名的注解 @Service("aa")
                    CobraService service = clazz.getAnnotation(CobraService.class);
                    if(!"".equals(service.value())){

                        beanName = service.value();// 默认首字母小写
                    }
                    ioc.put(beanName,instance);

                    // 3、根据类型自动注入实现类 (spring不是这样写)
                    for (Class<?>i:clazz.getInterfaces()){
                        // 一个接口多个实现类 需要使用自定义命名的
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("the beanName exists");
                        }
                        ioc.put(i.getName(),instance);
                    }


                }else {
                    continue;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(ioc);
    }

    /**
     * 第一个位置移动32即可
     * param simpleName的首字母转小写
     * @return
     */
    private String toLowerFirstClass(String simpleName)
    {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    private void doLoadConfig(String contextConfigLocation)
    {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try
        {

            // 读取配置文件 通过init-param中的key(contextConfigLocation)取值
            contextConfig.load(fis);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            if(null != fis){try {fis.close();}catch (IOException e) {e.printStackTrace();}}
        }
    }


    /**
     * 运行阶段
     * 封装doDispatcher
     * @param request
     * @param response
     */
    private void doDispatcher(HttpServletRequest request,HttpServletResponse response)throws Exception

    {
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");


        if(!handlerMapping.containsKey(url)){
            response.getWriter().println("404 Not Found");
            return;
        }
        Method method = handlerMapping.get(url);

        // 第一个参数 方法所在的实例
        // 第二个参数 调用时所需要的实参
        Map<String,String[]> params = request.getParameterMap();

        // 投机方式 spring不是这样写
        String beanName = toLowerFirstClass(method.getDeclaringClass().getSimpleName());

        // requestMapping 传递的是name 这里可能做类型转换
        method.invoke(ioc.get(beanName),new Object[]{request,response,params.get("name")[0]});

    }



}

package com.cobra.auto.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.Set;

/**
 * 自动配置类 是容器启动时 会启动SCI接口
 * servlet 规范 装在的类 WEB-INF/classes 或 WEB-INF/lib 下
 * 在使用插件打包时需要建立 webapp/WEb-INF/web.xml路径 web.xml 可以防空
 *
 * SCI接口
 * 1、{@link javax.servlet.ServletContainerInitializer#onStartup(Set, ServletContext)}
 * 当容器启动时，首先进入本接口
 * 2、{@link javax.servlet.ServletContextListener#contextInitialized(ServletContextEvent)}
 * 当ServletContext初始化时
 *
 *
 * @see org.springframework.web.SpringServletContainerInitializer#onStartup(Set, ServletContext)
 * set<Class<?> 默认扫描 WEB-INF/classes 和 WEB-INF/lib下的类 即classpath下的类
 * # @HandlesTypes(WebApplicationInitializer.class) // HandlesTypes 选择关心的类以及子类
 * @see org.springframework.web.WebApplicationInitializer 的子类包括但不限于
 *    @see org.springframework.web.context.AbstractContextLoaderInitializer
 *      @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer
 *          @see AbstractAnnotationConfigDispatcherServletInitializer
 *
 *     tip:
 *      1) SpringServletContainerInitializer实现SCI接口
 *      2) 注解 @HandlesTypes(WebApplicationInitializer.class)表示只扫描WebApplicationInitializer及其子类
 *          加载时 子类不能是接口、抽象类
 *
 */
public class CobraAutoDispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer
{
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[0];
    }

    /**
     * 读取配置文件 标注 @Configuration的类 注册bean
     * @return
     */
    protected Class<?>[] getServletConfigClasses()
    {

        return new Class[]{SpringWebMvcAutoConfiguration.class,SpringWebMvcConditionConfiguration.class};
    }

    /**
     * 映射的路径 url_pattern
     * @return
     */
    protected String[] getServletMappings()
    {
        return new String[]{"/*"};// 映射所有的路径
    }
}

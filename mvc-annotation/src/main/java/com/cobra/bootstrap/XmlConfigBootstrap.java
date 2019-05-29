package com.cobra.bootstrap;

import com.cobra.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml配置引导程序
 * META-INF 源信息 约定的标准 读取xml里面的数据 springApplication上下文数据
 */
public class XmlConfigBootstrap
{
    public static void main(String[] args)
    {
        // 构建xml版本的springApplication的上下文
        ClassPathXmlApplicationContext applicationContext =
                        new ClassPathXmlApplicationContext();
        // 注册一个配置文件
        applicationContext.setConfigLocation("classpath:META-INF/spring/content.xml");
        applicationContext.refresh();// 启动方法

        User user = applicationContext.getBean("user",User.class);

        System.out.printf("user.getName=%s\n",user.getName());// 格式化打印


    }
}

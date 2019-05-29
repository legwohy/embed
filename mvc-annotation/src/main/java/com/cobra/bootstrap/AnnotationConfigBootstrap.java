package com.cobra.bootstrap;

import com.cobra.auto.config.UserConfiguration;
import com.cobra.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解配置
 */
public class AnnotationConfigBootstrap
{
    public static void main(String[] args)
    {
        // 构建注解springApplication的上下文
        AnnotationConfigApplicationContext applicationContext =
                        new AnnotationConfigApplicationContext();

        // 注册bean对象
        applicationContext.register(UserConfiguration.class);

        applicationContext.refresh();// 启动刷新类 加载

        User user = applicationContext.getBean("user",User.class);

        System.out.printf("user.getName=%s\n",user.getName());// 格式化打印


    }
}

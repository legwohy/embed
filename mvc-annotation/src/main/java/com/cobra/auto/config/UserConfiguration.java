package com.cobra.auto.config;

import com.cobra.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User的配置Bean 注解的写法
 * 与xml的配置其实差不多
 */
@Configuration// 标注为配置
public class UserConfiguration
{
    @Bean(name = "user")
    public User user(){
        User user = new User();
        user.setName("rose");
        return user;
    }
}

package com.cobra.auto.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring web mvc 配置bean
 */
@Configuration// spring 3.0以后的注解
@ComponentScan(basePackages = "com.cobra.auto")
public class SpringWebMvcAutoConfiguration
{
}

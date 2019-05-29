package com.cobra.auto.config;

import com.cobra.auto.annotation.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 条件装配必须依赖全局的 不能单独流出来 不像SCI,因为SCI接口可以嵌入容器，
 *  而条件装配不能直接嵌入
 *
 *  条件装配适用该类是可选非必须的情况
 */
@Configuration
@ComponentScan(basePackages = "com.cobra.auto")
public class SpringWebMvcConditionConfiguration
{
    /**
     * 只有当这个 Bean helloWorld 存在时 装在进来
     * @return
     */
    @ConditionalOnClass(String.class)
    @Bean("helloWorld")
    public String helloWorld(){
        return "helloWorld";
    }
}

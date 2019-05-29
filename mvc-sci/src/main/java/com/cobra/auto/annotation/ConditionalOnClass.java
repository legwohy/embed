package com.cobra.auto.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 当类存在时候装配
 */
@Documented
@Target(ElementType.METHOD)// 方法上
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnClassCondition.class)// 只有当类OnClassCondition存在时装配 否则不装配
public @interface ConditionalOnClass
{
    Class<?>[] value();
}

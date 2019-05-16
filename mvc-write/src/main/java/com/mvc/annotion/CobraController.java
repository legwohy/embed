package com.mvc.annotion;


import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Documented
@Target({ ElementType.TYPE}) // 修饰类
@Retention(RetentionPolicy.RUNTIME)
public @interface CobraController
{
    String value() default "";
}

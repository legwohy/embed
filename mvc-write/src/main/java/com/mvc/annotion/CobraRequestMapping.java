package com.mvc.annotion;


import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CobraRequestMapping
{
    String value() default "";
}

package com.mvc.annotion;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CobraService
{
    String value() default "";
}

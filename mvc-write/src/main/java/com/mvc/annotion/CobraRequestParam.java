package com.mvc.annotion;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CobraRequestParam
{
    String value() default "";
}

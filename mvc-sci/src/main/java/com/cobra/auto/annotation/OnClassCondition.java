package com.cobra.auto.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * 条件装配
 * 需要是实现 {@link org.springframework.context.annotation.Condition#matches(ConditionContext, AnnotatedTypeMetadata)}接口
 * 当指定的某个类存在时，满足条件 选择装配
 * @since 4.0 spring4.0提供的
 */
public class OnClassCondition implements Condition
{
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
    {
        boolean matches = false;
        // 取出 ConditionalOnClass 中的属性和方法
        MultiValueMap<String, Object>  attributes =
                        metadata.getAllAnnotationAttributes(ConditionalOnClass.class.getName());

        List<Object> classes = attributes.get("value");
        try
        {
            for (Object cls:classes){
                Class<?>[] type = (Class<?>[])cls;// 如果异常 则class不存在
                matches = true;
            }

        }catch (Throwable error){
            matches = false;
        }

        System.out.println("OnClassCondition 是否匹配："+matches);
        return matches;
    }
}

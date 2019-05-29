package com.cobra.auto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 条件装配
 */
@RestController
public class DemoConditionController
{
    /** 有可能helloWorld的bean取不到*/
    @Autowired(required = false)
    @Qualifier("helloWorld")
    private String helloWorld;

    /**
     * 有的话 返回 helloWorld 否则返回 null
     * @return
     */
    @GetMapping("hello")
    public String index(){
        return helloWorld;
    }
}

package com.cobra.auto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
    @GetMapping("/index")
    public String index(){
        return "Spring web MVC 自动装配";
    }
}

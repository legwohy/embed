package com.mvc.service;

import com.mvc.annotion.CobraService;

/**
 * 核心业务逻辑
 */
@CobraService
public class DemoServiceImpl implements IDemoService
{
    @Override
    public String get(String name)
    {
        return "my name is"+name;
    }
}

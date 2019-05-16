package com.mvc.action;


import com.mvc.annotion.CobraAutowired;
import com.mvc.annotion.CobraController;
import com.mvc.annotion.CobraRequestMapping;
import com.mvc.annotion.CobraRequestParam;
import com.mvc.service.IDemoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CobraController
@CobraRequestMapping("/demo")
public class DemoAction
{
    @CobraAutowired
    private IDemoService demoService;// 声明接口

    @CobraRequestMapping("/query")
    public void query(HttpServletRequest request,HttpServletResponse response, @CobraRequestParam("name") String name){
        if(null == name){
            name = "fly";
        }
        String result = "My name is"+demoService.get(name);

        try
        {
            response.getWriter().print(result);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @CobraRequestMapping("/add")
    public void add(HttpServletRequest request,HttpServletResponse response){

        try
        {
            response.getWriter().print("add");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @CobraRequestMapping("/test")
    public void test(HttpServletRequest request,HttpServletResponse response){

        try
        {
            response.getWriter().print("test");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }



}

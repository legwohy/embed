package com.cobra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jetty8以下是不支持注解的servlet3.0 因此是能在web.xml中配置
 * web.xml是不能省的 里面直接配置 servlet（DispatcherServlet 是jetty实现）
 * mvn jetty:run 即可运行
 */
public class DemoServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.getWriter().print("hello");
    }

}

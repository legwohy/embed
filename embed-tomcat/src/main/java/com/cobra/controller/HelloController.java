package com.cobra.controller;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 本实例 嵌入式tomcat 注意以下问题
 * servlet 3.0+tomcat7
 * mvn -Dmaven.test.skip -U clean package （有两个包 一个jar包 一个 war包 只能启动jar包 java -jar 启动jar包即可）
 * mvn tomcat7:run 运行失败 具体原因未知
 */
@WebServlet("/demo")
public class HelloController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.getWriter().print("hello");
    }

}

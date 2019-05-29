package com.myTomcat.servlet;


import com.myTomcat.param.HttpRequest;
import com.myTomcat.param.HttpResponse;

import java.io.IOException;

/**
 * 登陆请求
 */
public class RegisterServlet implements HttpServlet
{
    @Override
    public void server(HttpRequest request, HttpResponse response) throws IOException
    {

        response.writeFile("htmlfile/registerSuccess.html");

    }
}

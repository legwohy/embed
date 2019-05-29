package com.myTomcat.servlet;


import com.myTomcat.param.HttpRequest;
import com.myTomcat.param.HttpResponse;

import java.io.IOException;

/**
 * 所有servlet的根类
 */
public interface HttpServlet
{
    void server(HttpRequest request, HttpResponse response)throws IOException;
}

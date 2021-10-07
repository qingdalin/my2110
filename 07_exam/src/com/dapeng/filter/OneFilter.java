package com.dapeng.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        /*
        以下代码有bug，任何账户都不能登录进去
        //向下转向
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession(false);
        //判断是否是合法用户
        if (session == null){
            request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);
            return;
        }
        //合法用户，放行
        filterChain.doFilter(servletRequest,servletResponse);*/
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = null;
        //1、调用请求对象，读取请求包的请求行的URI，了解用户访问的文件是谁
        String uri = request.getRequestURI();
        //2、判断uri是否含有login有关的字符，，，如果有，应放行
        if (uri.indexOf("login")!= -1 || "/myWeb/".equals(uri)){//代表访问默认页面也放行
            //以上命令指的是，login第一次出现在uri的下标，如果不包含，则返回-1，如果包含返回下标，进行放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3、如果访问其他文件，需要得到服务端的HttpSession
        session = request.getSession(false);
        if (session != null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //不合法用户
        request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);
    }
}

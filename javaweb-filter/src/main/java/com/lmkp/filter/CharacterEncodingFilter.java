package com.lmkp.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    // 初始化
    // web服务器启动就初始化了，随时等待过滤对象出现
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=UTF-8");

        System.out.println("执行前");
        filterChain.doFilter(servletRequest,servletResponse); // 让我们的请求继续走，如果不写，程序到这里就被拦截停止
        System.out.println("执行后");
    }

    // 销毁
    // web服务器关闭，过滤器销毁
    public void destroy() {
        System.out.println("销毁");
    }
}

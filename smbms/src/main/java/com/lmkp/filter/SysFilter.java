package com.lmkp.filter;

import com.lmkp.pojo.User;
import com.lmkp.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 过滤器，从session中获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if (user==null){// 已经被移除或者未登录
            response.sendRedirect("/smbms/error.jsp");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}

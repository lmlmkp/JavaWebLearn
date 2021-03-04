package com.lmkp.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetServlet extends HttpServlet {
    @Override
    /*
    共享数据
     */
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext context = this.getServletContext();
//        String username = (String) context.getAttribute("username");
//
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("utf-8");
//        resp.getWriter().print("名字"+username);

//    }

    /*
    获取初始化参数
     */
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext context = this.getServletContext();
//        String url = context.getInitParameter("url");
//        resp.getWriter().print(url);
//    }

    /*
    请求转发
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        System.out.println("进入了ServletDemo04");
        //RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp"); //转发的请求路径
        //requestDispatcher.forward(req,resp); //调用forward实现请求转发；
        context.getRequestDispatcher("/").forward(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

package com.lmkp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;


// 传递中文
public class CookiesDemo03 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-16");

        PrintWriter out = resp.getWriter();
        // cookie服务端从客户端获取
        Cookie[] cookies = req.getCookies();
        // 判断cookie是否存在
        if (cookies!=null){
            out.write("你上一次的访问时间是 ：");
            // 如果存在，取出数据
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 获取cookie名字
                if (cookie.getName().equals("name")) {
                    // 编码
                    out.write(URLDecoder.decode(cookie.getValue(),"UTF-8"));
                }
            }
        }else {
            out.write("这是您第一次访问本站");
        }

        // 解码
        Cookie cookie = new Cookie("name", URLEncoder.encode("传递中文","UTF-8"));

        resp.addCookie(cookie);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

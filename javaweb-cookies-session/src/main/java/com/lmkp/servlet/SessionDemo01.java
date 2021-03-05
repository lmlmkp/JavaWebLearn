package com.lmkp.servlet;

import com.lmkp.pojo.Person;
import sun.util.resources.cldr.id.CurrencyNames_id;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 得到Session
        HttpSession session = req.getSession();

        // 给session中存数据
        session.setAttribute("name",new Person("小明",23));

        // 获取session的ID
        String id = session.getId();

        // 判断session是不是新的
        if (session.isNew()){
            resp.getWriter().write("session创建成功,ID为:"+ id);
        }else {
            resp.getWriter().write("session已经存在,id为:"+ id);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

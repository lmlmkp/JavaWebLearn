package com.lmkp.servlet.user;

import com.lmkp.pojo.User;
import com.lmkp.service.user.UserService;
import com.lmkp.service.user.UserServiceImpl;
import com.lmkp.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    // 调用业务层代码


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet 进入....");

        // 获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        // 和数据库中的原始密码进行对比，调用业务层
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);

        if (user!=null&&userCode.equals(user.getUserCode())&&userPassword.equals(user.getUserPassword())){
            // 将用户信息放到session中
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            // 跳转到主页
            resp.sendRedirect("jsp/frame.jsp");
        }else { //查无此人
            // 转发回登录页面，顺带提示用户名或者密码错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

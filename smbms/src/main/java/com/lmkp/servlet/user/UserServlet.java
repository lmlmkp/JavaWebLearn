package com.lmkp.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.lmkp.pojo.Role;
import com.lmkp.pojo.User;
import com.lmkp.service.role.RoleServiceImpl;
import com.lmkp.service.user.UserService;
import com.lmkp.service.user.UserServiceImpl;
import com.lmkp.util.Constants;
import com.lmkp.util.PageSupport;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

// 实现servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method.equals("savepwd")&&method!=null){
            updatePwd(req,resp);
        }else if (method.equals("pwdmodify")&&method!=null){
            pwdmodify(req,resp);
        }else if (method.equals("query")&&method!=null){
            query(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 查询用户列表

        // 从前端获取数据
        String queryname = req.getParameter("queryUserName");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        // 获取用户列表
        UserService userService = new UserServiceImpl();

        // 第一次走是第一页，页面大小固定
        int pageSize = 5; // 可以写到配置文件里，方便后期修改
        int currentPageNo = 1;


        if (queryname == null) {
            queryname = "";
        }
        if (temp != null && !temp.equals("")) {
            queryUserRole = Integer.parseInt(temp);
        }
        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);
        }

        // 获取用户总数(分页：上一页，下一页)
        int totalCount = userService.getUserCount(queryname, queryUserRole);
        // 总页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        //int totalPageCount = (int)(totalCount/pageSize)+1;
        int totalPageCount = pageSupport.getTotalPageCount();

        // 控制首页和尾页
        // 如果页面要小于1了，就显示第一页的东西
        if (totalPageCount<1){
            currentPageNo = 1;
        }else if (currentPageNo>totalPageCount){
            currentPageNo = totalPageCount;

        }

        // 获取用户列表展示
        List<User> userList = userService.getUserList(queryname, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList",userList);

        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList",roleList);

        req.setAttribute("totalCount",totalCount);  // 用户总数
        req.setAttribute("currentPageNo",currentPageNo); // 当前页码
        req.setAttribute("totalPageCount",totalPageCount); // 总页数
        req.setAttribute("queryUserName",queryname);
        req.setAttribute("queryUserRole",queryUserRole);
        // 返回前端
        req.getRequestDispatcher("userlist.jsp").forward(req,resp);

    }
    // 修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从session获取id
        Object user = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        boolean flag = false;

        if (user!=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) user).getId(), newpassword);
            if (flag){
                req.setAttribute("message","修改密码成功，请退出使用新密码登录");
                // 密码修改成功，移除当前Session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message","密码修改失败");
            }
        }else {
            req.setAttribute("message","新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    // 验证旧密码
    // TODO 只有旧密码验证成功才能提交更改，此处前台控制旧密码成功发起请求，但直接访问后台不用旧密码也可以
    public void pwdmodify(HttpServletRequest req, HttpServletResponse resp){
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        // 万能map
        HashMap<String, String> resultMap = new HashMap<String, String>();

        if (o==null){// session失效，session过期
             resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){
             resultMap.put("result","error");
        }else {
            String userPassword = ((User) o).getUserPassword();
            if(oldpassword.equals(userPassword)){
                 resultMap.put("result","true");
            }else {
                 resultMap.put("result","false");
            }
        }
        PrintWriter printWriter=null;
        try {
            //设置响应的类型
            resp.setContentType("application/json");
            printWriter=resp.getWriter();
            // toJSONString 阿里巴巴工具类
            printWriter.write(JSONArray.toJSONString( resultMap));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printWriter.flush();
            printWriter.close();
        }
    }
}

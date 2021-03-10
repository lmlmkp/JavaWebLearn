package com.lmkp.dao.user;

import com.lmkp.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    // 得到要登录的用户
    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    // 修改当前用户密码
    public int updatePwd(Connection connection,int id, String password) throws SQLException;

    // 根据用户名和角色查询用户数量
    public int getUserCount(Connection connection,String username, int userRole)throws SQLException;

    //获取用户列表
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPage, int pageSize);

}

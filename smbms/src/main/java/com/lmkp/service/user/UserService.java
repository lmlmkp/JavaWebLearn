package com.lmkp.service.user;

import com.lmkp.pojo.Role;
import com.lmkp.pojo.User;

import java.util.List;

public interface UserService {
    // 用户登录
    public User login(String userCode, String password);

    // 根据用户id修改密码
    public boolean updatePwd(int id, String password);

    // 根据用户名和角色获取用户数量
    public int getUserCount(String username, int userRole);

    // 获取用户列表
    public List<User> getUserList(String username, int userRole, int currentPage, int pageSize);


}

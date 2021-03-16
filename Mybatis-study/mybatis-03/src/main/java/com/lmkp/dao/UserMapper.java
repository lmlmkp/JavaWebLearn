package com.lmkp.dao;

import com.lmkp.pojo.RsUser;
import com.lmkp.pojo.User;

import java.util.List;

public interface UserMapper {

    // 查询全部用户
    List<User> getUserList();

    // 根据id查询用户
    RsUser getUserByID(int id);

    // insert一个用户
    int addUser(User user);

    // 修改用户
    int updateUser(User user);

    // 删除一个用户
    int deleteUser(int id);
}

package com.lmkp.service.user;

import com.lmkp.dao.BaseDao;
import com.lmkp.dao.user.UserDao;
import com.lmkp.dao.user.UserDaoImpl;
import com.lmkp.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


public class UserServiceImpl implements UserService{

    // 调用DAO层
    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }


    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection,userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    public boolean updatePwd( int id, String password) {
        Connection connection = null;
        Boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection,id,password)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return flag;
    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin","1");
        System.out.println(admin.getUserPassword());
    }
}

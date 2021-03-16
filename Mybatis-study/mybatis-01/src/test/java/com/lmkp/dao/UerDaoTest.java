package com.lmkp.dao;

import com.lmkp.pojo.User;
import com.lmkp.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UerDaoTest {


    @Test
    public void test(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();;

        try {
            // 方式一：getMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }

            // 方式二:
            List<User> list = sqlSession.selectList("com.lmkp.dao.UserMapper.getUserList");
            for (User user : list) {
                System.out.println(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭qlSession
            sqlSession.close();
        }

    }


    @Test
    public void getUserLike(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 方法一 拼接字符串 %value%
        List<User> list = mapper.getUserLike("%a%");
        // 方法二 select * from mybatis.user where name like "%"#{value}"%"


        for (User user : list) {
            System.out.println(user);
        }

        sqlSession.close();
    }


    @Test
    public void getUserById(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserByID(1);
        System.out.println(user);

        sqlSession.close();
    }
    @Test
    public void getUser(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",8);
        map.put("name","张三");
        User user = mapper.getUser(map);
        System.out.println(user);

        sqlSession.close();
    }




    // 增删改需要提交事务
    @Test
    public void addUser(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(8, "add", "123123");
        int i = mapper.addUser(user);
        System.out.println(i);

        // 提交事务
        sqlSession.commit();


        sqlSession.close();
    }

    @Test
    public void addUser2() {
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",8);
        map.put("name","张三");
        map.put("pwd","ccc");
        int i = mapper.addUser2(map);
        System.out.println(i);

        // 提交事务
        sqlSession.commit();

    }






    @Test
    public void updateUser(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.updateUser(new User(6, "哈哈哈", "123123"));
        System.out.println(i);

        // 提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUser(8);

        // 提交事务
        sqlSession.commit();

        sqlSession.close();
    }
}

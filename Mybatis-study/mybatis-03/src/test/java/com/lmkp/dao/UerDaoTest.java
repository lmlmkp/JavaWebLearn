package com.lmkp.dao;

import com.lmkp.pojo.RsUser;
import com.lmkp.pojo.User;
import com.lmkp.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


public class UerDaoTest {




    @Test
    public void getUserById(){
        // 第一步，获取 SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        RsUser user = mapper.getUserByID(1);
        System.out.println(user);

        sqlSession.close();
    }


}

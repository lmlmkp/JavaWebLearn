package com.lmkp.service.role;

import com.lmkp.dao.BaseDao;
import com.lmkp.dao.role.RoleDao;
import com.lmkp.dao.role.RoleDaoImpl;
import com.lmkp.pojo.Role;

import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class RoleServiceImpl implements RoleService{
    // 调用DAO层
    private RoleDao roleDao;

    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }
    @Override
    public List<Role> getRoleList() {
        Connection connection=null;
        List<Role> list=null;
        connection= BaseDao.getConnection();
        list=roleDao.getRoleList(connection);
        BaseDao.closeResource(connection,null,null);
        return list;
    }

    @Test
    public void test(){
        RoleService roleService = new RoleServiceImpl();
        List<Role> list = roleService.getRoleList();
        System.out.println(list.get(0).getRoleName());
    }
}

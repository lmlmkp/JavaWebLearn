package com.lmkp.dao.role;

import com.lmkp.dao.BaseDao;
import com.lmkp.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    @Override
    public List<Role> getRoleList(Connection connection) {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Role role;
        List<Role> roleList=new ArrayList<Role>();
        String sql="select *from smbms_role";
        Object[] o={};
        if(connection!=null){
            try {
                resultSet= BaseDao.execute(connection,preparedStatement,sql,o,resultSet);
                while(resultSet.next()){
                    role=new Role();
                    role.setId(resultSet.getInt("id"));
                    role.setRoleCode(resultSet.getString("roleCode"));
                    role.setRoleName(resultSet.getString("roleName"));
                    roleList.add(role);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                BaseDao.closeResource(null,preparedStatement,resultSet);
            }
        }
        return roleList;
    }
}

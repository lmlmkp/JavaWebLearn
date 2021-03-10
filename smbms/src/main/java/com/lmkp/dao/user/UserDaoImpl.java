package com.lmkp.dao.user;

import com.lmkp.dao.BaseDao;
import com.lmkp.pojo.Role;
import com.lmkp.pojo.User;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if(null != connection){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            rs = BaseDao.execute(connection, pstm, sql, params, rs);
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return user;
    }

    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        int row=0;
        PreparedStatement pstm = null;
        if (connection!=null){
            String sql="update smbms_user set userPassword=? where id=?";
            Object[] params={password,id};
            row=BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResource(null, pstm, null);
        }
        return row;
    }

    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        ArrayList<Object> list = new ArrayList<Object>();

        if (connection!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");

            if (!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.username like ?");
                list.add("%"+username+"%");
            }
            if (userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }

            // list转化为数组
            Object[] objects = list.toArray();

            System.out.println(sql.toString());

            rs = BaseDao.execute(connection, pstm, sql.toString(), objects, rs);

            if (rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPage, int pageSize) {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<User> listUser=new ArrayList<User>();
        StringBuffer sql=new StringBuffer();
        if(connection!=null){
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole=r.id");
            List<Object> list=new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.userName like ?");
                list.add("%"+username+"%");
            }
            if(userRole>0){
                sql.append(" and u.userRole = ? ");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            int startIndex=(currentPage-1)*pageSize;
            list.add(startIndex);
            list.add(pageSize);
            Object[] o=list.toArray();
            System.out.println("用户管理查询SQL:"+sql);
            User user;
            // 这次尝试使用try catch 而不王上抛
            try {
                resultSet=BaseDao.execute(connection,preparedStatement,sql.toString(),o,resultSet);
                while(resultSet.next()){
                    user=new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setGender(resultSet.getInt("gender"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setUserRole(resultSet.getInt("userRole"));
                    user.setUserRoleName(resultSet.getString("userRoleName"));
                    listUser.add(user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                BaseDao.closeResource(null,null,resultSet);
            }
        }

        return listUser;
    }

}

package com.lmkp.test;

import java.sql.*;

public class JdbcTest2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 配置信息
        // useUnicode=true&characterEncoding=utf-8 解决中文乱码问题
        String url = "jdbc:mysql://127.0.0.1:3306/ashen_db?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "lmkp";

        // 1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3.编写SQL
        String sql = "insert into USER(id,user,password) values(?,?,?)";
        // 4.预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,4); // 给第一个占位符赋值
        preparedStatement.setString(2,"cccc");
        preparedStatement.setString(3,"lmlmlmlm");

        // 5.执行SQL
        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("插入成功");
        }

        // 6.关闭连接，释放资源（一定要做）先开后关
        preparedStatement.close();
        connection.close();
    }
}

package com.lmkp.test;

import java.sql.*;

public class JdbcTest {
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
        // 3.向数据库发送SQL的对象Statement,PreparedStatement : CRUD
        Statement statement = connection.createStatement();
        // 4.编写SQL
        String sql = "select * from USER";
        // 5.执行SQL
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("id="+resultSet.getObject("id"));
            System.out.println("name="+resultSet.getObject("user"));
            System.out.println("password="+resultSet.getObject("password"));
        }

        // 6.关闭连接，释放资源（一定要做）先开后关
        statement.close();
        connection.close();
    }
}

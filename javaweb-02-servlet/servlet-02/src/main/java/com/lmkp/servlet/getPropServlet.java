package com.lmkp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class getPropServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        Properties

        - 在java目录下新建properties
        - 在resources目录下新建properties

        发现：都被打包到了同一个路径下：classes，我们俗称这个路径为classpath:
         */
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/aa.properties");  // 文件流

        java.util.Properties prop = new java.util.Properties();
        prop.load(is);
        String user = prop.getProperty("username");
        String pwd = prop.getProperty("password");

        resp.getWriter().print(user+":"+pwd);

    }
}

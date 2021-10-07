package com.dapeng.listener;

import com.dapeng.util.DBUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserAddListener implements ServletContextListener {
    //tomcat启动时刻，预先创建20个Connection，userDao方法执行时使用
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBUtil util = new DBUtil();
        Map map = new HashMap();
        for (int i = 0 ; i < 20; i++){
            try {
                Connection conn = util.getConnection();
                System.out.println("服务器启动创建的第" + (i+1) + "个连接对象");
                map.put(conn,true);//true表示当前的连接对象处于空闲状态，false处于占用使用状态。
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //为了在服务器运行期间，可以一直使用这20个连接对象，将他们保存在全局作用域对象
            ServletContext application = sce.getServletContext();
            application.setAttribute("key1",map);
        }
    }
    //http服务器关闭的时刻，将全局作用域对象的20个连接对象销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            Connection conn = (Connection)it.next();
            if (conn != null) {
                    //conn.close();
                    System.out.println("conn被摧毁");
            }
        }
    }
}

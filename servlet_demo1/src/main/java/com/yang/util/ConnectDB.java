package com.yang.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yz on 2017/7/4.
 */
public class ConnectDB {
    private String fileName = "/db.properties";
    private String driver = "";
    private String url = "";
    private String username ="";
    private String password = "";
    Connection conn=null;


    public  Connection  getConn(){
        Properties p = new Properties();
        try {
            InputStream in = ConnectDB.class.getResourceAsStream(fileName);//这里有人用new FileInputStream(fileName),不过这种方式找不到配置文件。有人说是在classes下，我调过了，不行。
            p.load(in);
            in.close();
            if(p.containsKey("driver")){
                this.driver = p.getProperty("driver");
            }
            if(p.containsKey("url")){
                this.url = p.getProperty("url");
            }
            if(p.containsKey("user")){
                this.username = p.getProperty("user");
            }
            if(p.containsKey("password")){
                this.password = p.getProperty("password");
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p.getProperty("driver"));
        try {
            Class.forName(this.driver);
            conn = DriverManager.getConnection(this.url,this.username,this.password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.print("获取连接异常");
        } catch (ClassNotFoundException ex) {
            System.out.print("加载驱动出错");
            ex.printStackTrace();;
        }
        return conn;
    }

    public static void main(String[] args) {
        new ConnectDB().getConn();
    }
}

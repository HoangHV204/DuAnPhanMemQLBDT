/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author hoang
 */
public class DBUtils {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        getConnection();
        System.out.println("Done!");
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        //1. Add driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        //2. Declare connection String (Chuỗi kết nối) và username
        String url = "jdbc:sqlserver://DESKTOP-RAT6EIL\\SQLEXPRESS:1433;databaseName=FPL_DaoTao1";
        String username = "sa";
        String password = "hoang2004";
        return DriverManager.getConnection(url, username, password);
    }
}

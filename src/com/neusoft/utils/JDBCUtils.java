package com.neusoft.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;

/**
 * @author liuboting
 * @date 2020/8/6 9:01
 * druid 连接池工具类
 */

public class JDBCUtils {
    // 定义成员变量DataSoure,可以切换不同的连接池
    private static DataSource ds;

    // 初始化配置
    static {
        try{
            // 1、加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            // 2.获取 定义成员变量DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 连接资源
    public  static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    // 释放资源
    public static void close(Statement statement, Connection connection, ResultSet resultSet){
        if(statement != null){
            try{
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement statement,Connection connection){
        close(statement,connection,null);
    }
    // 获取连接池方法
    public static DataSource getDataSource(){
        return ds;
    }
}

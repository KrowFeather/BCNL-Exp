package com.krowfeather.utils;

import com.krowfeather.config.ConfigUtil;

import java.sql.*;

/**
 * JDBC工具类，提供数据库连接的获取和资源关闭的功能
 * 该类采用静态方法设计，方便直接调用，无需创建实例
 */
public class JDBCUtil {
    /*
      静态代码块，在类加载时执行
      用于加载数据库驱动类
     */
    static {
        try {
            // 通过ConfigUtil获取数据库驱动类名并加载
            Class.forName(ConfigUtil.getConfig("mysql.driverClassName"));
        } catch (ClassNotFoundException e) {
            // 打印驱动加载失败的异常信息
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return Connection对象，数据库连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 通过DriverManager获取数据库连接
            // 连接信息（URL、用户名、密码）通过ConfigUtil从配置文件中获取
            connection = DriverManager.getConnection(
                    ConfigUtil.getConfig("mysql.url"),
                    ConfigUtil.getConfig("mysql.username"),
                    ConfigUtil.getConfig("mysql.password")
            );
        } catch (SQLException e) {
            // 打印获取连接失败的异常信息
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库资源
     * 按照ResultSet -> PreparedStatement -> Connection的顺序关闭资源
     * 每个资源关闭都单独捕获异常，确保一个资源关闭失败不影响其他资源的关闭
     * @param connection 数据库连接对象
     * @param preparedStatement 预处理语句对象
     * @param resultSet 结果集对象
     */
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        // 关闭ResultSet资源
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭PreparedStatement资源
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭Connection资源
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

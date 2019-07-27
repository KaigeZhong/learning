package com.jdbc;

import java.sql.*;

public class JDBCL {

    public static void main(String[] args) throws Exception {
        dualTest();
    }

    private static void dualTest() throws SQLException {
        //实现了java中的java.lang.AutoCloseable接口,都可以在try-with-resources结构中使用。都会自动关闭资源.自定义的类实现了AutoCloseable接口也可以
        //关闭顺序: resultSet, statement, connection
        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select 1 from dual")) {

            //将游标移到第一行前
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        }
    }

    private static void prepareStatementTest() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from city where country = ?")) {
            statement.setString(1, "US");
        }
    }

}
package com.example.xixi.juc;

/**
 * @author : xi-xi
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public ConnectionManager(String a) {
        this.a = a;
    }

    public ConnectionManager() {
    }

    private static final ThreadLocal<Connection> dbConnectionLocal = ThreadLocal.withInitial(() -> {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud_order?user=root&password=nianqin2846");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    });

    public Connection getConnection() {
        return dbConnectionLocal.get();
    }

    public void setConnection(Connection connection) {
        dbConnectionLocal.set(connection);
    }

    public Connection getConnection1() {
        return dbConnectionLocal.get();
    }

    @Override
    public String toString() {
        return "ConnectionManager{" +
                "a='" + a + '\'' +
                '}';
    }
}


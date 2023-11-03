package com.bmo.practica5.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

    public Connection connection() {
        try {
            ConfigDB config = new ConfigDB();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(config.url, config.usr, config.pass);
            return c;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}

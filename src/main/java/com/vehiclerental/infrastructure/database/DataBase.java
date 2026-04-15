package main.java.com.vehiclerental.infrastructure.database;

import main.java.com.vehiclerental.infrastructure.exception.DataBaseException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
    private static Connection conn;

    public static Connection getConnection(){
        if (conn == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }catch (SQLException e){
                throw new DataBaseException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    private static Properties loadProperties(){
        try(InputStream fs = DataBase.class.getClassLoader().getResourceAsStream("main/resources/db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}

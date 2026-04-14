package br.com.portifolio.vehiclerentalsys.db;

import br.com.portifolio.vehiclerentalsys.domain.exception.DataBaseException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
    private static Connection conn = null;

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
        try(InputStream fs = DataBase.class.getResourceAsStream("/br/com/portifolio/vehiclerentalsys/config/db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}

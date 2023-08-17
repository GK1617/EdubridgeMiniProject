package ebs.dbconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConfig {
    
    public static Connection getConnection() {
        
        Properties properties = new Properties();
        Connection connection = null;
        FileInputStream fileInputStream = null;

        try {
        	fileInputStream= new FileInputStream("ebs.properties");   
             properties.load(fileInputStream);
            Class.forName(properties.getProperty("jdbcdriver"));
            String url = properties.getProperty("jdbcurl");
            String uname = properties.getProperty("username");
            String pass = properties.getProperty("password");
            connection = DriverManager.getConnection(url, uname, pass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
}

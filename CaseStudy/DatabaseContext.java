package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseContext {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                
                String propPath = "C:\\Users\\kikir\\git\\Hexawaretraining\\Hexawaretraining\\CarConnect\\src\\dao\\mydb.properties";
    
                Properties props = DBPropertyUtil.loadProperties(propPath);
                String url = props.getProperty("kiki.url");
                String user = props.getProperty("kiki.username");
                String pass = props.getProperty("kiki.password");
                String driver = props.getProperty("kiki.driver");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("✅ Database connection established.");

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("❌ Failed to connect to DB.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}


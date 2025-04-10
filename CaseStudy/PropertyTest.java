package util;
import java.util.Properties;
import util.DBPropertyUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class PropertyTest {

	

	
	
	    public static void main(String[] args) {
	        try {
	            String driver = "com.mysql.cj.jdbc.Driver";
	            String url = "jdbc:mysql://localhost:3306/carconnect";
	            String username = "root";
	            String password = "Sandhiya2509";

	            // Load Driver
	            Class.forName(driver);

	            // Establish Connection
	            Connection conn = DriverManager.getConnection(url, username, password);
	            System.out.println("✅ Successfully connected to the database!");

	            conn.close(); // Good practice
	        } catch (ClassNotFoundException e) {
	            System.out.println("❌ JDBC Driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("❌ SQL Exception occurred.");
	            e.printStackTrace();
	        }
	    }
	}





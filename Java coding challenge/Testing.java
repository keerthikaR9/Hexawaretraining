// test/DBTest.java
package util;


import java.sql.Connection;

public class Testing {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnectionUtil.getConnection();
            if (conn != null) {
                System.out.println("✅ Connection successful!");
            }
        } catch (Exception e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}


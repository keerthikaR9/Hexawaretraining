package dao.impl;

import dao.IAdminDAO;
import entity.Admin;
import exception.AdminNotFoundException;
import exception.AuthenticationException;
import exception.DatabaseConnectionException;
import util.DatabaseContext;

import java.sql.*;

public class AdminDAOImpl implements IAdminDAO {

    private Connection conn;

    public AdminDAOImpl() {
        this.conn = DatabaseContext.getConnection();
        if (this.conn == null) {
            throw new DatabaseConnectionException("Failed to establish a database connection.");
        }
    }



    @Override
    public Admin getAdminById(int adminId) throws AdminNotFoundException {
        String query = "SELECT * FROM Admins WHERE AdminID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAdminFromResultSet(rs);
            } else {
                throw new AdminNotFoundException("Admin not found with ID: " + adminId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdminNotFoundException("Database error while retrieving admin with ID: " + adminId);
        }
        
         
    }

    @Override
    public Admin getAdminByUsername(String username) throws AdminNotFoundException {
        String query = "SELECT * FROM Admins WHERE Username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAdminFromResultSet(rs);
            } else {
                throw new AdminNotFoundException("Admin not found with username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdminNotFoundException("Database error while retrieving admin with username: " + username);
        }
    }

    @Override
    public boolean registerAdmin(Admin admin) {
        String query = "INSERT INTO Admins (FirstName, LastName, Email, PhoneNumber, Username, Password, Role, JoinDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPhoneNumber());
            stmt.setString(5, admin.getUsername());
            stmt.setString(6, admin.getPassword());
            stmt.setString(7, admin.getRole());
            stmt.setDate(8, new java.sql.Date(admin.getJoinDate().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while registering admin: " + e.getMessage());
        }      
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        String query = "UPDATE Admins SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Username = ?, Password = ?, Role = ? WHERE AdminID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPhoneNumber());
            stmt.setString(5, admin.getUsername());
            stmt.setString(6, admin.getPassword());
            stmt.setString(7, admin.getRole());
            stmt.setInt(8, admin.getAdminID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while updating admin: " + e.getMessage());
        }
        
    }

    @Override
    public boolean deleteAdmin(int adminId) {
        String query = "DELETE FROM Admins WHERE AdminID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while deleting admin: " + e.getMessage());
        }
        
    }

    // Utility method to convert ResultSet into Admin object
    private Admin extractAdminFromResultSet(ResultSet rs) throws SQLException {
        return new Admin(
            rs.getInt("AdminID"),
            rs.getString("FirstName"),
            rs.getString("LastName"),
            rs.getString("Email"),
            rs.getString("PhoneNumber"),
            rs.getString("Username"),
            rs.getString("Password"),
            rs.getString("Role"),
            rs.getDate("JoinDate")
        );
    }

}

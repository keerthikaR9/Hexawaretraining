package dao.impl;

import dao.IVehicleDAO;
import entity.Vehicle;
import exception.VehicleNotFoundException;
import util.DatabaseContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements IVehicleDAO {

 private Connection conn;

  public VehicleDAOImpl() {
		        conn = DatabaseContext.getConnection();
		    }

		    @Override
		    public Vehicle getVehicleById(int vehicleId) throws VehicleNotFoundException {
		        String query = "SELECT * FROM Vehicle WHERE VehicleID = ?";
		        try (PreparedStatement stmt = conn.prepareStatement(query)) {
		            stmt.setInt(1, vehicleId);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		                return extractVehicleFromResultSet(rs);
		            }
		            else {
		                throw new VehicleNotFoundException("Vehicle not found with ID: " + vehicleId);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		            throw new VehicleNotFoundException("Error retrieving vehicle with ID: " + vehicleId);
		        }
		       
		    }

		    @Override
		    public List<Vehicle> getAvailableVehicles() {
		        List<Vehicle> vehicles = new ArrayList<>();
		        String query = "SELECT * FROM Vehicle WHERE Availability = 'Available'";
		        try (Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery(query)) {
		            while (rs.next()) {
		                vehicles.add(extractVehicleFromResultSet(rs));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return vehicles;
		    }

		    @Override
		    public boolean addVehicle(Vehicle vehicle) {
		        String query = "INSERT INTO Vehicle (Make, Model, Year, Color, RegistrationNumber, Type, Availability, DailyRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		        try (PreparedStatement stmt = conn.prepareStatement(query)) {
		            stmt.setString(1, vehicle.getMake());
		            stmt.setString(2, vehicle.getModel());
		            stmt.setInt(3, vehicle.getYear());
		            stmt.setString(4, vehicle.getColor());
		            stmt.setString(5, vehicle.getRegistrationNumber());
		            stmt.setString(6, vehicle.getType());
		            stmt.setBoolean(7, vehicle.isAvailability());
		            stmt.setDouble(8, vehicle.getDailyRate());
		            return stmt.executeUpdate() > 0;
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return false;
		    }


		    @Override
		    public boolean updateVehicle(Vehicle vehicle) {
		        String query = "UPDATE Vehicle SET Make = ?, Model = ?, Year = ?, Type = ?, Availability = ?, DailyRate = ? WHERE VehicleID = ?";
		        try (PreparedStatement stmt = conn.prepareStatement(query)) {
		            stmt.setString(1, vehicle.getMake());
		            stmt.setString(2, vehicle.getModel());
		            stmt.setInt(3, vehicle.getYear());
		            stmt.setString(4, vehicle.getType());
		            stmt.setBoolean(5, vehicle.isAvailability()); 
		            stmt.setDouble(6, vehicle.getDailyRate());
		            stmt.setInt(7, vehicle.getVehicleID()); 
		            return stmt.executeUpdate() > 0;
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return false;
		    }


		    @Override
		    public boolean removeVehicle(int vehicleId) {
		        String query = "DELETE FROM Vehicle WHERE VehicleID = ?";
		        try (PreparedStatement stmt = conn.prepareStatement(query)) {
		            stmt.setInt(1, vehicleId);
		            return stmt.executeUpdate() > 0;
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return false;
		    }
		    @Override
		    public List<Vehicle> getAllVehicles() {
		        List<Vehicle> vehicles = new ArrayList<>();
		        String query = "SELECT * FROM Vehicle";
		        try (Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery(query)) {
		            while (rs.next()) {
		                vehicles.add(extractVehicleFromResultSet(rs));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return vehicles;
		    }


		    
		    private Vehicle extractVehicleFromResultSet(ResultSet rs) throws SQLException {
		        return new Vehicle(
		            rs.getInt("VehicleID"),
		            rs.getString("Make"),
		            rs.getString("Model"),
		            rs.getInt("Year"),
		            rs.getString("Color"),
		            rs.getString("RegistrationNumber"),
		            rs.getString("Type"),              
		            rs.getBoolean("Availability"),
		            rs.getDouble("DailyRate")
		        );
		    }



		}
	    
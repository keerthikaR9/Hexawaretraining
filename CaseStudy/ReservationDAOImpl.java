package dao.impl;

import dao.IReservationDAO;
import entity.Reservation;
import exception.InvalidInputException;
import exception.ReservationException;
import util.DatabaseContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements IReservationDAO {

    private Connection conn;

    public ReservationDAOImpl() {
        conn = DatabaseContext.getConnection();
    }

    @Override
    public Reservation getReservationById(int reservationId) throws ReservationException, InvalidInputException {
        if (reservationId <= 0) {
            throw new InvalidInputException("Reservation ID must be a positive integer.");
        }

        String query = "SELECT * FROM Reservations WHERE ReservationID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractReservationFromResultSet(rs);
            } else {
                throw new ReservationException("No reservation found with ID: " + reservationId);
            }
        } catch (SQLException e) {
            throw new ReservationException("Database error while retrieving reservation", e);
        }
    }


    @Override
    public List<Reservation> getReservationsByCustomerId(int customerId)throws ReservationException  {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations WHERE CustomerID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reservations.add(extractReservationFromResultSet(rs));
            }
        } catch (SQLException e) {
        	throw new ReservationException("Error creating reservation", e);
        }
        return reservations;
    }

    @Override
    public boolean createReservation(Reservation reservation) throws ReservationException, InvalidInputException {
        if (reservation == null) {
            throw new InvalidInputException("Reservation cannot be null.");
        }
        if (reservation.getCustomerID() <= 0 || reservation.getVehicleID() <= 0) {
            throw new InvalidInputException("Customer ID and Vehicle ID must be positive.");
        }
        if (reservation.getStartDate().after(reservation.getEndDate())) {
            throw new InvalidInputException("Start date cannot be after end date.");
        }

        String query = "INSERT INTO Reservations (CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservation.getCustomerID());
            stmt.setInt(2, reservation.getVehicleID());
            stmt.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            stmt.setDouble(5, reservation.getTotalCost());
            stmt.setString(6, reservation.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ReservationException("Error creating reservation", e);
        }
    }


    @Override
    public boolean updateReservation(Reservation reservation)throws ReservationException  {
        String query = "UPDATE Reservations SET CustomerID = ?, VehicleID = ?, StartDate = ?, EndDate = ?, TotalCost = ?, Status = ? WHERE ReservationID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservation.getCustomerID());
            stmt.setInt(2, reservation.getVehicleID());
            stmt.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            stmt.setDouble(5, reservation.getTotalCost());
            stmt.setString(6, reservation.getStatus());
            stmt.setInt(7, reservation.getReservationID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
        	throw new ReservationException("Error updating reservation", e);
        }
        
    }

    @Override
    public boolean cancelReservation(int reservationId) throws ReservationException, InvalidInputException {
        if (reservationId <= 0) {
            throw new InvalidInputException("Reservation ID must be a positive integer.");
        }

        String query = "DELETE FROM Reservations WHERE ReservationID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ReservationException("Error canceling reservation", e);
        }
    }


    private Reservation extractReservationFromResultSet(ResultSet rs) throws SQLException {
        return new Reservation(
            rs.getInt("ReservationID"),
            rs.getInt("CustomerID"),
            rs.getInt("VehicleID"),
            rs.getDate("StartDate"),
            rs.getDate("EndDate"),
            rs.getDouble("TotalCost"),
            rs.getString("Status")
        );
    }
}


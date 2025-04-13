package dao.impl;

import dao.PaymentDAO;
import entity.Payment;
import exception.InvalidDataException;
import exception.DatabaseConnectionException;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public void processPayment(Payment payment) throws InvalidDataException, DatabaseConnectionException {
        validatePayment(payment);

        String sql = "INSERT INTO Payments (OrderID, PaymentMethod, Amount, PaymentDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment.getOrderId());
            stmt.setString(2, payment.getPaymentMethod());
            stmt.setDouble(3, payment.getAmount());
            stmt.setDate(4, java.sql.Date.valueOf(payment.getPaymentDate()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to process payment", e);
        }
    }

    private void validatePayment(Payment payment) throws InvalidDataException {
        if (payment.getAmount() <= 0) {
            throw new InvalidDataException("Payment amount must be positive.");
        }
        if (payment.getPaymentMethod() == null || payment.getPaymentMethod().trim().isEmpty()) {
            throw new InvalidDataException("Payment method is required.");
        }
    }
}

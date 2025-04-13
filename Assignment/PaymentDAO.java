package dao;

import entity.Payment;
import exception.InvalidDataException;
import exception.DatabaseConnectionException;

public interface PaymentDAO {
    void processPayment(Payment payment) throws InvalidDataException, DatabaseConnectionException;
}

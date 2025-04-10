package dao;

import java.util.List;

import entity.Reservation;
import exception.InvalidInputException;
import exception.ReservationException;

public interface IReservationDAO {
	Reservation getReservationById(int reservationId) throws ReservationException, InvalidInputException;

	List<Reservation> getReservationsByCustomerId(int customerId) throws ReservationException, InvalidInputException;

	boolean createReservation(Reservation reservation) throws ReservationException, InvalidInputException;

	boolean updateReservation(Reservation reservation) throws ReservationException, InvalidInputException;

	boolean cancelReservation(int reservationId) throws ReservationException, InvalidInputException;
}


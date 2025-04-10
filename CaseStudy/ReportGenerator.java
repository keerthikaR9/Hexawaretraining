package util;

import dao.impl.AdminDAOImpl;
import dao.impl.CustomerDAOImpl;
import dao.impl.ReservationDAOImpl;
import dao.impl.VehicleDAOImpl;
import entity.Admin;
import entity.Reservation;
import entity.Vehicle;

import java.util.List;

public class ReportGenerator {

    private static final AdminDAOImpl adminDAO = new AdminDAOImpl();
    private static final VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
    private static final ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public static void generateReservationReport(List<Reservation> reservations) {
        System.out.println("\n===== Reservation Report =====");
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        for (Reservation r : reservations) {
            System.out.println("Reservation ID: " + r.getReservationID());
            System.out.println("Customer ID   : " + r.getCustomerID());
            System.out.println("Vehicle ID    : " + r.getVehicleID());
            System.out.println("Start Date    : " + r.getStartDate());
            System.out.println("End Date      : " + r.getEndDate());
            System.out.println("Total Cost    : ₹" + r.getTotalCost());
            System.out.println("Status        : " + r.getStatus());
            System.out.println("------------------------------");
        }
    }

    public static void generateVehicleUtilizationReport(List<Vehicle> vehicles) {
        System.out.println("\n===== Vehicle Utilization Report =====");
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println("Vehicle ID         : " + v.getVehicleID());
            System.out.println("Model              : " + v.getModel());
            System.out.println("Make               : " + v.getMake());
            System.out.println("Year               : " + v.getYear());
            System.out.println("Available          : " + (v.isAvailability() ? "Yes" : "No"));
            System.out.println("Daily Rate         : ₹" + v.getDailyRate());
            System.out.println("------------------------------");
        }
    }

    public static void generateAdminReports(List<Admin> admins) {
        System.out.println("\n===== Admin Report =====");

        if (admins == null || admins.isEmpty()) {
            System.out.println("No admins found.");
            return;
        }

        for (Admin admin : admins) {
            System.out.println("Admin ID    : " + admin.getAdminID());
            System.out.println("Name        : " + admin.getFirstName() + " " + admin.getLastName());
            System.out.println("Email       : " + admin.getEmail());
            System.out.println("Phone Number: " + admin.getPhoneNumber());
            System.out.println("Username    : " + admin.getUsername());
            System.out.println("Role        : " + admin.getRole());
            System.out.println("Join Date   : " + admin.getJoinDate());
            System.out.println("-------------------------------");
        }
    }

    public static void generateRevenueReport(List<Reservation> reservations) {
        System.out.println("\n===== Revenue Report =====");
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservation data found.");
            return;
        }

        double totalRevenue = 0;
        for (Reservation r : reservations) {
            totalRevenue += r.getTotalCost();
        }

        System.out.println("Total Revenue Generated: ₹" + totalRevenue);
        System.out.println("------------------------------");
    }

    // ✅ Final report generator to combine all
    public static void generateAdminReports(List<Admin> admins, List<Vehicle> vehicles, List<Reservation> reservations) {
        System.out.println("\n===== Full Admin Report =====");
        
        generateAdminReports(admins);
        generateVehicleUtilizationReport(vehicles);
        generateReservationReport(reservations);
        generateRevenueReport(reservations);

        System.out.println("===== End of Report =====");
    }

}


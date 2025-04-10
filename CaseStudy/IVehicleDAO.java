package dao;

import java.util.List;
import exception.VehicleNotFoundException;


import entity.Vehicle;

public interface IVehicleDAO {
    Vehicle getVehicleById(int vehicleId)throws VehicleNotFoundException;
    List<Vehicle> getAvailableVehicles();
    boolean addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle);
    boolean removeVehicle(int vehicleId);
	List<Vehicle> getAllVehicles();
}

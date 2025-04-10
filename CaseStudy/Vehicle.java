package entity;

public class Vehicle {
	private int vehicleID;
    private String model;
    private String make;
    private int year;
    private String color;
    private String registrationNumber;
    private String type;  
    private boolean availability;
    private double dailyRate;


    // Default Constructor
    public Vehicle() {}

    // Parameterized Constructor
    public Vehicle(int vehicleID, String make, String model, int year, String color,
            String registrationNumber, String type, boolean availability, double dailyRate)
 {
 this.vehicleID = vehicleID;
 this.make = make;
 this.model = model;
 this.year = year;
 this.color = color;
 this.registrationNumber = registrationNumber;
 this.type = type;
 this.availability = availability;
 this.dailyRate = dailyRate;
}


    // Getters and Setters
    public int getVehicleID() { return vehicleID; }
    public void setVehicleID(int vehicleID) { this.vehicleID = vehicleID; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getType() { return type; } 
    public void setType(String type) { this.type = type; }
    
    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }

    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }

    

}


-- Step 1: Create the database
CREATE DATABASE CarConnect;
USE CarConnect;

--  Customer Table
CREATE TABLE Customer (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15) UNIQUE NOT NULL,
    Address TEXT,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL, -- Store hashed passwords
    RegistrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--  Vehicle Table
CREATE TABLE Vehicle (
    VehicleID INT AUTO_INCREMENT PRIMARY KEY,
    Model VARCHAR(50) NOT NULL,
    Make VARCHAR(50) NOT NULL,
    Year INT NOT NULL,  -- Removed CHECK constraint
    Color VARCHAR(20),
    RegistrationNumber VARCHAR(20) UNIQUE NOT NULL,
    Availability BOOLEAN DEFAULT TRUE,
    DailyRate DECIMAL(10,2) NOT NULL CHECK (DailyRate >= 0)
);


--  Reservation Table
CREATE TABLE Reservation (
    ReservationID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    VehicleID INT NOT NULL,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL,
    TotalCost DECIMAL(10,2) NOT NULL CHECK (TotalCost >= 0),
    Status ENUM('Pending', 'Confirmed', 'Completed', 'Cancelled') DEFAULT 'Pending',
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE,
    FOREIGN KEY (VehicleID) REFERENCES Vehicle(VehicleID) ON DELETE CASCADE
);

--  Admin Table
CREATE TABLE Admin (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15) UNIQUE NOT NULL,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL, -- Store hashed passwords
    Role ENUM('Super Admin', 'Fleet Manager') NOT NULL,
    JoinDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Customer (FirstName, LastName, Email, PhoneNumber, Address, Username, Password)
VALUES ('KARTHEESH', 'N R K', 'kartheesh.n.r.k.2021.cse@ritchennai.edu.in', '9626874974', 'Chennai, Tamil Nadu','Kartheesh',123),
('KATHIRAVAN', 'M', 'kathiravan.m.2021.cse@ritchennai.edu.in', '6374625061', 'Coimbatore, Tamil Nadu','Kathiravan',456),
('KAVIBHARATHI', 'K', 'kavibharathi.k.2021.cse@ritchennai.edu.in', '9342433095', 'Madurai, Tamil Nadu','kavibarathi',789),
('KEERTHANA', 'S', 'keerthanasivakumar1507@gmail.com', '8925279355', 'Trichy, Tamil Nadu','Keerthana',1234),
('KEERTHI', 'A', 'keerthichezian6@gmail.com', '9342570352', 'Salem, Tamil Nadu','Keerthi',5678),
('KEERTHIKA', 'R', 'kikiravi09@gmail.com', '9626360705', 'Tirunelveli, Tamil Nadu','Keerthika',134),
('KETHURAM', 'T A', 'kethuramta@gmail.com', '8825743615', 'Erode, Tamil Nadu','Kethuram',145),
('KIRAN KUMAR', 'P', 'kirankumar.p.2021.cse@ritchennai.edu.in', '8838681246', 'Vellore, Tamil Nadu','Kiran',345),
('KIRAN VIGNESH', 'S', 'kiranvignesh.s.2021.cse@ritchennai.edu.in', '7871342447', 'Thoothukudi, Tamil Nadu','Vignesh',256),
('KISHOREKANNAN', 'A', 'kishorekannan.a.2021.cse@ritchennai.edu.in', '9003616095', 'Kanyakumari, Tamil Nadu','Kishore',468);

UPDATE Customer 
SET Password = SHA2(Password, 256);

INSERT INTO Vehicle (Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate)
VALUES 
('Swift', 'Maruti Suzuki', 2022, 'Red', 'TN01AB1234', TRUE, 45.00),
('i20', 'Hyundai', 2021, 'White', 'TN02CD5678', TRUE, 50.00),
('City', 'Honda', 2023, 'Black', 'TN03EF9101', TRUE, 60.00),
('Creta', 'Hyundai', 2020, 'Gray', 'TN04GH2345', FALSE, 75.00),
('Ertiga', 'Maruti Suzuki', 2021, 'Blue', 'TN05IJ6789', TRUE, 70.00),
('Innova', 'Toyota', 2022, 'Silver', 'TN06KL1122', TRUE, 90.00),
('Fortuner', 'Toyota', 2023, 'Black', 'TN07MN3344', TRUE, 120.00),
('Thar', 'Mahindra', 2021, 'Red', 'TN08OP5566', FALSE, 110.00),
('XUV700', 'Mahindra', 2022, 'White', 'TN09QR7788', TRUE, 130.00),
('Verna', 'Hyundai', 2023, 'Blue', 'TN10ST9900', TRUE, 65.00);

INSERT INTO Reservation (CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status)
VALUES 
(1, 3, '2025-03-22 09:00:00', '2025-03-24 09:00:00', 120.00, 'Confirmed'),
(2, 6, '2025-03-25 12:00:00', '2025-03-27 12:00:00', 180.00, 'Pending'),
(3, 8, '2025-04-01 10:00:00', '2025-04-05 10:00:00', 440.00, 'Completed'),
(4, 2, '2025-04-07 08:00:00', '2025-04-09 08:00:00', 100.00, 'Cancelled'),
(5, 5, '2025-04-10 14:00:00', '2025-04-12 14:00:00', 140.00, 'Confirmed'),
(6, 1, '2025-04-15 16:00:00', '2025-04-18 16:00:00', 135.00, 'Pending'),
(7, 7, '2025-04-20 08:00:00', '2025-04-22 08:00:00', 240.00, 'Confirmed'),
(8, 4, '2025-04-25 12:00:00', '2025-04-28 12:00:00', 225.00, 'Confirmed'),
(9, 9, '2025-05-01 09:00:00', '2025-05-03 09:00:00', 260.00, 'Completed'),
(10, 10, '2025-05-05 15:00:00', '2025-05-08 15:00:00', 195.00, 'Pending');

INSERT INTO Admin (FirstName, LastName, Email, PhoneNumber, Username, Password, Role)
VALUES 
('Rajesh', 'Kumar', 'rajesh.admin@company.com', '9876543210', 'adminRajesh', 'hashed_password1', 'Super Admin'),
('Vignesh', 'M', 'vignesh.admin@company.com', '9765432109', 'adminVignesh', 'hashed_password2', 'Fleet Manager'),
('Suresh', 'B', 'suresh.admin@company.com', '9654321098', 'adminSuresh', 'hashed_password3', 'Super Admin'),
('Arjun', 'N', 'arjun.admin@company.com', '9543210987', 'adminArjun', 'hashed_password4', 'Fleet Manager'),
('Priya', 'S', 'priya.admin@company.com', '9432109876', 'adminPriya', 'hashed_password5', 'Fleet Manager');

UPDATE Admin 
SET Password = SHA2(Password, 256);
show tables;
SELECT * FROM Customer;
SELECT * FROM Vehicle;
SELECT * FROM Reservation;
SELECT * FROM Admin;


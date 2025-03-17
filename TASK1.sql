CREATE DATABASE TechShop;
use Techshop;
CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) not null,
    LastName VARCHAR(50) ,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Phone VARCHAR(15) UNIQUE NOT NULL,
    Address TEXT 
);
CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Description TEXT,
    Price DECIMAL(10,2) NOT NULL
);
CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE
);
CREATE TABLE OrderDetails (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY,  
    OrderID INT,  
    ProductID INT, 
    Quantity INT NOT NULL,  
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);
CREATE TABLE Inventory (
    InventoryID INT AUTO_INCREMENT PRIMARY KEY,  
    ProductID INT,  
    QuantityInStock INT NOT NULL,  
    LastStockUpdate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);
show TABLES;
INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES
('KARTHEESH', 'N R K', 'kartheesh.n.r.k.2021.cse@ritchennai.edu.in', '9626874974', 'Chennai, Tamil Nadu'),
('KATHIRAVAN', 'M', 'kathiravan.m.2021.cse@ritchennai.edu.in', '6374625061', 'Coimbatore, Tamil Nadu'),
('KAVIBHARATHI', 'K', 'kavibharathi.k.2021.cse@ritchennai.edu.in', '9342433095', 'Madurai, Tamil Nadu'),
('KEERTHANA', 'S', 'keerthanasivakumar1507@gmail.com', '8925279355', 'Trichy, Tamil Nadu'),
('KEERTHI', 'A', 'keerthichezian6@gmail.com', '9342570352', 'Salem, Tamil Nadu'),
('KEERTHIKA', 'R', 'kikiravi09@gmail.com', '9626360705', 'Tirunelveli, Tamil Nadu'),
('KETHURAM', 'T A', 'kethuramta@gmail.com', '8825743615', 'Erode, Tamil Nadu'),
('KIRAN KUMAR', 'P', 'kirankumar.p.2021.cse@ritchennai.edu.in', '8838681246', 'Vellore, Tamil Nadu'),
('KIRAN VIGNESH', 'S', 'kiranvignesh.s.2021.cse@ritchennai.edu.in', '7871342447', 'Thoothukudi, Tamil Nadu'),
('KISHOREKANNAN', 'A', 'kishorekannan.a.2021.cse@ritchennai.edu.in', '9003616095', 'Kanyakumari, Tamil Nadu');

INSERT INTO Products (ProductName, Description, Price) VALUES
('Laptop', 'Ultra-slim laptop with AMD Ryzen 7, 16GB RAM, and 1TB SSD', 750.00),
('Smartphone', '5G smartphone with a 120Hz refresh rate and AI-enhanced camera', 500.00),
('Tablet', 'Lightweight tablet with a 2K display, Dolby Atmos speakers, and stylus support', 300.00),
('Headphones', 'Wireless headphones with adaptive noise cancellation and 40-hour battery life', 150.00),
('Smartwatch', 'Water-resistant smartwatch with ECG monitoring and built-in GPS', 200.00),
('Gaming Console', '4K-ready gaming console with ultra-fast SSD and VR compatibility', 500.00),
('Keyboard', 'Compact mechanical keyboard with hot-swappable switches and Bluetooth', 100.00),
('Mouse', 'Ergonomic gaming mouse with adjustable DPI and RGB lighting', 50.00),
('Monitor', '27-inch 2K monitor with IPS panel and 165Hz refresh rate', 200.00),
('External Hard Drive', 'Portable 2TB SSD with USB-C and military-grade durability', 120.00);

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES
(1, '2025-03-10 09:45:00', 1850.00),  
(2, '2025-03-11 14:20:00', 750.00),  
(3, '2025-03-12 18:30:00', 120.00),  
(4, '2025-03-13 11:15:00', 450.00),   
(5, '2025-03-14 16:50:00', 90.00),    
(6, '2025-03-15 10:05:00', 1350.00),  
(7, '2025-03-16 19:40:00', 200.00),   
(8, '2025-03-17 08:25:00', 600.00),  
(9, '2025-03-18 14:10:00', 2200.00),  
(10, '2025-03-19 17:55:00', 380.00);  

INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES
(1, 1, 1),  
(1, 2, 1),  
(2, 3, 1),  
(3, 4, 2), 
(4, 5, 1),  
(5, 6, 1),  
(6, 7, 1),  
(7, 8, 2), 
(8, 9, 1),  
(9, 10, 2); 
INSERT INTO Inventory (ProductID, QuantityInStock, LastStockUpdate) VALUES
(1, 12, '2025-03-01 09:30:00'), 
(2, 20, '2025-03-02 10:45:00'),  
(3, 35, '2025-03-03 14:15:00'),  
(4, 50, '2025-03-04 11:00:00'),  
(5, 18, '2025-03-05 16:20:00'),  
(6, 6, '2025-03-06 17:45:00'),   
(7, 75, '2025-03-07 12:30:00'),  
(8, 90, '2025-03-08 13:55:00'),  
(9, 15, '2025-03-09 15:10:00'),  
(10, 22, '2025-03-10 18:05:00'); 
SELECT * FROM Customers;
SELECT * FROM Products;
SELECT * FROM Orders;
SELECT * FROM OrderDetails;
SELECT * FROM Inventory;

SELECT 
    Orders.OrderID, 
    Orders.OrderDate, 
    Customers.FirstName, 
    Customers.LastName
FROM Orders JOIN Customers ON Orders.CustomerID = Customers.CustomerID;
INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) 
VALUES ('Niketana', 'K P', 'niketanakp@gmail.com', '9876543210', ' Chennai, Tamil Nadu');

UPDATE Products 
SET Price = Price * 1.10;
select * from Products;


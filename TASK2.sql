USE TechShop;
 -- retrieve the names and emails of all customers
SELECT FirstName, LastName, Email FROM Customers;
-- Listing all orders with their order dates and corresponding customer names
SELECT Orders.OrderID, Orders.OrderDate, Customers.FirstName, Customers.LastName
FROM Orders
JOIN Customers ON Orders.CustomerID = Customers.CustomerID;

-- Inserting a new customer record into the "Customers" table
INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) 
VALUES ('Satvik', 'D', 'satvike@gmail.com', '987654310', 'kalpakkam,TamilNadu');

-- Updating the prices of all electronic gadgets in the "Products" table by increasing them by 10%
UPDATE Products 
SET Price = Price * 1.10;

-- Deleting a specific order and its associated order details
SET @OrderID = 10; 
DELETE FROM OrderDetails WHERE OrderID = @OrderID;
DELETE FROM Orders WHERE OrderID = @OrderID;

-- Inserting a new order into the "Orders" table
INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) 
VALUES (11, '2025-03-20 10:30:00', 500.00);

-- Updating the contact information of a specific customer with user input
SET @CustomerID = 1;
SET @NewEmail = 'kartheesh24@gmail.com';
SET @NewAddress = 'Senji, TamilNadu';

UPDATE Customers 
SET Email = @NewEmail, Address = @NewAddress 
WHERE CustomerID = @CustomerID;

--  Recalculating and update the total cost of each order in the "Orders" table
UPDATE Orders 
SET TotalAmount = (
    SELECT COALESCE(SUM(Products.Price * OrderDetails.Quantity), 0) 
    FROM OrderDetails 
    JOIN Products ON OrderDetails.ProductID = Products.ProductID 
    WHERE OrderDetails.OrderID = Orders.OrderID
);

-- Deleting all orders and their associated order details for a specific customer
SET @CustomerID = 13; 

DELETE FROM OrderDetails WHERE OrderID IN (SELECT OrderID FROM Orders WHERE CustomerID = @CustomerID);
DELETE FROM Orders WHERE CustomerID = @CustomerID;

-- Inserting a new electronic gadget into the "Products" table
INSERT INTO Products(ProductName,Description,Price) 
VALUES ('Wireless Earbuds', 'Bluetooth earbuds with noise cancellation and 24-hour battery life', 1200.00);

--  updating the status of a specific order in the "Orders" table by user input
ALTER TABLE Orders ADD COLUMN Status VARCHAR(20) DEFAULT 'Pending';

UPDATE Orders 
SET Status = CASE 
    WHEN OrderID = 1 THEN 'Shipped'
    WHEN OrderID = 2 THEN 'Delivered'
    WHEN OrderID = 3 THEN 'Pending'
    WHEN OrderID = 4 THEN 'Cancelled'
    WHEN OrderID = 5 THEN 'Shipped'
    WHEN OrderID = 6 THEN 'Pending'
    WHEN OrderID = 7 THEN 'Delivered'
    WHEN OrderID = 8 THEN 'Pending'
    WHEN OrderID = 9 THEN 'Delivered'
    WHEN OrderID = 11 THEN 'Delivered'
    
    ELSE Status  
END
WHERE OrderID IN (1, 2, 3, 4, 5,6,7,8,9,11); 

-- Calculating and update the number of orders placed by each customer
ALTER TABLE Customers ADD COLUMN TotalOrders INT DEFAULT 1;

UPDATE Customers 
SET TotalOrders = (
    SELECT COUNT(*) FROM Orders WHERE Orders.CustomerID = Customers.CustomerID
);


create database PetPals;
use PetPals;
create table Pets (
    PetID Int Primary key auto_increment,
    Name varchar(50) not null,
    Age Int not null,
    Breed varchar (100),
    Type varchar(150),
    AvailableForAdoption Bit not null);
    
create table shelters(
  ShelterID Int Primary key auto_increment,
  Name varchar(100) not null,
  Location varchar(200) not null);
  
create table Donations(
  DonationID int primary key auto_increment,
  DonarName varchar (150) not null,
  DonationType varchar (150) Not null,
  DonationAmount decimal CHECK (DonationAmount >= 0),
  DonationItem varchar(150),
  DonationDate datetime default current_timestamp );
  
create table AdoptionEvents(
 EventID int primary key auto_increment,
 EventName varchar(150) not null,
 EventDate datetime default current_timestamp,
 Location varchar(250));
 
create table Participants(
 ParticipantID int primary key auto_increment,
 ParticipantName varchar(150) not null,
 ParticipantType varchar(250)  Not null,
 EventID int,
 foreign key (EventID) references AdoptionEvents(EventID) on delete set null);
 

INSERT INTO Pets (Name, Age, Breed, Type, AvailableForAdoption) VALUES
('Jimmy', 5, 'Golden Retriever', 'Dog', 1),
('Whisky', 6, 'Siamese', 'Cat', 1),
('Charlie', 4, 'Beagle', 'Dog', 0),
('Bonda', 1, 'Persian', 'Cat', 1);


INSERT INTO Shelters (Name, Location) VALUES
('Happy Paws Shelter', 'Chennai'),
('Safe Haven Rescue', 'Kadalur');


INSERT INTO Donations (DonarName, DonationType, DonationAmount, DonationItem, DonationDate) VALUES
('Keerthika', 'Cash', 100.00, NULL,now() ),
('Niketana', 'Item', NULL, 'Dog Food', '2024-04-20');

 
INSERT INTO AdoptionEvents (EventName, EventDate, Location) VALUES
('Pet Adoption Day', '2025-03-30 10:00:00', 'Community Center');


INSERT INTO Participants (ParticipantName, ParticipantType, EventID) VALUES
('Happy Paws Shelter', 'Shelter', 1),
('Safe Haven Rescue', 'Shelter', 1);

Select Name,Age,Breed,Type from pets where availableForAdoption = 1;
Select ParticipantName, ParticipantType from Participants 
where EventID = 1;  

Select Shelters.Name, SUM(Donations.DonationAmount) 
FROM Shelters 
JOIN Donations ON Shelters.ShelterID = Donations.DonationID 
GROUP BY Shelters.Name;

Alter table Pets add column OwnerID INT NULL;

select Name, Age, Breed, Type from Pets 
where OwnerID IS NULL;

select YEAR(DonationDate) AS Year, MONTH(DonationDate) AS Month, SUM(DonationAmount) AS TotalDonations
from Donations
group by Year, Month
order by Year, Month;

select distinct Breed from Pets where Age between 1 and 3 OR Age > 5;

alter table Pets ADD COLUMN ShelterID INT;

SELECT Pets.Name AS PetName, Shelters.Name AS ShelterName
FROM Pets
JOIN Shelters ON Pets.ShelterID = Shelters.ShelterID
WHERE Pets.AvailableForAdoption = 1;

select count(Participants.ParticipantID) AS TotalParticipants from Participants
join AdoptionEvents ON Participants.EventID = AdoptionEvents.EventID
where AdoptionEvents.Location = 'Chennai';

select distinct Breed from Pets where Age between 1 and 5;

select * from Pets where AvailableForAdoption = 1;
desc adoptionevents;

alter table AdoptionEvents add column AdopterName VARCHAR(100);
update AdoptionEvents  
set AdopterName = 'Jimmy'  
where EventID = 1; 

select Pets.Name AS PetName, AdoptionEvents.AdopterName from Pets
inner join AdoptionEvents ON Pets.PetID = AdoptionEvents.EventID;

select Shelters.Name, COUNT(*) from Shelters, Pets  
where Shelters.ShelterID = Pets.ShelterID  
AND Pets.AvailableForAdoption = 1  
group by Shelters.Name;

select p1.Name AS Pet1, p2.Name AS Pet2, p1.Breed, p1.ShelterID
from Pets p1, Pets p2
where p1.ShelterID = p2.ShelterID  
AND p1.Breed = p2.Breed  
AND p1.Name < p2.Name;

select Shelters.Name as shelterName ,AdoptionEvents.EventName as EventName from Shelters
cross join AdoptionEvents;

select Shelters.Name, COUNT(*) AS AdoptedPets
from Shelters, Pets
where Shelters.ShelterID = Pets.ShelterID  
AND Pets.AvailableForAdoption = 0  
Group by Shelters.Name  
order by AdoptedPets DESC 
LIMIT 1;


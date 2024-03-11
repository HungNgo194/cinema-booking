create database BookingCinemaTicketsOnline
go
use BookingCinemaTicketsOnline
go

create table CINEMA
(
	cinemaID int identity(01,1) not null primary key,
	cinemaName nvarchar(200),
	city nvarchar(200),
	address nvarchar(200),
	hotline varchar(200)
)
go

create table ROOM
(
	roomID int identity(01,1) not null primary key,
	numberOfSeats int,
	cinemaID int,

	CONSTRAINT FK_cinemaID1 foreign key(cinemaID) references CINEMA(cinemaID)
)
go

create table MOVIE
(
	movieID int identity(01,1) not null primary key,
	movieName nvarchar(200),
	movieContent nvarchar(200),
	actor nvarchar(200),
	director nvarchar(200),
	age int,
	movieImage nvarchar(200),

	CONSTRAINT UQ_movieName UNIQUE (movieName)
)
go
alter table movie
drop CONSTRAINT UQ_movieName
--ALTER TABLE movie
--ADD CONSTRAINT UQ_movieName UNIQUE (movieName);

create table SHOWTIME
(
	showTimeID int identity(01,1) not null primary key,
	openDate date,
	closeDate date,
	hourStart time,
	hourEnd time,
	showStatus bit,
	roomID int,
	movieID int,

	CONSTRAINT FK_roomID1 foreign key(roomID) references ROOM(roomID),
	CONSTRAINT FK_MovieID1 foreign key(movieID) references MOVIE(movieID)
)
go
--ALTER TABLE SHOWTIME
--ADD CONSTRAINT UQ_hourStart UNIQUE (hourStart);
--ALTER TABLE SHOWTIME
--ADD CONSTRAINT UQ_hourEnd UNIQUE (hourEnd);

create table ACCOUNT
(
	userName varchar(200) not null primary key,
	[password] varchar(200) not null,
	fullName nvarchar(200),
	googleID varchar(200),
	googleName varchar(200),
	email varchar(200),
	phoneNumber varchar(200),
	gender nvarchar(200),
	[role] bit

	CONSTRAINT UQ_userName UNIQUE (userName)
)
go

create table BOOKING
(
	bookingID UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
	numberOfBooking int,
	priceTotal money,
	bookingDate date,
	userName varchar(200),

	CONSTRAINT FK_userName1 foreign key(userName) references ACCOUNT(userName),
	CONSTRAINT UQ_userName2 UNIQUE (userName)
)
go


create table SEATDETAILS (
	seatID varchar(3) not null,
	seatStatus bit,
	roomID int,
	showtimeID int not null,

	primary key (seatID, showTimeID),
	CONSTRAINT FK_roomID2 foreign key(roomID) references ROOM(roomID),
	CONSTRAINT FK_showTimeID2 foreign key(showtimeID) references SHOWTIME(showtimeID)
)
go

create table TICKET
(
	ticketID int identity(01,1) not null primary key,
	showTimeID int,
	seatID varchar(3),
	bookingID UNIQUEIDENTIFIER,

	CONSTRAINT FK_seatID1 foreign key(seatID, showTimeID) references SEATDETAILS(seatID, showTimeID),
	CONSTRAINT FK_showTimeID1 foreign key(showTimeID) references SHOWTIME(showTimeID),
	CONSTRAINT FK_bookingID1 foreign key(bookingID) references BOOKING(bookingID)
)
go


create table MEMBERSHIP
(
	memberID UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
	totalSpend money,
	discount int,
	userName varchar(200),

	CONSTRAINT FK_user1 foreign key(userName) references ACCOUNT(userName),
	CONSTRAINT UQ_userName3 UNIQUE (userName)
)
go

--ALTER TABLE MEMBERSHIP
--ADD CONSTRAINT UQ_userName3 UNIQUE (userName);

CREATE TABLE PAYMENT (
	id decimal(19, 2) PRIMARY KEY,
	amount int,
	orderInfo nvarchar(250),
	responseCode char,
	transactionNo int,
	bank char,
	payDate date,
	transactionStatus bit,
	bookingID UNIQUEIDENTIFIER,
	userName varchar(200),
	CONSTRAINT FK_bookingID2 foreign key(bookingID) references BOOKING(bookingID),
	CONSTRAINT FK_userName4 foreign key(userName) references ACCOUNT(userName)
)
go



select * from ACCOUNT	

delete from account

insert into ACCOUNT values ('phucAdmin', 123, null, null, null, null, null, 'Nam', 1);

insert into ACCOUNT values ('nphuc11111@gmail.com', 123, null, null, null, null, null, 0, 0);

delete from account where userName like 'nphuc11111@gmail.com'

insert into MOVIE values (default, 'ten phim', 'check', 'nguyen hoang phuc, ngo viet hung, duong minh an, ...', 'thien loc', 18); 

select * from movie where movieName like '%Nobita%'

delete from MOVIE where movieName like '%Red%'

select * from MEMBERSHIP

delete from MEMBERSHIP

insert into MEMBERSHIP values (default, 50000, 10, 'nphuc11111@gmail.com');
insert into MEMBERSHIP values (default, 50000, 10, 'hieu123@gmail.com');

select * from MEMBERSHIP where userName = 'nphuc11111@gmail.com'

insert into CINEMA values ('Cinema1', 'HCM', 'thu duc', '0123456789')
select * from CINEMA

insert into ROOM values (30, 1)
select * from ROOM

insert into SHOWTIME
select * from SHOWTIME
delete from SHOWTIME 
delete from SEATDETAILS

select * from MOVIE
select * from ROOM
select * from SHOWTIME
select * from SEATDETAILS

SELECT TOP 1 * FROM ShowTime ORDER BY showTimeID DESC;

update SEATDETAILS set seatStatus = 1 where showTimeID = 26 and seatID = 'A0'

delete from movie where movieID = 14
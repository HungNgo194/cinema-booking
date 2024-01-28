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
	hotline int
)

create table ROOM
(
	roomID int identity(01,1) not null primary key,
	numberOfSeats int,
	cinemaID int,

	CONSTRAINT FK_cinemaID1 foreign key(cinemaID) references CINEMA(cinemaID)
)

create table MOVIE
(
	movieID int identity(01,1) not null primary key,
	movieName nvarchar(200),
	movieContent nvarchar(200),
	actor nvarchar(200),
	director nvarchar(200),
	age int
)

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
create table ACCOUNT
(
	userName varchar(200) not null primary key,
	[password] varchar(200),
	lastName nvarchar(200),
	googleID varchar(200),
	googleName varchar(200),
	dob date,
	email varchar(200),
	phoneNumber varchar(200),
	gender bit,
	[role] bit
)


create table BOOKING
(
	bookingID UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
	numberOfBooking int,
	priceTotal money,
	bookingDate date,
	userName varchar(200),

	CONSTRAINT FK_userName1 foreign key(userName) references ACCOUNT(userName)
)

create table SEAT 
(
	seatID varchar(3) not null primary key,
	seatStatus bit,
	roomID int,

	CONSTRAINT FK_roomID2 foreign key(roomID) references ROOM(roomID)
)

create table TICKET
(
	ticketID int identity(01,1) not null primary key,
	showTimeID int,
	seatID varchar(3),
	bookingID UNIQUEIDENTIFIER,

	CONSTRAINT FK_showTimeID1 foreign key(showTimeID) references SHOWTIME(showTimeID),
	CONSTRAINT FK_seatID1 foreign key(seatID) references SEAT(seatID),
	CONSTRAINT FK_bookingID1 foreign key(bookingID) references BOOKING(bookingID)

)

create table MEMBERSHIP
(
	memberID UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
	totalSpend money,
	discount int,
	userName varchar(200),

	CONSTRAINT FK_user1 foreign key(userName) references ACCOUNT(userName)
)

select * from ACCOUNT

delete from account
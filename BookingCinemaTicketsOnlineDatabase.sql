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
ALTER TABLE movie
ADD CONSTRAINT UQ_movieName UNIQUE (movieName);


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
)
alter table account
alter column gender nvarchar(200)

ALTER TABLE account
ADD CONSTRAINT UQ_userName UNIQUE (userName);

create table BOOKING
(
	bookingID UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
	numberOfBooking int,
	priceTotal money,
	bookingDate date,
	userName varchar(200),

	CONSTRAINT FK_userName1 foreign key(userName) references ACCOUNT(userName)
)

ALTER TABLE BOOKING
ADD CONSTRAINT UQ_userName2 UNIQUE (userName);

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

ALTER TABLE MEMBERSHIP
ADD CONSTRAINT UQ_userName3 UNIQUE (userName);

select * from ACCOUNT	

delete from account

insert into ACCOUNT values ('phucAdmin', 123, null, null, null, null, null, 'Nam', 1);

insert into ACCOUNT values ('nphuc11111@gmail.com', 123, null, null, null, null, null, 0, 0);

insert into MOVIE values (default, 'ten phim', 'check', 'nguyen hoang phuc, ngo viet hung, duong minh an, ...', 'thien loc', 18); 

select * from movie where movieName like '%Nobita%'

delete from MOVIE

select * from MEMBERSHIP

delete from MEMBERSHIP

insert into MEMBERSHIP values (default, 50000, 10, 'nphuc11111@gmail.com');

select * from MEMBERSHIP where userName = 'nphuc11111@gmail.com'

insert into CINEMA values ('Cinema1', 'HCM', 'thu duc', '0123456789')
select * from CINEMA

insert into ROOM values (50, 1)
select * from ROOM

insert into SHOWTIME values ()
select * from SHOWTIME
delete from SHOWTIME 
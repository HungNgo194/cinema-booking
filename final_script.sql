USE [BookingCinemaTicketsOnline]
GO
/****** Object:  Table [dbo].[ACCOUNT]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ACCOUNT](
	[userName] [varchar](200) NOT NULL,
	[password] [varchar](200) NOT NULL,
	[fullName] [nvarchar](200) NULL,
	[googleID] [varchar](200) NULL,
	[googleName] [varchar](200) NULL,
	[email] [varchar](200) NULL,
	[phoneNumber] [varchar](200) NULL,
	[gender] [nvarchar](200) NULL,
	[role] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BOOKING]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BOOKING](
	[bookingID] [varchar](200) NOT NULL,
	[numberOfBooking] [int] NULL,
	[priceTotal] [money] NULL,
	[bookingDate] [date] NULL,
	[userName] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[bookingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CINEMA]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CINEMA](
	[cinemaID] [int] IDENTITY(1,1) NOT NULL,
	[cinemaName] [nvarchar](200) NULL,
	[city] [nvarchar](200) NULL,
	[address] [nvarchar](200) NULL,
	[hotline] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[cinemaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MEMBERSHIP]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MEMBERSHIP](
	[memberID] [uniqueidentifier] NOT NULL,
	[totalSpend] [money] NULL,
	[discount] [int] NULL,
	[userName] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[memberID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MOVIE]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MOVIE](
	[movieID] [int] IDENTITY(1,1) NOT NULL,
	[movieName] [nvarchar](200) NULL,
	[movieContent] [nvarchar](200) NULL,
	[actor] [nvarchar](200) NULL,
	[director] [nvarchar](200) NULL,
	[age] [int] NULL,
	[movieImage] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[movieID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PAYMENT]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PAYMENT](
	[id] [decimal](19, 2) NOT NULL,
	[amount] [int] NULL,
	[orderInfo] [nvarchar](250) NULL,
	[responseCode] [char](5) NULL,
	[transactionNo] [int] NULL,
	[bank] [char](5) NULL,
	[payDate] [date] NULL,
	[transactionStatus] [char](5) NULL,
	[bookingID] [varchar](200) NULL,
	[userName] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ROOM]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ROOM](
	[roomID] [int] IDENTITY(1,1) NOT NULL,
	[numberOfSeats] [int] NULL,
	[cinemaID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SEATDETAILS]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SEATDETAILS](
	[seatID] [varchar](3) NOT NULL,
	[seatStatus] [bit] NULL,
	[roomID] [int] NULL,
	[showtimeID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[seatID] ASC,
	[showtimeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SHOWTIME]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SHOWTIME](
	[showTimeID] [int] IDENTITY(1,1) NOT NULL,
	[openDate] [date] NULL,
	[closeDate] [date] NULL,
	[hourStart] [time](7) NULL,
	[hourEnd] [time](7) NULL,
	[showStatus] [bit] NULL,
	[roomID] [int] NULL,
	[movieID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[showTimeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TICKET]    Script Date: 3/14/2024 10:00:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TICKET](
	[ticketID] [int] IDENTITY(1,1) NOT NULL,
	[showTimeID] [int] NULL,
	[seatID] [varchar](3) NULL,
	[bookingID] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[ticketID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ACCOUNT] ([userName], [password], [fullName], [googleID], [googleName], [email], [phoneNumber], [gender], [role]) VALUES (N'admin2@gmail.com', N'1', N'mĩnh an ', NULL, NULL, N'admin2@gmail.com', N'9181231', N'Nam', 0)
INSERT [dbo].[ACCOUNT] ([userName], [password], [fullName], [googleID], [googleName], [email], [phoneNumber], [gender], [role]) VALUES (N'andmse182449@fpt.edu.vn', N'null', N'andmse182449@fpt.edu.vn', N'118346277130265871060', N'andmse182449@fpt.edu.vn', N'andmse182449@fpt.edu.vn', NULL, NULL, 0)
INSERT [dbo].[ACCOUNT] ([userName], [password], [fullName], [googleID], [googleName], [email], [phoneNumber], [gender], [role]) VALUES (N'phucAdmin', N'123', NULL, NULL, NULL, NULL, NULL, N'Nam', 1)
GO
INSERT [dbo].[BOOKING] ([bookingID], [numberOfBooking], [priceTotal], [bookingDate], [userName]) VALUES (N'd0f38454-03ae-4548-a84b-2e3ac461a041', 1, 70000.0000, CAST(N'2024-03-11' AS Date), N'admin2@gmail.com')
GO
SET IDENTITY_INSERT [dbo].[CINEMA] ON 

INSERT [dbo].[CINEMA] ([cinemaID], [cinemaName], [city], [address], [hotline]) VALUES (1, N'NCTTM Thảo Điền (TP.HCM)', N'BARIA', N'123 Xxx, Phường An Phú, Thảo Điền, Quận 2, TP. Hồ Chí Minh', N'961792119')
SET IDENTITY_INSERT [dbo].[CINEMA] OFF
GO
SET IDENTITY_INSERT [dbo].[MOVIE] ON 

INSERT [dbo].[MOVIE] ([movieID], [movieName], [movieContent], [actor], [director], [age], [movieImage]) VALUES (1, N'ONE PIECE(CÚ ĐẤM SẤM SÉT)', N'BARIA', N'aaaa', N'Nguyễn Hoàng Phúc', 18, N'RED.jpg')
INSERT [dbo].[MOVIE] ([movieID], [movieName], [movieContent], [actor], [director], [age], [movieImage]) VALUES (6, N'NOBITA', N'123', N'aaaa', N'HÆ°ng NgÃ´', 17, N'Nobita.jpg')
INSERT [dbo].[MOVIE] ([movieID], [movieName], [movieContent], [actor], [director], [age], [movieImage]) VALUES (7, N'Kungfu panda', N'hoat hinh', N'panda', N'panda', 5, N'KungFuPanda_2.jpg')
SET IDENTITY_INSERT [dbo].[MOVIE] OFF
GO
SET IDENTITY_INSERT [dbo].[ROOM] ON 

INSERT [dbo].[ROOM] ([roomID], [numberOfSeats], [cinemaID]) VALUES (1, 30, 1)
INSERT [dbo].[ROOM] ([roomID], [numberOfSeats], [cinemaID]) VALUES (2, 30, 1)
SET IDENTITY_INSERT [dbo].[ROOM] OFF
GO
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A0', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A0', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A0', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A0', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A0', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A1', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A1', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A1', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A1', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A1', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A2', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A2', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A2', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A2', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A2', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A3', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A3', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A3', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A3', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A3', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A4', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A4', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A4', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A4', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A4', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A5', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A5', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A5', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A5', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A5', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A6', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A6', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A6', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A6', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A6', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A7', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A7', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A7', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A7', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A7', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A8', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A8', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A8', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A8', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A8', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A9', 0, 2, 2)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A9', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A9', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A9', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'A9', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B0', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B0', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B0', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B0', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B1', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B1', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B1', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B1', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B2', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B2', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B2', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B2', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B3', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B3', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B3', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B3', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B4', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B4', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B4', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B4', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B5', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B5', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B5', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B5', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B6', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B6', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B6', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B6', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B7', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B7', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B7', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B7', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B8', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B8', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B8', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B8', 1, 2, 7)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B9', 0, 1, 4)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B9', 0, 1, 5)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B9', 0, 2, 6)
INSERT [dbo].[SEATDETAILS] ([seatID], [seatStatus], [roomID], [showtimeID]) VALUES (N'B9', 1, 2, 7)
GO
SET IDENTITY_INSERT [dbo].[SHOWTIME] ON 

INSERT [dbo].[SHOWTIME] ([showTimeID], [openDate], [closeDate], [hourStart], [hourEnd], [showStatus], [roomID], [movieID]) VALUES (1, CAST(N'2024-03-10' AS Date), CAST(N'2024-01-05' AS Date), CAST(N'20:00:00' AS Time), CAST(N'13:00:00' AS Time), 0, 2, 1)
INSERT [dbo].[SHOWTIME] ([showTimeID], [openDate], [closeDate], [hourStart], [hourEnd], [showStatus], [roomID], [movieID]) VALUES (2, CAST(N'2024-03-25' AS Date), CAST(N'2024-01-05' AS Date), CAST(N'20:00:00' AS Time), CAST(N'20:00:00' AS Time), 1, 2, 1)
INSERT [dbo].[SHOWTIME] ([showTimeID], [openDate], [closeDate], [hourStart], [hourEnd], [showStatus], [roomID], [movieID]) VALUES (3, CAST(N'2024-01-03' AS Date), CAST(N'2024-01-05' AS Date), CAST(N'15:00:00' AS Time), CAST(N'13:00:00' AS Time), 0, 2, 1)
INSERT [dbo].[SHOWTIME] ([showTimeID], [openDate], [closeDate], [hourStart], [hourEnd], [showStatus], [roomID], [movieID]) VALUES (4, CAST(N'2024-03-12' AS Date), CAST(N'2024-03-12' AS Date), CAST(N'01:00:00' AS Time), CAST(N'02:00:00' AS Time), 0, 2, 1)
INSERT [dbo].[SHOWTIME] ([showTimeID], [openDate], [closeDate], [hourStart], [hourEnd], [showStatus], [roomID], [movieID]) VALUES (5, CAST(N'2024-03-12' AS Date), CAST(N'2024-03-12' AS Date), CAST(N'13:27:00' AS Time), CAST(N'14:04:00' AS Time), 0, 2, 1)
INSERT [dbo].[SHOWTIME] ([showTimeID], [openDate], [closeDate], [hourStart], [hourEnd], [showStatus], [roomID], [movieID]) VALUES (6, CAST(N'2024-03-15' AS Date), CAST(N'2024-03-13' AS Date), CAST(N'03:11:00' AS Time), CAST(N'04:11:00' AS Time), 1, 1, 7)
INSERT [dbo].[SHOWTIME] ([showTimeID], [openDate], [closeDate], [hourStart], [hourEnd], [showStatus], [roomID], [movieID]) VALUES (7, CAST(N'2024-03-15' AS Date), CAST(N'2024-03-17' AS Date), CAST(N'04:13:00' AS Time), CAST(N'05:13:00' AS Time), 1, 2, 7)
SET IDENTITY_INSERT [dbo].[SHOWTIME] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_userName]    Script Date: 3/14/2024 10:00:08 PM ******/
ALTER TABLE [dbo].[ACCOUNT] ADD  CONSTRAINT [UQ_userName] UNIQUE NONCLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_userName2]    Script Date: 3/14/2024 10:00:08 PM ******/

GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_userName3]    Script Date: 3/14/2024 10:00:08 PM ******/
ALTER TABLE [dbo].[MEMBERSHIP] ADD  CONSTRAINT [UQ_userName3] UNIQUE NONCLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_movieName]    Script Date: 3/14/2024 10:00:08 PM ******/
ALTER TABLE [dbo].[MOVIE] ADD  CONSTRAINT [UQ_movieName] UNIQUE NONCLUSTERED 
(
	[movieName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BOOKING] ADD  DEFAULT (newid()) FOR [bookingID]
GO
ALTER TABLE [dbo].[MEMBERSHIP] ADD  DEFAULT (newid()) FOR [memberID]
GO
ALTER TABLE [dbo].[BOOKING]  WITH CHECK ADD  CONSTRAINT [FK_userName1] FOREIGN KEY([userName])
REFERENCES [dbo].[ACCOUNT] ([userName])
GO
ALTER TABLE [dbo].[BOOKING] CHECK CONSTRAINT [FK_userName1]
GO
ALTER TABLE [dbo].[MEMBERSHIP]  WITH CHECK ADD  CONSTRAINT [FK_user1] FOREIGN KEY([userName])
REFERENCES [dbo].[ACCOUNT] ([userName])
GO
ALTER TABLE [dbo].[MEMBERSHIP] CHECK CONSTRAINT [FK_user1]
GO
ALTER TABLE [dbo].[PAYMENT]  WITH CHECK ADD  CONSTRAINT [FK_bookingID2] FOREIGN KEY([bookingID])
REFERENCES [dbo].[BOOKING] ([bookingID])
GO
ALTER TABLE [dbo].[PAYMENT] CHECK CONSTRAINT [FK_bookingID2]
GO
ALTER TABLE [dbo].[PAYMENT]  WITH CHECK ADD  CONSTRAINT [FK_userName4] FOREIGN KEY([userName])
REFERENCES [dbo].[ACCOUNT] ([userName])
GO
ALTER TABLE [dbo].[PAYMENT] CHECK CONSTRAINT [FK_userName4]
GO
ALTER TABLE [dbo].[ROOM]  WITH CHECK ADD  CONSTRAINT [FK_cinemaID1] FOREIGN KEY([cinemaID])
REFERENCES [dbo].[CINEMA] ([cinemaID])
GO
ALTER TABLE [dbo].[ROOM] CHECK CONSTRAINT [FK_cinemaID1]
GO
ALTER TABLE [dbo].[SEATDETAILS]  WITH CHECK ADD  CONSTRAINT [FK_roomID3] FOREIGN KEY([roomID])
REFERENCES [dbo].[ROOM] ([roomID])
GO
ALTER TABLE [dbo].[SEATDETAILS] CHECK CONSTRAINT [FK_roomID3]
GO
ALTER TABLE [dbo].[SEATDETAILS]  WITH CHECK ADD  CONSTRAINT [FK_showTimeID2] FOREIGN KEY([showtimeID])
REFERENCES [dbo].[SHOWTIME] ([showTimeID])
GO
ALTER TABLE [dbo].[SEATDETAILS] CHECK CONSTRAINT [FK_showTimeID2]
GO
ALTER TABLE [dbo].[SHOWTIME]  WITH CHECK ADD  CONSTRAINT [FK_MovieID1] FOREIGN KEY([movieID])
REFERENCES [dbo].[MOVIE] ([movieID])
GO
ALTER TABLE [dbo].[SHOWTIME] CHECK CONSTRAINT [FK_MovieID1]
GO
ALTER TABLE [dbo].[SHOWTIME]  WITH CHECK ADD  CONSTRAINT [FK_roomID1] FOREIGN KEY([roomID])
REFERENCES [dbo].[ROOM] ([roomID])
GO
ALTER TABLE [dbo].[SHOWTIME] CHECK CONSTRAINT [FK_roomID1]
GO
ALTER TABLE [dbo].[TICKET]  WITH CHECK ADD  CONSTRAINT [FK_bookingID1] FOREIGN KEY([bookingID])
REFERENCES [dbo].[BOOKING] ([bookingID])
GO
ALTER TABLE [dbo].[TICKET] CHECK CONSTRAINT [FK_bookingID1]
GO
ALTER TABLE [dbo].[TICKET]  WITH CHECK ADD  CONSTRAINT [FK_seatID_showtimeID] FOREIGN KEY([seatID], [showTimeID])
REFERENCES [dbo].[SEATDETAILS] ([seatID], [showtimeID])
GO
ALTER TABLE [dbo].[TICKET] CHECK CONSTRAINT [FK_seatID_showtimeID]
GO
ALTER TABLE [dbo].[TICKET]  WITH CHECK ADD  CONSTRAINT [FK_showTimeID1] FOREIGN KEY([showTimeID])
REFERENCES [dbo].[SHOWTIME] ([showTimeID])
GO
ALTER TABLE [dbo].[TICKET] CHECK CONSTRAINT [FK_showTimeID1]
GO
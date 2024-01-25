-- Account
insert into ACCOUNT values
('duongminhan', 'minhan123', N'An Dương', '20040302', 'ansiucapcute@gmail.com', 0961792119, 0, 0),
('nguyenhoangphuc', 'phuchoang123', N'Phúc Nguyễn', '20041002', 'phuccute@gmail.com', 0921792119, 1, 1)
select * from ACCOUNT
-- Movie
insert into MOVIE values
(N'Chúa Tể của những chiếc Nhẫn', N'Kể về một chàng trai đi giao lưu thám hiểm', N'Minh An, Hoàng Phúc', N'Hưng Ngô', 18)	
select * from MOVIE
-- Cinema
insert into CINEMA values
(N'Những Cậu Trai Thân Mật', N'Thành phố Hồ Chí Minh', N'178 Hoàng Đạo Thành, Thảo Điền, Quận 2', 0961792117)	
select * from CINEMA
-- Room
insert into Room values
(20, 1)	
select * from Room
-- Seat
insert into Seat values
('A01', 0, 1),	
('A02', 0, 1),	
('A03', 0, 1),
('A04', 0, 1),
('A05', 0, 1),
('A06', 0, 1),
('A07', 0, 1),
('A08', 0, 1),
('A09', 0, 1),
('A10', 0, 1),
('B01', 0, 1),	
('B02', 0, 1),	
('B03', 0, 1),
('B04', 0, 1),
('B05', 0, 1),
('B06', 0, 1),
('B07', 0, 1),
('B08', 0, 1),
('B09', 0, 1),
('B10', 0, 1)
select * from Seat
-- SHOWTIME
insert into SHOWTIME values
('20240101', '20240201', '15:00:00', '16:00:00', 0, 1, 3)
select * from SHOWTIME
-- Booking 
insert into Booking values
(default,4, 70000, '20240101', 'duongminhan')
select * from Booking
-- Ticket 
insert into Ticket values
(1, 'A01', '38A914E1-A5D3-4BDF-B778-142454406C78'), 
(1, 'A02', '38A914E1-A5D3-4BDF-B778-142454406C78'), 
(1, 'A03', '38A914E1-A5D3-4BDF-B778-142454406C78'), 
(1, 'A04', '38A914E1-A5D3-4BDF-B778-142454406C78')
select * from Ticket
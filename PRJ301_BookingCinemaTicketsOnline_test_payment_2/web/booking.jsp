<%-- 
    Document   : booking.jsp
    Created on : Jan 30, 2024, 3:40:16 PM
    Author     : ROG STRIX
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Những Cậu Trai Thân Mật</title>
        <link rel="icon" href="images/logo.jpg" type="images/x-icon">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./css/booking.css">
        <link rel="stylesheet" href="./css/showtime.css">
        <link rel="stylesheet" href="./css/member.css">

    </head>

    <body>

        <!-- HEADER -->
        <div class="nav-header">
            <nav class="nav-container">
                <a href="#" class="nav-logo"><img src="images/logo.jpg" alt="logo"></a>

                <div class="nav-menu" id="nav-menu">
                    <ul class="nav-list">
                        <li class="nav-item"><a href="#" class="nav-link">Phim</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Lịch Chiếu</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Khuyến Mãi</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Hỏi & Đáp</a></li>
                    </ul>

                    <!-- Close button -->
                    <div class="nav-close" id="nav-close">
                        <i class="ri-close-line"></i>
                    </div>
                </div>

                <div class="nav-actions">
                    <!-- Seach button -->
                    <i class="ri-search-line nav-search" id="search-btn"></i>

                    <!-- Login button -->
                    <a href="login.html"><i class="ri-user-line nav-login" id="login-btn"></i></a>

                    <!-- Toggle button -->
                    <div class="nav-toggle" id="nav-toggle">
                        <i class="ri-menu-line"></i>
                    </div>
                </div>
            </nav>
        </div>
        <div>

            <div class="body-container">
                <!-- CURRENT CHOOSE -->

                <div class="order-title">
                    <div class="order-wrap">
                        <div class="order-overview">
                            <c:set var="movie" value="${sessionScope.MOVIE}"/>
                            <div class="about-movie">
                                <h2><strong>Tên phim</strong><br><span class="movie" style="font-size: 25px;">${movie.movieName}</span></h2>
                                <h2><strong>Nội dung</strong><br><span class="movie" style="font-size: 25px;">${movie.content}</span></h2>
                                <h2><strong>Đạo diễn</strong><br><span class="movie" style="font-size: 25px;">${movie.director}</span></h2>
                                <h2><strong>Diễn viên</strong><br><span class="movie" style="font-size: 25px;">${movie.actor}</span></h2>

                            </div>
                            <div class="about-movieImg">
                                <img src="images/NVT8.jpg" />
                            </div>
                        </div>
                        <!-- SEAT -->
                        <div class="cinema-wrap">
                            <c:set var="seats" value="${sessionScope.ArraySeats}" />

                            <script>
                                document.addEventListener('DOMContentLoaded', function () {
                                    const seats2 = document.querySelectorAll('.seat');
                                    seats2.forEach(seat => {
                                        const seat_chosen = [<c:forEach items="${seats}" var="seat" varStatus="status">
                                        "${seat.getSeatID()}"<c:if test="${!status.last}">, </c:if>
                                </c:forEach>
                                        ];
                                        const seatId = seat.innerText;
                                        if (seat_chosen.includes(seatId)) {
                                            seat.style.backgroundColor = 'red';
                                            seat.classList.toggle('already-selected');
                                        }
                                        seat.addEventListener('click', () => {
                                            if (seat_chosen.includes(seatId)) {
                                                alert('Ghế này đã được người dùng khác chọn');
                                            }
                                        });
                                    });
                                });
                            </script>

                            <c:set var="showlists" value="${sessionScope.SHOWTIMELIST}"/>
                            <c:set var="time" value="${requestScope.time}"/>
                            <form action="./LoadShowTimeServlet" method="POST">
                                <div class="shtime-wr" style="text-align: center">
                                    <h2 class="heading">LỊCH CHIẾU</h2>
                                    <c:forEach items="${showlists}" var="show" varStatus="i">
                                        <div class="swiper-container">
                                            <div class="time-item">
                                                <input type="submit" id="date" name="showtime" value="${show}"
                                                       style="
                                                       font-size: 20px;
                                                       padding: 20px;
                                                       border: 4px solid;
                                                       cursor: pointer">
                                            </div>                                                                                                                                                
                                        </div>
                                    </c:forEach>
                                    <h2 class="heading">DANH SÁCH RẠP</h2>
                                    <c:set var="cinemas" value="${requestScope.SHOWTIMELIST}"/>
                                    <div class="container">
                                        <p class="collapsible">NCTTM Thảo Điền (TP.HCM)</p>
                                        <div class="content">
                                            <p class="addr">271 Nguyễn Trãi, Phường Nguyễn Cư Trinh, Quận 1, Thành Phố Hồ Chí Minh</p>
                                            <div class="body-list">
                                                <ul class="list-time">
                                                    <c:set var="shows" value="${requestScope.showTimes}"/>
                                                    <c:forEach items="${shows}" var="show">                                                                                                       
                                                        <li class="item-time">${show}</li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!--GHE NGOI-->
                            <div class="seat-choice">
                                <div class="television" style="text-align: center;">
                                    <h2>MÀN HÌNH</h2>
                                </div>

                                <div class="seats">

                                    <div class="row">
                                        <div class="seat">A1</div>
                                        <div class="seat">A2</div>
                                        <div class="seat">A3</div>
                                        <div class="seat">A4</div>
                                        <div class="seat">A5</div>
                                        <div class="seat">A6</div>
                                        <div class="seat">A7</div>
                                        <div class="seat">A8</div>
                                        <div class="seat">A9</div>
                                        <div class="seat">A10</div>

                                    </div>
                                    <div class="row">
                                        <div class="seat">B1</div>
                                        <div class="seat">B2</div>
                                        <div class="seat">B3</div>
                                        <div class="seat">B4</div>
                                        <div class="seat">B5</div>
                                        <div class="seat">B6</div>
                                        <div class="seat">B7</div>
                                        <div class="seat">B8</div>
                                        <div class="seat">B9</div>
                                        <div class="seat">B10</div>

                                    </div><div class="row">
                                        <div class="seat">C1</div>
                                        <div class="seat">C2</div>
                                        <div class="seat">C3</div>
                                        <div class="seat">C4</div>
                                        <div class="seat">C5</div>
                                        <div class="seat">C6</div>
                                        <div class="seat">C7</div>
                                        <div class="seat">C8</div>
                                        <div class="seat">C9</div>
                                        <div class="seat">C10</div>

                                    </div><div class="row">
                                        <div class="seat">D1</div>
                                        <div class="seat">D2</div>
                                        <div class="seat">D3</div>
                                        <div class="seat">D4</div>
                                        <div class="seat">D5</div>
                                        <div class="seat">D6</div>
                                        <div class="seat">D7</div>
                                        <div class="seat">D8</div>
                                        <div class="seat">D9</div>
                                        <div class="seat">D10</div>

                                    </div><div class="row">
                                        <div class="seat">E1</div>
                                        <div class="seat">E2</div>
                                        <div class="seat">E3</div>
                                        <div class="seat">E4</div>
                                        <div class="seat">E5</div>
                                        <div class="seat">E6</div>
                                        <div class="seat">E7</div>
                                        <div class="seat">E8</div>
                                        <div class="seat">E9</div>
                                        <div class="seat">E10</div>

                                    </div><div class="row">
                                        <div class="seat">F1</div>
                                        <div class="seat">F2</div>
                                        <div class="seat">F3</div>
                                        <div class="seat">F4</div>
                                        <div class="seat">F5</div>
                                        <div class="seat">F6</div>
                                        <div class="seat">F7</div>
                                        <div class="seat">F8</div>
                                        <div class="seat">F9</div>
                                        <div class="seat">F10</div>

                                    </div><div class="row">
                                        <div class="seat">G1</div>
                                        <div class="seat">G2</div>
                                        <div class="seat">G3</div>
                                        <div class="seat">G4</div>
                                        <div class="seat">G5</div>
                                        <div class="seat">G6</div>
                                        <div class="seat">G7</div>
                                        <div class="seat">G8</div>
                                        <div class="seat">G9</div>
                                        <div class="seat">G10</div>

                                    </div><div class="row">
                                        <div class="seat">H1</div>
                                        <div class="seat">H2</div>
                                        <div class="seat">H3</div>
                                        <div class="seat">H4</div>
                                        <div class="seat">H5</div>
                                        <div class="seat">H6</div>
                                        <div class="seat">H7</div>
                                        <div class="seat">H8</div>
                                        <div class="seat">H9</div>
                                        <div class="seat">H10</div>

                                    </div>

                                </div>



                                <div class="right">
                                    <div class="showcase">
                                        <div class="normal">
                                            <div class="seat1"></div>
                                            <strong>GHẾ THƯỜNG</strong>
                                        </div>
                                        <div class="seat-selected">
                                            <div class="seat1 selected"></div>
                                            <strong>GHẾ ĐANG CHỌN</strong>
                                        </div>
                                        <div class="seat-selected">
                                            <div class="seat1 already-selected"></div>
                                            <strong>GHẾ ĐÃ ĐẶT</strong>
                                        </div>
                                    </div>
                                </div>



                            </div>

                        </div>
                    </div>
                </div>

            </div>
            <form action="./BookingServlet" method="POST">
                <div class="order-wrap">

                    <div class="final-check">
                        <ul class="about-ticket">
                            <li>
                                <p class="caption">Suất chiếu</p>
                                <p class="value"><input readonly="readonly" type="text" name="showTime" id="showTime" value=""></p>
                            </li>
                            <li>
                                <p class="caption">Ngày</p>
                                <p class="value"><input readonly="readonly" type="text" name="showDate" value="${time}"></p>
                            </li>

                            <li>
                                <p class="caption">Số lượng</p>
                                <p class="value"> <input readonly="readonly" type="text" name="numOfBook" id="count" value="0"> <sub>vé</sub></p>

                            </li>
                            <li>
                                <p class="caption">Tổng số tiền</p>

                                <p class="value"> <input readonly="readonly" type="text" name="total" id="total" value="0"><sub>VNĐ</sub></p>
                            </li>
                        </ul>
                        <ul class="about-seat">
                            <li>
                                <p class="value1">
                                    Số ghế: <input readonly="readonly" type="text" name="seat" id="Seatnumber">
                                </p>


                            </li>

                        </ul>

                    </div>
                </div>
                <!-- BUTTON -->
                <div class="input-but col-md-12">
                    <button type="submit" class="buy-btn" id="checkout-btn" value="THANH TOÁN" style="
                            font-size: 20px;
                            padding: 20px;
                            border: 4px solid;
                            border-radius: 30px 30px 30px 0;"
                            disabled>THANH TOÁN
                    </button>
                    <input type="button" class="back-btn" value="QUAY LẠI" data-link="userWeb-page.jsp">
                </div>
            </form>
            <script src="js/booking.js"></script>
    </body>
</html>

<%-- 
    Document   : booking.jsp
    Created on : Jan 30, 2024, 3:40:16 PM
    Author     : ROG STRIX
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
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
                <a href="LoadAllMovieServlet?url=userWeb-page.jsp" class="nav-logo"><img src="images/logo.jpg" alt="logo"></a>

                <div class="nav-menu" id="nav-menu">
                    <ul class="nav-list">
                        <li class="nav-item"><a href="LoadAllMovieServlet?url=index2.jsp" class="nav-link">Phim</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Khuyến Mãi</a></li>
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
                            <c:set var="movie" value="${requestScope.MOVIE}"/>
                            <div class="about-movie">

                                <h2><strong>Tên phim</strong><br><input type="hidden" name="movie" value="${movie.getMovieName()}" style="font-size: 25px;">${movie.getMovieName()}</h2>
                                <h2><strong>Nội dung</strong><br><input type="hidden" name="content" value="${movie.getMovieContent()}" style="font-size: 25px;">${movie.getMovieContent()}</h2>
                                <h2><strong>Đạo diễn</strong><br><input type="hidden" name="director" value="${movie.getDirector()}" style="font-size: 25px;">${movie.getDirector()}</h2>
                                <h2><strong>Diễn viên</strong><br><input type="hidden" name="actor" value="${movie.getActor()}" style="font-size: 25px;">${movie.getActor()}</h2>

                            </div>
                            <div class="about-movieImg">
                                <img src="img/${movie.movieImage}" />
                            </div>
                        </div>
                        <!-- SEAT -->
                        <div class="cinema-wrap">





                            <div class="shtime-wr" style="text-align: center">
                                <h2 class="heading">LỊCH CHIẾU</h2>
                                <form action="./LoadShowTimeServlet" method="POST">
                                    <c:set var="showlists" value="${sessionScope.SHOWTIMELIST}"/>
                                    <div class="swiper-container" style="
                                         display: flex;
                                         justify-content: center;
                                         gap: 1.2rem;">
                                        <c:forEach items="${showlists}" var="showTime" varStatus="i">
                                            <input type="hidden" name="id" value="${movie.getMovieName()}">
                                            <div class="time-item">

                                                <c:set var="lay1" value="${showTime}"/>
                                                <input  type="submit" name="showtime" value="${showTime}"
                                                        style="
                                                        font-size: 20px;
                                                        padding: 20px;
                                                        border: 4px solid;
                                                        cursor: pointer;
                                                        margin-bottom:20px;">

                                            </div>  
                                        </c:forEach>
                                    </div>



                                </form>


                                <h2 class="heading">DANH SÁCH RẠP</h2>
                                <c:set var="cinemas" value="${requestScope.CINEMA}"/>
                                <div class="container">
                                    <c:forEach items="${cinemas}" var="c">
                                        <p class="collapsible">${c.cinemaName}</p>
                                        <div class="content">
                                            <p class="addr">${c.address}</p>
                                        </c:forEach>
                                        <div class="body-list">
                                            <ul class="list-time">
                                                <c:set var="shows" value="${requestScope.showTimes}"/>
                                                <c:set var="show" value="${requestScope.show}"/>
                                                <c:forEach items="${shows}" var="time"> 
                                                    <form action="./LoadSeatServlet" method="POST">
                                                        <input type="hidden" name="allShows" value="${shows}"/>
                                                        <input type="hidden" name="movie" value="${movie.getMovieName()}"/>
                                                        <input type="hidden" name="lay" value="${show}"/>
                                                        <input type="hidden" name="cinema" value="${cinemas}"/>
                                                        <input type="hidden" name="roomID" value="${time.getRoomID()}"/>
                                                        <input type="hidden" name="showTimeID" value="${time.getShowTimeID()}"/>
                                                        <div class="item-time">

                                                            <input type="submit" id="time" name="detailTime" value="${time.getHourStart()}"
                                                                   style="
                                                                   font-size: 20px;
                                                                   padding: 20px;
                                                                   border: 4px solid;
                                                                   cursor: pointer;
                                                                   margin-bottom:20px;">
                                                        </div>
                                                    </form>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!--GHE NGOI-->
                            <c:set var="lockSeats" value="${requestScope.lockSeats}" />

                            <script>
                                document.addEventListener('DOMContentLoaded', function () {
                                    const seats2 = document.querySelectorAll('.seat');
                                    seats2.forEach(seat => {
                                        const seat_chosen = [
                                <c:forEach items="${lockSeats}" var="seat" varStatus="status">
                                        "${seat.getSeatID()}"
                                    <c:if test="${!status.last}">, </c:if>
                                </c:forEach>
                                        ];
                                        const seatId = seat.innerText;
                                        if (seat_chosen.includes(seatId)) {
                                            seat.style.backgroundColor = 'red';
                                            seat.classList.toggle('already-selected');
                                        }
                                    });
                                });
                            </script>
                            <div class="seat-choice">
                                <div class="television" style="text-align: center;">
                                    <h2>MÀN HÌNH</h2>
                                </div>
                                <c:set var="seats" value="${requestScope.ArraySeats}" />
                                <div class="seats">
                                    <div class="row">
                                        <c:forEach items="${seats}" var="s" varStatus="loop">
                                            <c:if test="${fn:startsWith(s.seatID, 'A')}">
                                                <div class="seat">${s.seatID}</div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="row">
                                        <c:forEach items="${seats}" var="s" varStatus="loop">
                                            <c:if test="${fn:startsWith(s.seatID, 'B')}">
                                                <div class="seat">${s.seatID}</div>
                                            </c:if>
                                        </c:forEach>
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
            <form action="./BookingServlet?movieName=${movie.getMovieName()}" method="POST">
                <div class="order-wrap">
                    <input type="hidden" name="uniqueShow" value="${requestScope.uniqueShow}">
                    <c:set var="timeDetail" value="${requestScope.detailTime}"/>

                    <div class="final-check">
                        <ul class="about-ticket">
                            <li>
                                <p class="caption">Ngày</p>

                                <p class="value" id="demo"><input readonly="readonly" type="text" name="showDate" value="${show}"></p>
                            </li>
                            <li>
                                <p class="caption">Suất chiếu</p>

                                <p class="value"><input readonly="readonly" type="text" name="showTime" value="${detailTime}"></p>
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
                    <input type="button" class="back-btn" value="QUAY LẠI" data-link="LoadAllMovieServlet?url=index2.jsp">
                </div>
            </form>
            <script src="js/booking.js"></script>
    </body>
</html>

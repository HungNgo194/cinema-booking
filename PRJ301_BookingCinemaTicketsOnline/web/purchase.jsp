<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

    <head>
        <title>Những cậu trai thân mật</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="images/logo.jpg" type="images/x-icon">
        <link rel="stylesheet" href="./css/purchase.css">
        <link rel="stylesheet" href="./css/member.css">
    </head>

    <body>
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
        <div class="final-content" style="display: block;">
            <form action="./PaymentServlet" id="frmCreateOrder" method="POST">
                <!--confirm-->
                <div class="final-confirm">

                    <p class="header-text">Cảm ơn quý khách đã đến với <strong>Những Cậu Trai Thân Mật</strong>!<br> Xin quý
                        khách vui lòng kiểm
                        tra lại
                        thông tin đặt vé </p>
                    <div class="confirm-cus">
                        <c:set var="user" value="${sessionScope.account}"/>
                        <p>Họ tên:<strong>${user.getUserName()}</strong></p>
                        <p>Email: <strong>${user.getEmail()}</strong></p>
                        <p>Phone: <strong>${user.getPhoneNumber()}</strong></p>
                    </div>
                    <c:set var="movie" value="${requestScope.MOVIE}"/>
                    <c:set var="date" value="${requestScope.SHOWDATE}"/>
                    <c:set var="time" value="${requestScope.SHOWTIME}"/>
                    <div class="confirm-box">
                        <div class="confirm-film">
                            <div class="confirm-film-pic" 
                                 style="float: left;
                                 gap:1em">

                                <img src="img/${movie.getMovieImage()}">
                                <input type="hidden" name="MOVIE" value="${movie.getMovieID()}">
                            </div>

                            <div class="confirm-film-text">
                                <div class="confirm-film-text" style="font-size: larger;">
                                    <h3>${movie.getMovieName()}</h3>
                                    <p>Ngày chiếu: <strong><input readonly="readonly" type="text" name="showDate" value="${date}"></strong></p>
                                    <p>Xuất chiếu: <strong><input readonly="readonly" type="text" name="showTime" value="${time}"></strong></p>
                                    <p><span class="icon-2d"></span></p>
                                </div>
                            </div>

                        </div>

                        <div class="confirm-ticket">
                            <div class="confirm-mark">Ghế</div>
                            <div class="confirm-">
                                <ul>                              
                                    <li>         
                                        <div class="each-ticket" style="width: 295px;">
                                            <c:set var="seats" value="${requestScope.availableSeats}" />
                                            <input type="hidden" name="seatAvailable" value="${requestScope.seatArray}"/>

                                            <c:forEach var="seat" items="${seats}">
                                                <div class="inside-each" ">
                                                    <div class="confirm-mark">
                                                        <input readonly="readonly" type="text" value="${seat}" style="
                                                               text-align: left;
                                                               font-size: larger;
                                                               border: none;
                                                               background-color: transparent;
                                                               outline: none;
                                                               color: inherit;
                                                               width: auto;
                                                               cursor: default;
                                                               ">
                                                    </div>
                                                    <c:set var="price" value="${requestScope.totalPrice}" />
                                                    <p class="value" style="float: left"><input readonly="readonly" type="text" value="${price}"

                                                                                                style="
                                                                                                text-align: left;
                                                                                                font-size: larger;
                                                                                                border: none;
                                                                                                background-color: transparent;
                                                                                                outline: none;
                                                                                                color: inherit;
                                                                                                width: 70px;
                                                                                                cursor: default;
                                                                                                "><sub>VNĐ</sub></p>
                                                    <br/>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="final-check">
                            <div class="confirm-total">
                                <div class="confirm-mark">Tổng tiền</div>
                                <div class="confirm-value"><p class="value">${requestScope.totalPaid}<sub>VNĐ</sub></p></div>
                            </div>
                            <div class="confirm-total-promotion">
                                <div class="confirm-mark">Số tiền giảm</div>
                                <div class="confirm-value">${requestScope.discountPortion}<sup>VNĐ</sup></div>
                            </div>
                            <div class="confirm-total-pay">
                                <div class="confirm-mark">Số tiền cần thanh toán</div>
                                <div class="confirm-value">${requestScope.priceAfterDiscount}<sup>VNĐ</sup></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="final-form">
                    <div class="payment_check">
                        <div style="font-size: larger;">
                            <input class="check_terms_condition" type="checkbox" value="Tôi bảo đảm mua vé xem phim này theo đúng độ tuổi quy định">
                            <span class="check_terms_condition_icon"></span>
                            Tôi bảo đảm mua vé xem phim này theo đúng độ tuổi quy định
                        </div>
                    </div>
                    <div class="payment_method" style="display: block;">
                        <div style="font-size: larger;">
                            <input type="radio" class="payment_method_radio" value="momo" name="payment_method">
                            <span class="payment_method_icon"></span> 
                            Thanh toán qua Thẻ Thanh Toán Quốc Tế (VISA)
                        </div>
                    </div>
                </div>

                <div class="input-but1">
                    <input type="hidden" name="amount" value="${requestScope.priceAfterDiscount}">
                    <input type="hidden" name="movies" value="${requestScope.MOVIE.movieID}"/>
                        <input type="hidden" name="uniqueShow" value="${requestScope.uniqueShow}">
                    <input type="button" class="cancel" value="Quay lại" id="payment-back" data-link="booking.jsp">
                    <input type="submit" class="ok" value="Thanh toán" id="payment-next">

                </div>
            </form>
        </div>
        <script src="./js/purchase.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
                return false;
            });
        </script>      
    </body>

</html>
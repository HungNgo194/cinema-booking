<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

    <head>
        <title>Những cậu trai thân mật</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


        <title>Những Cậu Trai Thân Mật</title>
        <link rel="icon" href="images/logo.jpg" type="images/x-icon">
        <link rel="stylesheet" href="./css/purchase.css">
        <link rel="stylesheet" href="./css/member.css">
    </head>

    <body>
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
        <div class="final-content" style="display: block;">
            <form action="./TicketServlet" method="POST">
                <!--confirm-->
                <div class="final-confirm">

                    <p class="header-text">Cảm ơn quý khách đã đến với <strong>Những Cậu Trai Thân Mật</strong> !<br> Xin quý
                        khách vui lòng kiểm
                        tra lại
                        thông tin đặt vé </p>
                    <div class="confirm-cus">
                        <p>Họ tên:<strong>An Dương</strong></p>
                        <p>Email: <strong>ansiucapcute@gmail.com</strong></p>
                        <p>Phone: <strong>0961792119</strong></p>
                    </div>
                    <div class="confirm-box">
                        <div class="confirm-film">
                            <div class="confirm-film-pic"><img
                                    src="https://cinestar.com.vn/pictures/Cinestar/11-2023/poster-nguoi-vo-cuoi-cung.jpg">
                            </div>

                            <div class="confirm-film-text">


                                <div class="confirm-film-text" style="font-size: larger;">
                                    <h3>NGƯỜI VỢ CUỐI CÙNG (T18)</h3>
                                    <p>Ngày chiếu: <strong>01-02-2024</strong></p>
                                    <p>Xuất chiếu: <strong>16:00</strong></p>
                                    <p><span class="icon-2d"></span></p>
                                </div>
                            </div>

                        </div>

                        <div class="confirm-ticket">
                            <div class="confirm-mark">Ghế</div>
                            <div class="confirm-">

                                <ul>                              
                                    <li  >         
                                        <div class="each-ticket" style="width: 295px;">
                                            <c:set var="seats" value="${sessionScope.availableSeats}" />
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

                                                    <c:set var="price" value="${sessionScope.totalPrice}" />

                                                    <!--                                                 Debug statements 
                                                                                                    <p>Total Price: ${price}</p>-->

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
                <!--confirm-->

                <div class="final-form">


                    <div class="payment_check">
                        <div style="font-size: larger;"><input class="check_terms_condition" type="checkbox" 
                                                               value="Tôi bảo đảm mua vé xem phim này theo đúng độ tuổi quy định"><span
                                                               class="check_terms_condition_icon"></span>Tôi bảo đảm mua vé xem phim này theo đúng độ tuổi quy định</div>
                        <!--<div><input class="check_terms_condition_1" type="checkbox" value="" /><span class="check_terms_condition_icon"></span>Tôi bảo đảm mua vé xem phim này theo đúng độ tuổi quy định</div>-->
                    </div>
                    <div class="payment_method" style="display: block;">
                        <div style="font-size: larger;"><input type="radio" class="payment_method_radio" value="momo" name="payment_method"><span
                                class="payment_method_icon"></span> Thanh toán qua ví MoMo</div>
                    </div>
                </div>

                <div class="input-but1">
                    <input type="button" class="cancel" value="Quay lại" id="payment-back" data-link="booking.jsp">
                    <input type="submit" class="ok" value="Thanh toán" id="payment-next" data-link="transaction.jsp">
                </div>
            </form>
        </div>
        <script src="./js/purchase.js"></script>

    </body>

</html>
<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Font -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600%7CUbuntu:300,400,500,700" rel="stylesheet">

        <!-- CSS -->
        <link rel="stylesheet" href="css/bootstrap-reboot.min.css">
        <link rel="stylesheet" href="css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <link rel="stylesheet" href="css/nouislider.min.css">
        <link rel="stylesheet" href="css/ionicons.min.css">
        <link rel="stylesheet" href="css/plyr.css">
        <link rel="stylesheet" href="css/photoswipe.css">
        <link rel="stylesheet" href="css/default-skin.css">
        <link rel="stylesheet" href="css/main.css">

        <!-- Favicons -->
        <link rel="icon" type="image/png" href="img/logo.jpg" sizes="32x32">
        <link rel="apple-touch-icon" href="icon/favicon-32x32.png">
        <link rel="apple-touch-icon" sizes="72x72" href="icon/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="icon/apple-touch-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="144x144" href="icon/apple-touch-icon-144x144.png">

        <meta name="description" content="">
        <meta name="keywords" content="">
        <meta name="author" content="Dmitry Volkov">
        <title>Những cậu trai thân mật</title>

    </head>

    <body class="body">
        <!-- header -->
        <header class="header">
            <div class="header__wrap">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="header__content">
                                <!-- header logo -->
                                <a href="index.html" class="header__logo">
                                    <img src="img/logo.jpg" alt="">
                                </a>
                                <!-- end header logo -->

                                <!-- header nav -->
                                <ul class="header__nav">
                                    <li class="header__nav-item">
                                        <a href="index2.html" class="header__nav-link">Phim</a>
                                    </li>
                                </ul>
                                <!-- end header nav -->

                                <!-- header auth -->
                                <div class="header__auth">
                                    <form action="#" class="header__search">
<div class="header__search-content">
<input type="text" placeholder="Tìm phim...">

                                            <button type="button">
                                                <i class="icon ion-ios-search"></i>
                                            </button>
                                        </div>
                                    </form>

                                    <div class="dropdown profile__dropdown-menu">
                                        <button class="profile__btn" type="button">
                                            <i class="icon ion-ios-person"></i>
                                        </button>

                                        <ul class="profile__nav">
                                            <li class="profile__nav-item">
                                                <a href="LogoutServlet" class="profile__nav-link">Log out</a>
                                            </li>

                                            <li class="profile__nav-item">
                                                <a href="LoadMemberShipServlet?action=show-profile"
                                                   class="profile__nav-link">Show Profile</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- end dropdown -->
                                </div>
                                <!-- end header auth -->

                                <!-- header menu btn -->
                                <button class="header__btn" type="button">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </button>
                                <!-- end header menu btn -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- end header -->

        <!-- profile -->
        <section class="profile">
            <div class="container">
                <div class="row">
                    <div class="col-12">                  
                        <!-- profile info -->
                        <div class="profile__info">
                            <h1 class="profile__title">Thông tin tài khoản</h1>
                            <c:forEach items="${requestScope.BookingInfo}" var="bookingInfo">
                            <div class="profile__content">
                                <ul>
                                    <li>${bookingInfo.getBookingID()}</li>
                                    <li>${bookingInfo.getNumberOfBooking()}</li>
                                    <li>${bookingInfo.getPriceTotal()}</li>
<li>${bookingInfo.getBookingDate()}</li>
                                    <li>${bookingInfo.getUserName()}</li>
</ul>
                            </div>
                            </c:forEach>
                        </div>
                        <!-- end profile info -->                    
                    </div>
                </div>
            </div>
        </section>
        <!-- end profile -->

        <!-- footer -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-sm-4 col-md-3">
                        <h6 class="footer__title">Liên hệ</h6>
                        <ul class="footer__list">
                            <li><a href="tel:+(điền số)">Số điện thoại : 1234567890</a></li>
                            <li><a href="#">Email : andmse182449@gmail.com</a></li>
                        </ul>
                    </div>

                    <!-- footer copyright -->
                    <div class="col-12">
                        <div class="footer__copyright">
                            <a href="#">Địa chỉ : Trường đại học công nghệ số 1 thế giới FPT, Việt Nam.</a>
                        </div>
                    </div>
                    <!-- end footer copyright -->
                </div>
        </footer>
        <!-- end footer -->

        <!-- JS -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.mousewheel.min.js"></script>
        <script src="js/jquery.mCustomScrollbar.min.js"></script>
        <script src="js/wNumb.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/plyr.min.js"></script>
        <script src="js/jquery.morelines.min.js"></script>
        <script src="js/photoswipe.min.js"></script>
        <script src="js/photoswipe-ui-default.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/jsCuaAn.js"></script>

        <!-- <div class="footer">
            <p>andmse182449@gmail.com</p>
            <p>Address: 100 Dien Bien Phu,District 3, HCMC</p>
            <h5>&copy; Copyright 2021. Chanel.com</h5>
        </div> -->
    </body>

</html>
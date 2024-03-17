<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Font -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600%7CUbuntu:300,400,500,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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

                                    <a href="signin.jsp" class="header__sign-in">
                                        <i class="icon ion-ios-log-in"></i>
                                        <span>Đăng nhập</span>
                                    </a>
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

        <!-- home -->
        <section class="home home--bg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h1 class="home__title">Phim đang chiếu</h1>

                        <button class="home__nav home__nav--prev" type="button">
                            <i class="icon ion-ios-arrow-round-back"></i>
                        </button>
                        <button class="home__nav home__nav--next" type="button">
                            <i class="icon ion-ios-arrow-round-forward"></i>
                        </button>
                    </div>

                    <div class="col-12">
                        <div class="owl-carousel home__carousel">

                            <!-- card -->
                            <c:set var="movies" value="${requestScope.MOVIES}"/>
                            <c:forEach items="${movies}" var="movie">
                                <div class="col-6 col-sm-12 col-lg-6">
                                    <div class="card card--list">
                                        <div class="row">
                                            <div class="">
                                                <div class="card__cover">
                                                    <a href="signin.jsp"> 
                                                        <img src="img/${movie.getMovieImage()}" alt="" style="height:300px; width: 200px;">
                                                    </a>
                                                </div>
                                            </div>

                                            <div class="">
                                                <div class="card__content">
                                                    <h3 class="card__title1" style="max-width: 200px;">
                                                        <a href="#">
                                                            <input readonly="readonly"
                                                                   type="text"
                                                                   name="movieName" 
                                                                   value="${movie.movieName}" 
                                                                   style="font-size: 20px;
                                                                   border: none;
                                                                   background-color: transparent;
                                                                   outline: none;
                                                                   color: inherit;
                                                                   cursor: default;
                                                                   height: 100px; 
                                                                   width: 200px;
                                                                   color: white;
                                                                   overflow-wrap: break-word; /* Áp dụng overflow-wrap cho input */
                                                                   ">
                                                        </a>
                                                    </h3>
                                                </div>
                                            </div>
                                                                   
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <!-- end card -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end home -->


<!-- footer -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-12 col-sm-4 col-md-3">
                <h6 class="footer__title">Liên hệ</h6>
                <ul class="footer__list">
                    <li><a href="tel:+(điền số)">Số điện thoại : 0961792119</a></li>
                    <li><a href="#">Email : andmse182449@gmail.com</a></li>
                    <a href="https://www.facebook.com/100034287818995/videos/1374263226490511" target="_blank">
                        <button style="font-size: 24px; background-color: #007bff; border: #007bff 1px solid; padding: 2px;">
                            <i class="fa fa-facebook-f" style="font-size: 30px; color: white;"></i>
                        </button>
                    </a>

                </ul>
            </div>

            <!-- footer copyright -->
            <div class="col-12">
                <div class="footer__copyright">
                    <a href="https://www.google.com/maps/search/?api=1&query=Trường%20đại%20học%20công%20nghệ%20số%201%20thế%20giới%20FPT%2C%20Việt%20Nam">Địa chỉ : Trường đại học công nghệ số 1 thế giới FPT, Việt Nam.</a>
                </div>
            </div>
            <!-- end footer copyright -->
        </div>
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
</body>

</html>
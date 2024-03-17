<%-- 
    Document   : index2
    Created on : Mar 7, 2024, 9:59:58 AM
    Author     : ROG STRIX
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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

                                    <a href="signin.html" class="header__sign-in">
                                        <i class="icon ion-ios-log-in"></i>
                                        <span>Đăng kí</span>
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

        <!-- content -->
        <section class="content">
            <div class="content__head">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="home__title">Phim đang chiếu</h1>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container">
                <!-- content tabs -->
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
                        <div class="row">
                            <!-- card -->
                            <c:set var="movies" value="${requestScope.MOVIES}"/>
                            <c:forEach items="${movies}" var="movie">
                                <div class="col-6 col-sm-12 col-lg-6">
                                    <div class="card card--list">
                                        <div class="row">
                                            <div class="col-12 col-sm-4">
                                                <div class="card__cover">
                                                    <img src="img/${movie.getMovieImage()}" alt="" style="
                                                         height:300px;
                                                         ">
                                                    <!--                                                    <a href="#" class="card__play">
                                                                                                            <i class="icon ion-ios-play"></i>
                                                                                                        </a>-->
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-8">
                                                <div class="card__content">
                                                    <h3 class="card__title">
                                                        <a href="#"><input readonly="readonly"
                                                                                       type="text"
                                                                                       name="movieName" 
                                                                                       value="${movie.movieName}" 
                                                                                       style=" 
                                                                                       font-size: larger;
                                                                                       border: none;
                                                                                       background-color: transparent;
                                                                                       outline: none;
                                                                                       color: inherit;
                                                                                       width: 350px;
                                                                                       cursor: default;
                                                                                       height: 100px; 
                                                                                       ">
                                                        </a>
                                                    </h3>

                                                    <div class="card__description">
                                                        <p>${movie.movieContent}
                                                        </p>
                                                    </div>


                                                    <form action="./LoadMovieServlet" method="POST">
                                                        <input type="hidden" name="button" value="${movie.movieName}">
                                                        <input class="card__ticket" type="submit" value="ĐẶT VÉ" style="">

                                                    </form>

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
                <!-- end content tabs -->
            </div>
        </section>
        <!-- end content -->

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
    </body>

</html>
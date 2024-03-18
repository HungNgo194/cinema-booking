<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>Admin</title>

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

                                <div class="header__auth">
                                  

                                    <!-- dropdown -->
                                    <div class="dropdown profile__dropdown-menu">
                                        <button class="profile__btn" type="button">
                                            <i class="icon ion-ios-person"></i>
                                        </button>

                                        <ul class="profile__nav">
                                            <li class="profile__nav-item">
                                                <a href="LogoutServlet" class="profile__nav-link">Log out</a>
                                            </li>

                                            <li class="profile__nav-item">
                                                <a href="404.html" class="profile__nav-link">404 Page</a>
                                            </li>

                                            <li class="profile__nav-item">
                                                <a href="UpdateProfileServlet?action=show-profile"
                                                   class="profile__nav-link">Show Profile</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- end dropdown -->
                                </div>
                                <!-- end header menu btn -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- end header -->

        <!-- admin home -->
        <section class="admin">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h1 class="home__title">Chào mừng bạn đến với trang Admin</h1>
                        <ul class="admin__nav">
                            <!-- admin item -->
                            <li class="admin__nav-item-title">
                                <a class="dropdown-toggle admin__nav-link" data-toggle="collapse" href="#movies"
                                   aria-expanded="false">
                                    <span class="menu-title">Phim</span>
                                </a>

                                <div class="collapse" id="movies">
                                    <ul class="nav flex-column sub-menu">
                                        <li class="admin__nav-item">
                                            <a class="admin__nav-link" href="addNewMovie-Admin.jsp">Thêm phim</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <!-- end admin item -->

                            <!-- admin item -->
                            <li class="admin__nav-item-title">
                                <a class="dropdown-toggle admin__nav-link" data-toggle="collapse" href="#showtimes"
                                   aria-expanded="false">
                                    <span class="menu-title">List of movie</span>
                                </a>

                                <div class="collapse" id="showtimes">
                                    <ul class="nav flex-column sub-menu">
                                        <li class="admin__nav-item">
                                            <a class="admin__nav-link" href="LoadAllMovieAdminServlet">List Of Movies</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <!-- end admin item -->
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <!-- end admin home -->

    </body>

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

</html>
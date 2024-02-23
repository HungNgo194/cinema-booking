<%-- 
    Document   : test
    Created on : Feb 20, 2024, 10:25:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nh?ng c?u trai thân m?t</title>
        <meta charset="UTF-8">
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
        <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
        <link rel="apple-touch-icon" href="icon/favicon-32x32.png">
        <link rel="apple-touch-icon" sizes="72x72" href="icon/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="icon/apple-touch-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="144x144" href="icon/apple-touch-icon-144x144.png">

        <meta name="description" content="">
        <meta name="keywords" content="">
        <meta name="author" content="Dmitry Volkov">
        <title>FlixGo ? Online Movies, TV Shows & Cinema HTML Template</title>
    </head>
    <body>

        <!-- HEADER -->
        <header class="header">
            <div class="header__wrap">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="header__content">
                                <!-- header logo -->
                                <a href="index.html" class="header__logo">
                                    <img src="img/logo.svg" alt="">
                                </a>
                                <!-- end header logo -->

                                <!-- header nav -->
                                <ul class="header__nav">
                                    <!-- dropdown -->
                                    <li class="header__nav-item">
                                        <a class="dropdown-toggle header__nav-link" href="#" role="button" id="dropdownMenuHome" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Home</a>

                                        <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuHome">
                                            <li><a href="index.html">Home slideshow bg</a></li>
                                            <li><a href="index2.html">Home static bg</a></li>
                                        </ul>
                                    </li>
                                    <!-- end dropdown -->

                                    <!-- dropdown -->
                                    <li class="header__nav-item">
                                        <a class="dropdown-toggle header__nav-link" href="#" role="button" id="dropdownMenuCatalog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Catalog</a>

                                        <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuCatalog">
                                            <li><a href="catalog1.html">Catalog Grid</a></li>
                                            <li><a href="catalog2.html">Catalog List</a></li>
                                            <li><a href="details1.html">Details Movie</a></li>
                                            <li><a href="details2.html">Details TV Series</a></li>
                                        </ul>
                                    </li>
                                    <!-- end dropdown -->

                                    <li class="header__nav-item">
                                        <a href="pricing.html" class="header__nav-link">Pricing Plan</a>
                                    </li>

                                    <li class="header__nav-item">
                                        <a href="faq.html" class="header__nav-link">Help</a>
                                    </li>

                                    <!-- dropdown -->
                                    <li class="dropdown header__nav-item">
                                        <a class="dropdown-toggle header__nav-link" href="#" role="button" id="dropdownMenuMore" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="icon ion-ios-more"></i></a>
                                        <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuMore">
                                            <li><a href="about.html">About</a></li>
                                            <li><a href="signin.jsp">Sign In</a></li>
                                            <li><a href="signup.jsp">Sign Up</a></li>
                                            <li><a href="404.html">404 Page</a></li>
                                        </ul>
                                    </li>
                                    <!-- end dropdown -->
                                </ul>
                                <!-- end header nav -->

                                <!-- header auth -->
                                <div class="header__auth">
                                    <button class="header__search-btn" type="button">
                                        <i class="icon ion-ios-search"></i>
                                    </button>
                                    <li class="dropdown header__nav-item">
                                        <a style=" border-radius: 100px/100px;
                                           background: white;
                                           padding: 20px;"
                                           class="dropdown-toggle header__nav-link" href="#" role="button" id="dropdownMenuMore" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa-solid fa-user"></i></a>
                                        <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuMore">
                                            <li><a href="LogoutServlet">Log out</a></li>
                                            <li><a href="signup.jsp">Receipt History</a></li>
                                            <li><a href="404.html">404 Page</a></li>
                                            <!--<li><a href="UpdateProfileServlet&?action=show-profile">Show Profile</a></li>-->
                                            <li><a href="LoadMemberShipServlet?action=show-profile">Show Profile</a></li>

                                        </ul>
                                    </li>
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

            <!-- header search -->
            <form action="#" class="header__search">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="header__search-content">
                                <input type="text" placeholder="Search for a movie, TV Series that you are looking for">

                                <button type="button">search</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- end header search -->
        </header>
    </body>
</html>

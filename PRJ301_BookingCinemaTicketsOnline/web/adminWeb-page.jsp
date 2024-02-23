<%-- 
    Document   : adminWeb-page
    Created on : Jan 30, 2024, 3:16:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN</title>
    </head>
    <body>
        <header class="header">
            <div class="header__wrap">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="header__content">
                                <!-- header logo -->
                                <a href="adminWeb-page.jsp" class="header__logo">
                                    <img src="img/logo.svg" alt="">
                                </a>
                                <!-- end header logo -->


                                <!-- end dropdown -->
                                <!-- end header nav -->

                                <!-- header auth -->
                                <div class="header__auth">
                                    <button class="header__search-btn" type="button">
                                        <i class="icon ion-ios-search"></i>
                                    </button>
                                    <li class="dropdown header__nav-item">
                                        <a style=" border-radius: 100px/100px;
                                           background: white;
                                           padding: 20px; "
                                           class="dropdown-toggle header__nav-link header__nav-link--more" href="#" role="button" id="dropdownMenuMore" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa-solid fa-user"></i></a>
                                        <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuMore">
                                            <li><a href="LogoutServlet">Log out</a></li>
                                            <li><a href="404.html">404 Page</a></li>
                                            <li><a href="UpdateProfileServlet?action=show-profile">Show Profile</a></li>
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
       

    <li style="margin-top: 10%; margin-left: 5%" class="header__nav-item">
        <a class="dropdown-toggle header__nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
            <i class="icon-disc menu-icon"></i>
            <span class="menu-title">Movies</span>
            <i class="menu-arrow"></i>
        </a>
        <div class="collapse" id="ui-basic">
            <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="addNewMovie-Admin.jsp">Add new Movie</a></li>
                <i class="fa-solid fa-user"></i>
                <li class="nav-item"> <a class="nav-link" href="modifyMovie-Admin.jsp">Modify Movie</a></li>
                <li class="nav-item"> <a class="nav-link" href="deleteMovie-Admin.jsp">Delete Movie</a></li>
            </ul>
        </div>
    </li>

    <li style="margin-top: 10px; margin-left: 5%"  class="header__nav-item">
        <a class="dropdown-toggle header__nav-link" data-toggle="collapse" href="#auth" aria-expanded="false" aria-controls="auth">
            <i class="icon-head menu-icon"></i>
            <span class="menu-title">Show Times</span>
            <i class="menu-arrow"></i>
        </a>
        <div class="collapse" id="auth">
            <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="addNewShowTime-Admin.jsp">Add new ShowTime</a></li>
                <i class="fa-solid fa-user"></i>
                <li class="nav-item"> <a class="nav-link" href="SearchShowTimeAdminServlet">Modify ShowTime</a></li>
            </ul>
        </div>
    </li>
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

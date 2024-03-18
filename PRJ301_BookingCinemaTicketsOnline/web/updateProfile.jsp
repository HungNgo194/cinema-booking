<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <c:set value="${sessionScope.account}" var="account" />
                        <!-- profile info -->
                        <div class="profile__info">
                            <h1 class="profile__title">Thông tin tài khoản</h1>

                            <div class="profile__content">
                                <ul>
                                    <li>Email: ${account.getUserName()}</li>
                                    <li>Họ và tên: ${account.fullName}</li>
                                    <li>Giới tính: ${account.gender}</li>
                                    <li>Số điện thoại: ${account.phoneNumber}</li>
                                </ul>
                            </div>
                        </div>
                        <!-- end profile info -->

                        <!-- profile info -->
                        <div class="profile__info">
                            <h1 class="profile__title">Hoạt động với rạp</h1>
                            <div class="body2 col-md-6">
                                <p><b>Loại Thẻ:</b></p>
                                <c:set var="sessionMember" value="${sessionScope.sessionMember}" />
                                <c:choose>
                                    <c:when test="${sessionMember.getTotalSpend() > 5000}">
                                        <img style="height: 200px; width: 200px" src="images/membershipcard.jpg"
                                             alt="Image 1" />
                                    </c:when>
                                    <c:otherwise>
                                        <img src="image2.jpg" alt="Image 2" />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <div class="profile__line">
                            <div class="line"></div>
                            <div class="line1"></div>
                            <div class="profile__modify-user">
                                <a style="display: inline-block;" onclick="openForm()" id="modify-hide">Thay đổi thông
                                    tin</a>
                            </div>
                        </div>
                        <!-- end profile info -->

                        <!-- profile info -->
                        <div class="profile__info">
                            <div class="user-center">
                                <div class="user-form" id="myForm">
                                    <h1 class="profile__title">Chỉnh sửa thông tin cá nhân</h1>
                                    <form action="./UpdateProfileServlet" method="post" id="modify-top"
                                          data-gtm-form-interact-id="5">
                                        <div class="profile__info__content">

                                            <div class="sign__group">
                                                <!-- <span class="focus-text hide">EMAIL (*)</span> -->
                                                <input class="sign__input" readonly="readonly" id="update_email"
                                                       name="email" type="text" value="${account.email}"
                                                       placeholder="EMAIL (*)">
                                            </div>

                                            <div class="sign__group">
                                                <div class="input-text name">
                                                    <!-- <span class="focus-text hide">HỌ TÊN (*)</span> -->
                                                    <input class="sign__input" required="true" id="update_name"
                                                           name="fullName" type="text" placeholder="HỌ TÊN">
                                                </div>
                                                <!-- <div class="input-text birth">
                                                    <span class="focus-text hide">DD/MM/YYYY ( Ngày sinh*)</span> 
                                                    <input id="update_birthday" name="birthday" type="text" value=""
                                                        placeholder="DD/MM/YYYY ( Ngày sinh*)">
                                                </div>-->
                                            </div>

                                            <div class="sign__group">
                                                <div class="input-text tel">
                                                    <!-- <span class="focus-text hide">ĐIỆN THOẠI (*)</span> -->
                                                    <input class="sign__input" required="true" id="update_phone"
                                                           name="phoneNumber" type="text" placeholder="ĐIỆN THOẠI">
                                                </div>
                                            </div>

                                            <div>
                                                <div class="sign__group sign__group--checkbox">
                                                    <input id="nam" name="gender" type="checkbox" value="nam">
                                                    <label class="nam" for="nam">Nam</label>

                                                    <input id="nu" name="gender" type="checkbox" value="nu">
                                                    <label class="nu" for="nu">Nữ</label>
                                                </div>
                                            </div>

                                            <div class="user__btn__container">
                                                <div class="user__btn__item">
                                                    <input type="button" class="modify-user-hide" value="Đóng"
                                                           onclick="closeForm()" id="">
                                                </div>

                                                <div class="user__btn__item">
                                                    <input type="submit" value="Lưu" id="btn-save-top">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- end profile info -->
                        <div class="profile__view">
                            <form action="BookingInfoServlet" method="get">
                                <input type="submit" value="View Profile">
                            </form>
                        </div>
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
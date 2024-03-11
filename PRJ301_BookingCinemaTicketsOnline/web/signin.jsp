
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <style>
        div.google span {
            background-image: -webkit-linear-gradient(0deg, #ff55a5 0%, #ff5860 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            color: #ff5860;
            opacity: 0.8;
        }
    </style>
    <body class="body">
        <div class="sign section--bg" data-bg="img/section/section.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="sign__content"> 
                            <!-- authorization form -->
                            <form name="form" action="./LoginActionServlet" method="post" class="sign__form" onsubmit="return validLogin();"> 
                                <a href="index.html" class="sign__logo">
                                    <img src="img/logo.jpg" alt="">
                                </a>

                                <div class="sign__group">
                                    <input required="true" type="text" name="email" class="sign__input" placeholder="Username">
                                </div>

                                <div class="sign__group">
                                    <input required="true" type="password" name="password" class="sign__input" placeholder="Password">
                                </div>

                                <div class="sign__group sign__group--checkbox">
                                    <input id="remember" name="remember" type="checkbox" checked="checked">
                                    <label for="remember">Hãy nhớ tôi</label>
                                </div>

                                <input class="sign__btn" type="submit" value="Sign in" onclick="">

                                <span class="sign__text">Không có tài khoản? <a href="signup.jsp">Đăng ký!</a></span>

                                <span class="sign__text"><a href="sendEmail.jsp">Quên mật khẩu?</a></span>

                                <div style="height: 20px; margin: 10px" class="google">
                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/PRJ301_BookingCinemaTicketsOnline/GoogleLoginServlet&response_type=code
                                       &client_id=996005212204-os62utcnpk9msouuvestcal1j5helkfs.apps.googleusercontent.com&approval_prompt=force" class="field google">
                                        <img style="width:30px;height:30px;" src="images/Google__G__logo.svg.png" alt="" class="google-img">
                                        <span >Đăng nhập bằng Google</span>
                                    </a>
                                </div>
                                <!--                                    <span class="d-block text-center my-4 text-muted">Not registered?<a
                                                                            href="register-page.jsp" style="color: #3987ad">create new
                                                                            account</a></span>-->
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


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
    <script>
                                function validLogin() {
                                    if (document.form.email.value == "") {
                                        alert("Please enter Login Name.");
                                        document.loginform.email.focus();
                                        return false;
                                    }
                                    if (document.form.password.value == "") {
                                        alert("Please enter password.");
                                        document.userform.password.focus();
                                        return false;
                                    }
//                                        alert("Welcome User");
//                                        return true;
                                }
    </script>
</html>

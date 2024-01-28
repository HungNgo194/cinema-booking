<%-- 
    Document   : loginGoogle
    Created on : Jan 26, 2024, 12:06:36 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="css/stylesheet.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/3.5.0/remixicon.css">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.html">tạm thời là nút trở về tại lười chỉnh</a>
        <section class="form container">
            <div class="form login">

                <!-- LOGIN -->
                <div class="form content">
                    <header class="form-header">ĐĂNG NHẬP</header>

                    <form action="#">
                        <div class="field input-field">
                            <input type="email" class="input" placeholder="Nhập Email">
                        </div>

                        <div class="field input-field">
                            <input type="password" class="input" placeholder="Nhập mật khẩu">
                            <i class="ri-eye-off-line eye-icon"></i>
                        </div>

                        <div class="form-link">
                            <a href="#" class="forgot-pass">Quên mật khẩu????</a>
                        </div>

                        <div class="field button-field">
                            <button>Đăng nhập đi boi</button>
                        </div>

                        <div class="form-link">
                            <span>Đã có tài khoản???????
                                <a href="#" class="signup-link">Đăng ký nèeeeeeee</a>
                            </span>
                        </div>
                    </form>
                </div>

                <div class="line"></div>

                <!-- GOOGLE LOGIN -->
                <div class="form-google">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/PRJ301_BookingCinemaTicketsOnline/GoogleLoginServlet&response_type=code
                       &client_id=996005212204-os62utcnpk9msouuvestcal1j5helkfs.apps.googleusercontent.com&approval_prompt=force" class="field google">
                        <img src="images/Google__G__logo.svg.png" alt="" class="google-img">
                        <span>Đăng nhập bằng Google</span>
                    </a>
                </div>
                <span class="d-block text-center my-4 text-muted">Not registered?<a
                        href="register-page.jsp" style="color: #3987ad">create new
                        account</a></span>
            </div>
        </section>

        <script src="template-login/js/jquery-3.3.1.min.js"></script>
        <script src="template-login/js/popper.min.js"></script>
        <script src="template-login/js/bootstrap.min.js"></script>
        <script src="template-login/js/main.js"></script>
    </body>
</html>

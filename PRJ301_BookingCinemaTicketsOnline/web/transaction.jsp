<%-- 
    Document   : transaction
    Created on : Mar 12, 2024, 9:21:20 AM
    Author     : ROG STRIX
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                margin: 0;
                padding: 0;
            }
            .container-sec {
                margin-top: 300px;
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            }
            h1 {
                color: #333;
            }
            .message {
                margin-top: 20px;
                font-size: 18px;
                color: #4CAF50;
            }
            .detailly {
                margin-top: 20px;
                text-align: left;
            }
            .detailly p {
                margin: 5px 0;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <div class="header__wrap">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="header__content">
                                <!-- header logo -->
                                <a href="LoadAllMovieServlet?url=userWeb-page.jsp" class="header__logo">
                                    <img src="images/logo.jpg" alt="">
                                </a>

                                
                                <ul class="header__nav">
                                    <li class="header__nav-item">
                                        <a href="LoadAllMovieServlet?url=index2.jsp" class="header__nav-link">Phim</a>
                                    </li>
                                </ul>
                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        
        <div class="container-sec" style="margin-top: 300px;">
            <h1>${requestScope.title}</h1>
            <div class="message">
                <p>${requestScope.message}</p>
            </div>
            <div class="detailly">
                <c:set var="b" value="${sessionScope.booking}"/>
                <c:set var="money" value="${requestScope.totalPaid}"/>
                <p><strong>Booking ID: </strong>${b.getBookingID()}</p>
                <p><strong>Amount Paid: </strong>${money}VNĐ</p>
                <h5><strong>${requestScope.ms1}</strong></h5>
                <p>For any queries, please contact our customer support.</p>
            </div>
        </div>
    </body>
</html>

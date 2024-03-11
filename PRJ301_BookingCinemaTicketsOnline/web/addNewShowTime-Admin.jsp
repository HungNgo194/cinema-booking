<%-- 
    Document   : addNewShowTime-Admin
    Created on : Feb 9, 2024, 6:31:42 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="showTime.ShowTimeDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Show Time</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: flex-start;
                height: 100vh;
                margin: 0;
            }
            .container {
                width: 50%;
                margin: 0 auto;
                padding: 20px;
            }

            h1 {
                font-size: 35px; 
                color: #333; 
                margin-bottom: 20px; 
                text-align: center; 
            }

            .movie-add input {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                box-sizing: border-box;
            }

            .check-button {
                text-align: center;
            }

            .check-button input[type="submit"], .check-button input[type="button"] {
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .check-button input[type="submit"]:hover, .check-button input[type="button"]:hover {
                background-color: #0056b3;
            }

            .check-button a input[type="button"] {
                background-color: #dc3545;
            }
            .check-button a input[type="button"]:hover {
                background-color: #c82333;
            }
            .java-code-messages {
                margin-top: 10px;
            }
            .java-code-messages p {
                margin: 5px 0; 
            }
            .java-code-messages p:nth-child(odd) {
                color: red;
            }
        </style>
    </head>
    <body>
        <c:set value="${requestScope.movieByID}" var="movieByID" />
        <div class="container">
            <form action="AddNewShowTimeAdminServlet" method="post">
                <h1>Khởi Tạo Xuất Chiếu Mới</h1>
                <div class="movie-add">
                    <input type="hidden" required="true" name="showTimeID" placeholder="showTimeID"><br>
                    <input type="date" required="true" name="openDate" placeholder="openDate"><br>
                    <input type="date" required="true" name="closeDate" placeholder="closeDate"><br>
                    <input type="text" required="true" name="director" placeholder="director"><br>
                    <input type="time" required="true" name="hourStart" placeholder="hourStart"><br>
                    <input type="time" required="true" name="hourEnd" placeholder="hourEnd"><br>
                    <input type="number" required="true" name="roomID" placeholder="roomID"><br>
                    <input type="number" readonly value="${movieByID.movieID}" name="movieID" placeholder="movieID"><br>
                </div>
                <div class="check-button">
                    <a href="LoadAllMovieAdminServlet"><input type="button" name="" value="Quay Lại"></a>
                    <input type="submit" name="" value="Xác Nhận">
                </div>
                <%-- Check if timeFormatError attribute is present --%>
                <c:set value="${requestScope.timeFormatError}" var="timeFormatError" />
                <c:set value="${requestScope.dateFormatError}" var="dateFormatError" />
                <c:set value="${requestScope.notExist}" var="notExist" />
                <div class="java-code">
                    <% String notExist = (String) request.getAttribute("notExist");
                        if (notExist != null) {
                    %>
                    <p style="font-weight: bold; color: red" class="error-message">${notExist}</p>
                    <%
                        }
                    %>
                    <div class="java-code-messages">
                        <% String timeFormatError = (String) request.getAttribute("timeFormatError");
                            if (timeFormatError != null) {
                        %>
                        <p style="color: red">${timeFormatError}</p>
                        <p style="color: red">${dateFormatError}</p>
                        <% } %>
                        <% Boolean openDateAfterCloseDate = (Boolean) request.getAttribute("openDateAfterCloseDate");
                            if (Boolean.TRUE.equals(openDateAfterCloseDate)) {
                        %>
                        <p style="font-weight: bold; color: red">Không khả thi !</p>
                        <p style="color: red">Ngày bắt đầu không thể sau ngày kết thúc phim</p>
                        <% }
                            ShowTimeDTO existingShowTime = (ShowTimeDTO) request.getAttribute("existingShowTime");
                            if (existingShowTime != null) {
                        %>
                        <p style="font-weight: bold; color: red">There is an overlap with an existing show time:</p>
                        <p style="color: red">Start Time: <%= existingShowTime.getHourStart()%></p>
                        <p style="color: red">End Time: <%= existingShowTime.getHourEnd()%></p>
                        <% }
                            Boolean hourStartAfterHourEnd = (Boolean) request.getAttribute("hourStartAfterHourEnd");
                            if (Boolean.TRUE.equals(hourStartAfterHourEnd)) {
                        %>
                        <p style="font-weight: bold;">Không khả thi !</p>
                        <p style="color: red">Giờ bắt đầu không thể sau giờ kết thúc phim</p>
                        <% }
                            Boolean showTime = (Boolean) request.getAttribute("showTime");
                            if (Boolean.TRUE.equals(showTime)) {
                        %>
                        <p style="font-weight: bold; color: green">Thêm Xuất Chiếu Mới Thành Công !</p>
                        <% }
                        %>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>


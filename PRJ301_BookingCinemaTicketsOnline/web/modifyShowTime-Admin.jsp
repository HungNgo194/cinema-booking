<%-- 
    Document   : modifyShowTime-Admin
    Created on : Feb 9, 2024, 6:31:54 PM
    Author     : Admin
--%>

<%@page import="showTime.ShowTimeDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Show Time</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f9f9f9;
            }

            .container {
                max-width: 800px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .table-container {
                margin-bottom: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th, td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }

            .form-container {
                text-align: center; 
                margin-top: 20px; 
            }

            .button-container {
                margin-top: 10px;
            }

            .form-container input[type="submit"] {
                padding: 12px 24px; 
                background-color: #1aafff; 
                color: #fff; 
                border: none; 
                border-radius: 5px; 
                cursor: pointer; 
                font-size: 16px; 
                font-weight: bold; 
                text-transform: uppercase; 
                transition: background-color 0.3s ease;
                margin-top: 10px; 
                margin-bottom: 50px;
            }

            .form-container input[type="submit"]:hover {
                background-color: #0056b3;
            }

            .form-container h1 {
                font-size: 35px; 
                color: #333; 
                margin-bottom: 20px; 
                text-align: center; 
            }

            .error-message {
                color: red;
                text-align: center;
            }

            .success-message {
                color: green;
                text-align: center;
            }

            label {
                display: inline-block;
                margin-right: 10px;
            }

            input[type="radio"] {
                display: none;
            }

            input[type="radio"]:checked + span {
                background-color: #007bff;
                color: #1aafff;
            }
            .movie-add {
                max-width: 400px;
                margin: 0 auto; 
            }

            .movie-add h1 {
                font-size: 35px;
                color: #333;
                margin-bottom: 20px;
                text-align: center;
            }

            .movie-add input {
                width: 100%; 
                height: 40px; 
                margin-bottom: 10px;
            }

            .check-button {
                text-align: center; 

            }

            .check-button input[type="submit"],
            .check-button input[type="button"] {
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .check-button a input[type="button"] {
                background-color: #dc3545;
            }

            .check-button a input[type="button"]:hover {
                background-color: #c82333;
            }

            label {
                display: block; 
                margin-bottom: 10px; 
            }

            span {
                display: inline-block;
                padding: 10px 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="radio"] {
                display: none;
            }

            input[type="radio"]:checked + span {
                background-color: #1aafff;
                color: #fff;
            }

        </style>
    </head>
    <body>
        <c:set value="${requestScope.getShowTimeByID}" var="getShowTimeByID" />

        <form action="ModifyShowTimeAdminServlet" method="post">  
            <div class="movie-add">
                    <h1>Chỉnh Sửa Thông Tin Xuất Chiếu</h1>
                    <input readonly value="${getShowTimeByID.showTimeID}" required="true" name="showTimeID" placeholder="ID xuất chiếu"><br>
                    <input type="date" required="true" name="openDate" placeholder="Ngày bắt đầu"><br>
                    <input type="date" required="true" name="closeDate" placeholder="Ngày kết thúc"><br>
                    <input type="text" required="true" name="director" placeholder="Các diễn viên"><br>
                    <input type="time" required="true" name="hourStart" placeholder="Giờ bắt đầu"><br>
                    <input type="time" required="true" name="hourEnd" placeholder="Giờ kết thúc"><br>
                    <input readonly value="${getShowTimeByID.roomID}" required="true" name="roomID" placeholder="ID phòng chiếu"><br>
                    <input readonly value="${getShowTimeByID.movieID}" required="true" name="movieID" placeholder="ID phim"><br>
                    <br>
                    Tình Trạng Phim:
                    <label style="text-align: center"><input type="radio" name="showStatus" value="Exist" checked=""><span>Tồn tại</span></label>
                    <label style="text-align: center"><input type="radio" name="showStatus" value="Not exist"><span>Không tồn tại</span></label>
            </div>
            <br>
            <div class="check-button">
                <a href="adminWeb-page.jsp"><input type="button" name="" value="Quay Lại"></a>
                <input type="submit" name="" value="Xác Nhận">
            </div>
            <%-- Check if timeFormatError attribute is present --%>
            <c:set value="${requestScope.seatDetailDTO}" var="seatDetailDTO" />
            <c:set value="${requestScope.timeFormatError}" var="timeFormatError" />
            <c:set value="${requestScope.dateFormatError}" var="dateFormatError" />
            <c:set value="${requestScope.notExist}" var="notExist" />
            <div class="java-code">
                <% String seatDetailDTO = (String) request.getAttribute("seatDetailDTO");
                    if (seatDetailDTO != null) {
                %>
                <p style="font-weight: bold; color: red" class="error-message">${seatDetailDTO}</p>
                <%
                    }
                %>
                <% String notExist = (String) request.getAttribute("notExist");
                    if (notExist != null) {
                %>
                <p style="font-weight: bold; color: red" class="error-message">${notExist}</p>
                <%
                    }
                %>
                <% String timeFormatError = (String) request.getAttribute("timeFormatError");
                    if (timeFormatError != null) {
                %>
                <p style="font-weight: bold" class="error-message">${timeFormatError}</p>
                <p style="font-weight: bold" class="error-message">${dateFormatError}</p>
                <%
                    }
                %>
                <% Boolean openDateAfterCloseDate = (Boolean) request.getAttribute("openDateAfterCloseDate");
                    if (Boolean.TRUE.equals(openDateAfterCloseDate)) { %>
                <p style="font-weight: bold; color: red" class="error-message">Không Khả Thi !</p>
                <p class="error-message">Ngày bắt đầu không thể diễn ra sau ngày kết thúc phim</p>
                <%
                    }
                    ShowTimeDTO existingShowTime = (ShowTimeDTO) request.getAttribute("existingShowTime");
                    if (existingShowTime != null) {
                %>
                <p style="font-weight: bold; color: red" class="error-message">There is an overlap with an existing show time:</p>
                <p style="color: red">Start Time: <%= existingShowTime.getHourStart()%></p>
                <p style="color: red">End Time: <%= existingShowTime.getHourEnd()%></p>
                <%
                    }
                    Boolean hourStartAfterHourEnd = (Boolean) request.getAttribute("hourStartAfterHourEnd");
                    if (Boolean.TRUE.equals(hourStartAfterHourEnd)) {
                %>
                <p style="font-weight: bold; color: red" class="error-message">Không Khả Thi !</p>
                <p class="error-message">Giờ bắt đầu không thể diễn ra sau giờ kết thúc phim</p>m
                <%
                    }
                    Boolean showTime = (Boolean) request.getAttribute("showTime");
                    if (Boolean.TRUE.equals(showTime)) {
                %>
                <p style="font-weight: bold" class="success-message">Thay Đổi Xuất Chiếu Thành Công !</p>
                <%
                    }
                %>
            </div>
        </form>
    </body>
</html>

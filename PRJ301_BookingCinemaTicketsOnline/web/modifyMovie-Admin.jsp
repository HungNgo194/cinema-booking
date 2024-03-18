<%-- 
    Document   : modifyMovie
    Created on : Feb 5, 2024, 10:19:58 PM
    Author     : Admin
--%>

<%@page import="movie.MovieDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Movie</title>
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
            .search-form {
                text-align: center;
                margin-top: 20px;
            }

            .search-form input[type="text"] {
                width: 50%;
                padding: 10px;
                margin-bottom: 10px;
                box-sizing: border-box;
            }

            .search-form input[type="submit"] {
                padding: 10px 20px;
                background-color: #1aafff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .search-form input[type="submit"]:hover {
                background-color: #0056b3;
            }

            .movie-add {
                text-align: center;
                margin-top: 20px;
            }

            .movie-add input[type="text"],
            .movie-add input[type="number"],
            .movie-add input[type="file"] {
                width: 50%;
                padding: 10px;
                margin-bottom: 10px;
                box-sizing: border-box;
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
                margin-top: 10px;
            }

            .check-button input[type="submit"]:hover,
            .check-button input[type="button"]:hover {
                background-color: #0056b3;
            }

            .check-button a {
                text-decoration: none;
            }

            .check-button a input[type="button"] {
                background-color: #dc3545;
            }

            .check-button a input[type="button"]:hover {
                background-color: #c82333;
            }
            .form-container h1 {
                font-size: 35px; 
                color: #333; 
                margin-bottom: 20px; 
                text-align: center; 
            }
            .error-message {
                font-weight: bold;
                color: red;
                text-align: center;
            }

            .success-message {
                font-weight: bold;
                color: green;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <c:set value="${requestScope.movieByID}" var="movieByID" />
        <form action="ModifyMovieAdminServlet" enctype="multipart/form-data" method="POST">
            <div class="movie-add">
                <h1>Thay Đổi Thông Tin Phim</h1>
                <input readonly value="${movieByID.movieID}" required="true" type="text" name="movieID" placeholder="ID phim"><br>
                <input required="true" type="text" name="movieName" placeholder="Tên phim" value=""><br>
                <input required="true" type="text" name="movieContent" placeholder="Nội ung phim"value=""><br>
                <input required="true" type="text" name="actor" placeholder="Các diễn viên"value=""><br>
                <input required="true" type="text" name="director" placeholder="Đạo Diễn"value=""><br>
                <input required="true" type="number" name="age" placeholder="Giới hạn tuổi"value=""><br>
                <input required="true" type="text" name="movieImage" value="" placeholder="Tên hình ảnh phim"/><br>
                <input required="true" type="file" name="file" accept="image/png, image/jpg"/><br>
            </div>
            <div class="check-button">
                <a href="LoadAllMovieAdminServlet"><input type="button" name="" value="Quay Lại"></a>
                <input type="submit" name="" value="Xác Nhận">
            </div>
        </form>
        <c:set value="${requestScope.ERRORS}" var="errors" />

        <%
            MovieDTO existingMovie = (MovieDTO) request.getAttribute("existingMovie");
            Boolean modifyMovie = (Boolean) request.getAttribute("modifyMovie");

            if (existingMovie == null) {
        %>
        <p class="error-message">${errors}</p>
        <%
            }
            if (Boolean.TRUE.equals(modifyMovie)) {
        %>
        <p class="success-message">Thay đổi thông tin phim thành công !</p>
        <%
            }
        %>

    </body>
</html>

<%-- 
    Document   : addNewMovie-Admin
    Created on : Feb 2, 2024, 12:35:36 PM
    Author     : Admin
--%>

<%@page import="movie.MovieDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Movie</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: flex-start; /* Align items to the top */
                height: 100vh;
                margin: 0;
            }

            .container {
                width: 70%;
            }

            .movie-add {
                text-align: center; /* Center the input fields */
            }

            .movie-add input[type="text"],
            .movie-add input[type="number"],
            .movie-add input[type="file"] {
                width: 100%;
                padding: 10px;
                margin: 0 auto; /* Center the input fields */
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
            .message {
                color: #155724;
                background-color: #d4edda;
                border-color: #c3e6cb;
                padding: 10px;
                margin-top: 10px;
                border: 1px solid transparent;
                border-radius: .25rem;
            }

            .error-message {
                color: #721c24;
                background-color: #f8d7da;
                border-color: #f5c6cb;
                padding: 10px;
                margin-top: 10px;
                border: 1px solid transparent;
                border-radius: .25rem;
            }

            h1 {
                margin-top: 50px;
                font-size: 35px; 
                color: #333; 
                margin-bottom: 20px; 
                text-align: center; 
            }
            .message-container {
                text-align: center; /* Center the content horizontally */
                position: absolute; /* Position the container absolutely */
                bottom: 20px; /* Set bottom margin */
                left: 50%; /* Center the container horizontally */
                transform: translateX(-50%); /* Adjust to center precisely */
            }

            .message-container p {
                margin: 10px 0; /* Add vertical margin to separate messages */
            }


        </style>
    </head>
    <body>
    <body>
        <form action="AddNewMovieAdminServlet" enctype="multipart/form-data" method="post">
            <div class="movie-add">
                <h1>Khởi Tạo Bộ Phim Mới</h1>
                <input required="true" type="text" name="movieName" placeholder="Tên"><br>
                <input required="true" type="text" name="movieContent" placeholder="Nội Dung"><br>
                <input required="true" type="text" name="actor" placeholder="Diễn viên"><br>
                <input required="true" type="text" name="director" placeholder="Đạo Diễn"><br>
                <input required="true" type="number" name="age" placeholder="Giới hạn tuổi"><br>
                <input required="true" type="text" name="movieImage" value="" placeholder="Tên hình ảnh"/><br>
                <input required="true" type="file" name="file" accept="image/png, image/jpg"/><br>
            </div>
            <div class="check-button">
                <a href="adminWeb-page.jsp"><input type="button" name="" value="Quay Lại"></a>
                <input type="submit" name="" value="Xác Nhận">
                <%
                    MovieDTO existingMovie = (MovieDTO) request.getAttribute("existingMovie");
                    if (existingMovie != null && !existingMovie.equals("")) {
                %>
                <p style="font-weight: bold; color: red">Phim đã tồn tại, nhập lại</p>
                <%
                    }
                    Boolean addNewMovie = (Boolean) request.getAttribute("addNewMovie");
                    if (Boolean.TRUE.equals(addNewMovie)) {
                %>
                <p style="font-weight: bold; color: green">Khởi tạo phim thành công !</p>
                <%
                    }
                %>
            </div>
        </form>
    </body>
</html>

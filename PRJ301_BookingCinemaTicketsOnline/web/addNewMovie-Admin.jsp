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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .movie-add{
                text-align: left;
                margin-left: 20px;

            }
            .movie-add input{
                margin-top: 10px;
                width: 500px;
                height: 30px;
            }
            .check-button input{
                margin-top:10px;
            }
            p {
                color: red;
            }
        </style>
    </head>
    <body>
        <form action="AddNewMovieAdminServlet" method="post">
            <div class="movie-add">
                <input required="true" type="text" name="movieName" placeholder="Tên"><br>
                <input required="true" type="text" name="movieContent" placeholder="Nội Dung"><br>
                <input required="true" type="text" name="actor" placeholder="Diễn viên"><br>
                <input required="true" type="text" name="director" placeholder="Đạo Diễn"><br>
                <input required="true" type="number" name="age" placeholder="Giới hạn tuổi"><br>
            </div>
            <div class="check-button">
                <a href="adminWeb-page.jsp"><input type="button" name="" value="Quay Lại"></a>
                <input type="submit" name="" value="Xác Nhận">
            </div>
            <%
                MovieDTO existingMovie = (MovieDTO) request.getAttribute("existingMovie");
                if (existingMovie != null && !existingMovie.equals("")) {
            %>
            <p>Phim đã tồn tại, nhập lại</p>
            <%
                }
                Boolean movieCreated = (Boolean) request.getAttribute("movieCreated");
                if (Boolean.TRUE.equals(movieCreated)) {
            %>
            <p>Successfully added new movie!</p>
            <%
                }
            %>
        </form>

    </body>
</html>

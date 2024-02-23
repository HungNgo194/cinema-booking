<%-- 
    Document   : deleteMovie-Admin.jsp
    Created on : Feb 6, 2024, 11:06:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
        </style>

        <form action="DeleteMovieAdminServlet" method="POST">
            <div class="movie-add">
                <!--                <input readonly="readonly" name="movieID" value=""><br>-->
                <input name="movieName" placeholder="Tên"><br>
            </div>
            <div class="check-button">
                <a href="adminWeb-page.jsp"><input type="button" name="" value="Quay Lại"></a>
                <input type="submit" name="" value="Xác Nhận">
            </div>
        </form>
    </body>
</html>

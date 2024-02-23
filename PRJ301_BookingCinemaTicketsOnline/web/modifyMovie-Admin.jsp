<%-- 
    Document   : modifyMovie
    Created on : Feb 5, 2024, 10:19:58 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        </style>
    </head>
    <body>

        <form action="SearchMovieAdminServlet" method="POST">
            <input type="text" name="movieName" value="${requestScope.searchKey}" placeholder="Search name of movie ">
            <input  type="submit" value="Search">
        </form>
        <table border="1px solid black">
            <tr>
                <!--                <td>movieID</td>-->
                <td>movieName</td>
                <td>movieContent</td>
                <td>actor</td>
                <td>director</td>
                <td>age</td>
            </tr>

            <c:forEach items="${requestScope.list}" var="movie">
                <tr>
<!--                    <td>${movie.getMovieID()}</td>-->
                    <td>${movie.getMovieName()}</td>
                    <td>${movie.getMovieContent()}</td>
                    <td>${movie.getActor()}</td>
                    <td>${movie.getDirector()}</td>
                    <td>${movie.getAge()}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="ModifyMovieAdminServlet" method="POST">
            <div class="movie-add">
<!--                <input readonly="readonly" name="movieID" value=""><br>-->
                <input name="movieName" placeholder="Tên"><br>
                <input name="movieContent" placeholder="Nội Dung"><br>
                <input name="actor" placeholder="Diễn viên"><br>
                <input name="director" placeholder="Đạo Diễn"><br>
                <input name="age" placeholder="Giới hạn tuổi"><br>
            </div>
            <div class="check-button">
                <a href="adminWeb-page.jsp"><input type="button" name="" value="Quay Lại"></a>
                <input type="submit" name="" value="Xác Nhận">
            </div>
        </form>
    </body>
</html>

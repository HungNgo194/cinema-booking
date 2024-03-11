
<%@page import="movie.MovieDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
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
        <div class="form-container">
            <h1>Danh Sách Phim</h1>
            <table border="1px solid black">
                <tr>
                    <th>movieID</th>
                    <th>movieName</th>
                    <th>movieContent</th>
                    <th>actor</th>
                    <th>director</th>
                    <th>age</th>
                    <th>image</th>
                    <th>function</th>
                </tr>

                <c:forEach items="${requestScope.allMovies}" var="allMovies">
                    <tr>
                        <td>${allMovies.getMovieID()}</td>
                        <td>${allMovies.getMovieName()}</td>
                        <td>${allMovies.getMovieContent()}</td>
                        <td>${allMovies.getActor()}</td>
                        <td>${allMovies.getDirector()}</td>
                        <td>${allMovies.getAge()}</td>
                        <td><img style="height: 100px; width: 100px" src="img/${allMovies.getMovieImage()}"/></td>
                        <td>
                            <a href="LoadFromMovieToModifyMovieServlet?id=${allMovies.movieID}">Modify movie</a>
                            <br>
                            <br>
                            <a href="LoadFromMovieToAddShowTimeServlet?id=${allMovies.movieID}">Add new show time</a>
                            <c:set value="${requestScope.allShowTimesByID}" var="showTime" />
                            <br>
                            <br>
                            <a href="LoadAllShowTimeAdminServlet?id=${allMovies.movieID}">Show All show time</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <a href="adminWeb-page.jsp"><input type="button" name="" value="Quay Lại"></a>
    </body>
</html>
<!--            <table border="1px solid black">
                <tr>
                    <th>function</th>
                </tr>
                <c:forEach items="${sessionScope.allShowTimesByID}" var="showTime">
                    <tr>
                        <%--<c:if test="${showTime.movieID == movie.movieID}">--%>
                        <td>
                            <a href="LoadAllShowTimeAdminServlet?id=${showTime.showTimeID}">Show All show time</a>
                        </td>
                        <%--</c:if>--%>
                    </tr>
                </c:forEach>
            </table>-->
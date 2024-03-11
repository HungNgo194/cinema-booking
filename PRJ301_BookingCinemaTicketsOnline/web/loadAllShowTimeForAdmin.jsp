<%-- 
    Document   : loadAllShowTimeForAdmin
    Created on : Mar 7, 2024, 11:49:28 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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


    </style>
    <body>
    <div class="form-container">
        <h1>Danh Sách xuất chiếu</h1>
            <table border="1">
                <tr>
                    <th>ShowTime ID</th>
                    <th>Open Date</th>
                    <th>Close Date</th>
                    <th>Hour Start</th>
                    <th>Hour End</th>
                    <th>Show Status</th>
                    <th>Room ID</th>
                    <th>Movie ID</th>
                </tr>
                <c:forEach var="getShowTimeByMovieID" items="${requestScope.getShowTimeByMovieID}" >
                    <tr>
                        <td>${getShowTimeByMovieID.showTimeID}</td>
                        <td>${getShowTimeByMovieID.openDate}</td>
                        <td>${getShowTimeByMovieID.closeDate}</td>
                        <td>${getShowTimeByMovieID.hourStart}</td>
                        <td>${getShowTimeByMovieID.hourEnd}</td>
                        <td>${getShowTimeByMovieID.showStatus}</td>
                        <td>${getShowTimeByMovieID.roomID}</td>
                        <td>${getShowTimeByMovieID.movieID}</td>
                        <td>
                            <a href="LoadFromShowTimeToModifyShowTimeServlet?showTimeId=${getShowTimeByMovieID.showTimeID}">Modify show time</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </div>
</body>
</html>

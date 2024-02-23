<%-- 
    Document   : addNewShowTime-Admin
    Created on : Feb 9, 2024, 6:31:42 PM
    Author     : Admin
--%>

<%@page import="showTime.ShowTimeDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new Show Time</title>
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
                color: red
            }
        </style>
    </head>
    <body>
        <h2>Add new show times</h2>
        <form action="AddNewShowTimeAdminServlet" method="post">
            <div class="movie-add">
                <!--<input name="showTimeID" placeholder="showTimeID"><br>-->
                <input required="true" name="openDate" placeholder="openDate"><br>
                <input required="true" name="closeDate" placeholder="closeDate"><br>
                <input required="true" name="director" placeholder="director"><br>
                <input required="true" name="hourStart" placeholder="hourStart"><br>
                <input required="true" name="hourEnd" placeholder="hourEnd"><br>
                <!--<input name="showStatus" placeholder="showStatus"><br>-->
                <input required="true" name="roomID" placeholder="roomID"><br>
                <input required="true" name="movieID" placeholder="movieID"><br>
            </div>
            <div class="check-button">
                <a href="adminWeb-page.jsp"><input type="button" name="" value="Quay Lại"></a>
                <input type="submit" name="" value="Xác Nhận">
            </div>
            <%
                Boolean openDateAfterCloseDate = (Boolean) request.getAttribute("openDateAfterCloseDate");
                if (Boolean.TRUE.equals(openDateAfterCloseDate)) {

            %>
            <p>Not valid !</p>
            <p>The opening date is can be not after the closing date</p>
            <%                }
                ShowTimeDTO existingShowTime = (ShowTimeDTO) request.getAttribute("existingShowTime");
                if (existingShowTime != null) {
            %>
            <p>There is an overlap with an existing show time:</p>
            <p>Start Time: <%= existingShowTime.getHourStart()%></p>
            <p>End Time: <%= existingShowTime.getHourEnd()%></p>
            <%
                }
                Boolean hourStartAfterHourEnd = (Boolean) request.getAttribute("hourStartAfterHourEnd");
                if (Boolean.TRUE.equals(hourStartAfterHourEnd)) {
            %>
            <p>Not valid !</p>
            <p>The starting time is can be not after the ending time</p>
            <%
                }
                Boolean showTime = (Boolean) request.getAttribute("showTime");
                if (Boolean.TRUE.equals(showTime)) {
            %>
            <p>Successfully added show time!</p>
            <%
                }
            %>
        </form>
    </body>
</html>


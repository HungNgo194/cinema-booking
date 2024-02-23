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
            input[type=radio]{


            }
        </style>
    </head>
    <body>
<!--        <form action="SearchShowTimeAdminServlet" method="GET">-->
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
                <c:forEach var="showTime" items="${requestScope.getAllShowTimes}">
                    <tr>
                        <td>${showTime.showTimeID}</td>
                        <td>${showTime.openDate}</td>
                        <td>${showTime.closeDate}</td>
                        <td>${showTime.hourStart}</td>
                        <td>${showTime.hourEnd}</td>
                        <td>${showTime.showStatus}</td>
                        <td>${showTime.roomID}</td>
                        <td>${showTime.movieID}</td>
                    </tr>
                </c:forEach>
            </table>
<!--            <input type="submit" name="" value="Show all show times">
        </form>-->
        <form action="ModifyShowTimeAdminServlet" method="post">
            <div class="movie-add">
                <input required="true" name="showTimeID" placeholder="showTimeID"><br>
                <input required="true" name="openDate" placeholder="openDate"><br>
                <input required="true" name="closeDate" placeholder="closeDate"><br>
                <input required="true" name="director" placeholder="director"><br>
                <input required="true" name="hourStart" placeholder="hourStart"><br>
                <input required="true" name="hourEnd" placeholder="hourEnd"><br>
                <!--<input required="true" name="showStatus" placeholder="showStatus"><br>-->
                <input required="true" name="roomID" placeholder="roomID"><br>
                <input required="true" name="movieID" placeholder="movieID"><br>
                Check status:
                <label style="text-align: center"><input type="radio" name="showStatus" value="Exist" checked=""><span>Exist</span></label>
                <label style="text-align: center"><input type="radio" name="showStatus" value="Not exist"><span>Not exist</span></label>

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

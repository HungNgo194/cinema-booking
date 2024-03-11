
<%@page import="SendEmailPassword.SendEmail"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Send email password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <style>
        h1 {
            margin-left: 18px;
            font-size: 24px; 
            color: #333; 
            margin-bottom: 20px;
        }
     
        form {
            margin: 20px; 
        }

      
        input[type="text"] {
            width: 200px; 
            padding: 5px; 
            border: 1px solid #ccc; 
            border-radius: 3px; 
        }

        
        input[type="submit"] {
            padding: 5px 10px; 
            background-color: #007bff; 
            color: #fff; 
            border: none; 
            border-radius: 3px; 
            cursor: pointer; 
        }

        
        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        button {
            padding: 10px 20px; 
            background-color: red; 
            color: #fff; 
            border: none; 
            border-radius: 3px; 
            cursor: pointer; 
            text-decoration: none; 
            margin-left: 18px;
        }

        
        button:hover {
            background-color: #0056b3; 
        }
    </style>
    <body>
        <h1>Nếu bạn quên mật khẩu, hãy nhập địa chỉ email tại đây !</h1>
        <form action="send">    
            Re-enter your email: <input type="text" name="mail" placeholder="Email"/>
            <input type="submit" value="Send"/>
        </form>
    </body>
    <a href="signin.jsp"><button type="button">Quay lại</button></a> 
    <c:set value="${requestScope.sendEmail}" var="sendEmail" />
    <c:set value="${requestScope.unSuccess}" var="unSuccess" />
    <c:set value="${requestScope.success}" var="success" />
    <%
        SendEmail sendEmail = (SendEmail) request.getAttribute("sendEmail");
        String unSuccess = (String) request.getAttribute("unSuccess");
        String success = (String) request.getAttribute("success");
        if (unSuccess != null) {
    %>
    <p style="font-weight: bold; color: red">${unSuccess}</p>
    <%
        }
        if (success != null) {

    %>
    <p style="font-weight: bold; color: green">${success}</p>
    <%        }
    %>
</html>

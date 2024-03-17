<%-- 
    Document   : return_payment.jsp
    Created on : Feb 27, 2024, 1:52:51 PM
    Author     : Admin
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="payment.PaymentConfig"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KẾT QUẢ THANH TOÁN</title>
    </head>
    <body>
        <h1>${requestScope.message}</h1>
        <div class="table-responsive">
            <div class="form-group">
                <label >Mã giao dịch thanh toán:</label>
                <label>${requestScope.payment.getId()}</label>
            </div>    
            <div class="form-group">
                <label >Số tiền:</label>
                <label>${requestScope.payment.getAmount()}</label>
            </div>  
            <div class="form-group">
                <label >Mô tả giao dịch:</label>
                <label>Thanh toán hóa đơn ${requestScope.booking.getBookingID()}</label>
            </div> 
            <div class="form-group">
                <label >Mã lỗi thanh toán:</label>
                <label>${requestScope.payment.getResponseCode()}</label>
            </div> 
            <div class="form-group">
                <label >Mã giao dịch tại CTT VNPAY-QR:</label>
                <label>${requestScope.payment.getTransactionNo()}</label>
            </div> 
            <div class="form-group">
                <label >Mã ngân hàng thanh toán:</label>
                <label>${requestScope.payment.getBank()}</label>
            </div> 
            <div class="form-group">
                <label >Thời gian thanh toán:</label>
                <label>${requestScope.payment.getPayDate()}</label>
            </div> 
            <p>
                <a href="userWeb-page.jsp"><input type="button" value="Quay lại"></a>
                &nbsp;
            </p>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>  
    </body>
</html>


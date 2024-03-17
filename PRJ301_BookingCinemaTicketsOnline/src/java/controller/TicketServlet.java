/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import account.AccountDTO;
import booking.BookingDAO;
import booking.BookingDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import movie.MovieDTO;
import payment.PaymentConfig;
import payment.PaymentDAO;
import payment.PaymentDTO;
import seatDetail.SeatDetailDAO;
import seatDetail.SeatDetailDTO;
import showTime.ShowTimeDAO;
import showTime.ShowTimeDTO;
import ticket.TicketDAO;
import ticket.TicketDTO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "TicketServlet", urlPatterns = {"/TicketServlet"})
public class TicketServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        BookingDTO booking = (BookingDTO) session.getAttribute("booking"); // lấy attributte booking từ servlet booking

        /*
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = PaymentConfig.hashAllFields(fields);
        String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");;
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
        String vnp_BankCode = request.getParameter("vnp_BankCode");
        String vnp_PayDate = request.getParameter("vnp_PayDate");

        String message = "";
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(vnp_TransactionStatus)) {
                message += "GIAO DICH THANH CONG";
            } else {
                message += "GIAO DICH KHONG THANH CCONG";
            }

        } else {
            message += "CHU KI KHONG HOP LE";
        }
        
        request.setAttribute("message", message);
        request.getRequestDispatcher("return_payment.jsp").forward(request, response);
    }
        
         */
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");

        }
        String signValue = PaymentConfig.hashAllFields(fields);
        String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");;
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
        String vnp_BankCode = request.getParameter("vnp_BankCode");
        String vnp_PayDate = request.getParameter("vnp_PayDate");
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");

        Long payId = Long.parseLong(vnp_TxnRef);
        int amount = Integer.parseInt(vnp_Amount);

        AccountDTO account = (AccountDTO) session.getAttribute("account");
        LocalDate payDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            payDate = LocalDate.parse(vnp_PayDate, formatter);
        } catch (Exception e) {
        }

        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");

        String infoList[] = vnp_OrderInfo.trim().split("&");
        String showDate = infoList[0];
        String showTime = infoList[1];
        int movieId = Integer.parseInt(infoList[2]) / 100;
        int transactionNo = Integer.parseInt(vnp_TransactionNo);

        String availableSeats[] = infoList[3].split(",");
        ArrayList tickets = new ArrayList<TicketDTO>();
        PaymentDTO payment = null;
        
        String message = "";
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(vnp_TransactionStatus)) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(showDate, formatter);
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime time = LocalTime.parse(showTime, formatter2);

                    String bookingId = booking.getBookingID(); // lấy booking ID của thằng booking được lấy từ session
                    int numOfBook = booking.getNumberOfBooking(); // tương tự

                    // SAVE BOOKING
                    BookingDAO bdao = new BookingDAO();
                    bdao.createBooking(booking.getBookingID(), booking.getNumberOfBooking(), booking.getPriceTotal(), booking.getBookingDate(), booking.getUserName());
                    // END BOOKING

                    SeatDetailDAO sd = new SeatDetailDAO();
                    ShowTimeDAO sdao = new ShowTimeDAO();
                    int showTimeID = 0;
                    int roomID = 0;
                    List<ShowTimeDTO> ShowTimeList = sdao.getShowTimeId2(date, movieId);
                    for (ShowTimeDTO showTimeDTO : ShowTimeList) {
                        if (showTimeDTO.getHourStart() == time) {
                            showTimeID = showTimeDTO.getShowTimeID();
                            roomID = showTimeDTO.getRoomID();
                        }
                    }
                    // cái dưới đây để chỉnh seatStatus thành 1 khi ghế được đặt hoàn tất, Hưng tự gọi lại ở trang cuối nha
                    for (String availableSeat : availableSeats) {
                        sd.modifiedStatus(availableSeat, roomID, showTimeID);
                    }
                    TicketDAO tk = new TicketDAO();
                    // với mỗi ticket thì nó sẽ gọi thằng dưới đây để xử lí từng cái 
                    for (int i = 0; i < numOfBook; i++) {
                        tk.createTicket(showTimeID, availableSeats[i], bookingId);
                        TicketDTO ticket = new TicketDTO(showTimeID, availableSeats[i], bookingId);
                        tickets.add(ticket);
                    }
                    PaymentDAO pmdao = new PaymentDAO();
                    pmdao.createPayment(payId, amount, vnp_OrderInfo, vnp_ResponseCode, transactionNo, vnp_BankCode, payDate, vnp_TransactionStatus, booking, account);
                    payment = new PaymentDTO(payId, amount, vnp_OrderInfo, vnp_ResponseCode, transactionNo, vnp_BankCode, payDate, vnp_TransactionStatus, booking, account);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                message += "GIAO DICH THANH CONG";
                request.setAttribute("booking", booking);
                request.setAttribute("ticket", tickets);
                request.setAttribute("payment", payment);
                
            } else {
                message += "GIAO DICH KHONG THANH CCONG";
            }

        } else {
            message += "CHU KI KHONG HOP LE";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("return_payment.jsp").forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

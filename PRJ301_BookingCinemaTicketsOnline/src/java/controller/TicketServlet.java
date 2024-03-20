/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import SendEmailPassword.SendEmail;
import account.AccountDTO;
import booking.BookingDAO;
import booking.BookingDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
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
import movie.MovieDAO;
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
    public String convertedWithComma(int raw_price) {
        // Convert eachTicketPrice to String
        StringBuilder convertedEachTicket = new StringBuilder();
        // Convert again to string with commas
        String priceString = String.valueOf(raw_price);
        for (int i = priceString.length() - 1; i >= 0; i--) {
            if ((priceString.length() - i) % 3 == 1 && i != priceString.length() - 1) {
                convertedEachTicket.insert(0, ",");
            }
            convertedEachTicket.insert(0, priceString.charAt(i));
        }

        // Use convertedEachTicket.toString() as the formatted string
        String formattedPrice = convertedEachTicket.toString();
        return formattedPrice;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        BookingDTO booking = (BookingDTO) session.getAttribute("booking"); // lấy attributte booking từ servlet booking

        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }
        String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = PaymentConfig.hashAllFields(fields);
        String title = "";
        String ms = "";
        String ms1 = "";
        String final_price = "";
        TicketDTO ticket = new TicketDTO();
        PaymentDTO payment = new PaymentDTO();
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(vnp_TransactionStatus)) {
                String vnp_TxnRef = request.getParameter("vnp_TxnRef");
                String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
                String vnp_Amount = request.getParameter("vnp_Amount");
                String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
                String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
                String vnp_BankCode = request.getParameter("vnp_BankCode");
                String vnp_PayDate = request.getParameter("vnp_PayDate");

                String orderInfoList[] = vnp_OrderInfo.trim().split("&");
                String showDate = orderInfoList[0];
                String showTime = orderInfoList[1];
                String movieID = orderInfoList[2];
                String availableSeat = orderInfoList[3]; 
                
                
                String[] availableSeats = availableSeat.split(",");
                boolean next = false;
                try {
                    TicketDAO tk = new TicketDAO();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(showDate, formatter);
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime time = LocalTime.parse(showTime, formatter2);
                    String bookingId = booking.getBookingID(); // lấy booking ID của thằng booking được lấy từ session
                    // SAVE BOOKING 
                    BookingDAO bdao = new BookingDAO();
                    bdao.createBooking(booking.getBookingID(), booking.getNumberOfBooking(), booking.getPriceTotal(), booking.getBookingDate(), booking.getUserName());
                    // END BOOKING
                    SeatDetailDAO sd = new SeatDetailDAO();
                    ShowTimeDAO sdao = new ShowTimeDAO();
                    int showTimeID = 0;
                    int roomID = 0;
                    List<ShowTimeDTO> ShowTimeList = new ArrayList<>();
                    ShowTimeList = sdao.getShowTimeId2(date, Integer.parseInt(movieID));
                    if (ShowTimeList.size() == 0) {
                        String uniqueShow = orderInfoList[4];
                        showTimeID = Integer.parseInt(uniqueShow);
                        ShowTimeList.add(sdao.getShowTimeByID(showTimeID));
                        roomID = sdao.getShowTimeByID(showTimeID).getRoomID();
                        next = true;
                    }
                    if (ShowTimeList.size() != 0) {

                        for (ShowTimeDTO showTimeDTO : ShowTimeList) {
                            System.out.println(showTimeDTO.getHourStart());
                            if (showTimeDTO.getHourStart().equals(time)) {
                                showTimeID = showTimeDTO.getShowTimeID();
                                roomID = sdao.getShowTimeByID(showTimeID).getRoomID();
                            }
                        }
                    }
//             cái dưới đây để chỉnh seatStatus thành 1 khi ghế được đặt hoàn tất
                    for (String seatID : availableSeats) {
                        sd.modifiedStatus(seatID, showTimeID);
                        tk.createTicket(showTimeID, seatID, bookingId);
                        ticket.setShowTimeID(showTimeID);
                        ticket.setSeatID(seatID);
                        ticket.setBookingID(bookingId);
                    }
                    final_price = convertedWithComma(booking.getPriceTotal());
                    request.setAttribute("totalPaid", final_price);
                    
                    MovieDAO mdao = new MovieDAO();
                   
                    
                    DateTimeFormatter payFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                    LocalDate paydate = LocalDate.parse(vnp_PayDate, payFormatter);
                    PaymentDAO pdao = new PaymentDAO();
                    payment = pdao.createPayment(Long.parseLong(vnp_TxnRef), Integer.parseInt(vnp_Amount), vnp_OrderInfo, vnp_ResponseCode, vnp_TransactionNo, vnp_BankCode, paydate, vnp_TransactionStatus, booking, account);
                    String message = "TICKET INFORMATION" + "\n"
                            + "Booking code: " + booking.getBookingID() + "\n"
                            + "Moive: " + mdao.searchByID(Integer.parseInt(movieID)).getMovieName() + "\n"
                            + "Date: " + date + "\n"
                            + "Time: " + time + "\n"
                            + "Room: " + roomID + "\n"
                            + "Number of tickets: " + booking.getNumberOfBooking() + "\n"
                            + "Seats: " + availableSeat + "\n" + "\n"
                            + "PAYMENT INFORMATION" + "\n"
                            + "Total momey: " + final_price + "\n"
                            + "Transaction ID: " + vnp_TxnRef + "\n"
                            + "Order description: " + "Payment for movie tickets." + "\n"
                            + "Bank code: " + vnp_BankCode + "\n"
                            + "Payment time: " + paydate;
                    
                    SendEmail send = new SendEmail(account.getEmail(), "RẠP CHIẾU PHIM NHỮNG CẬU TRAI THÂN MẬT", message);
                    title += "SUCCESSFULL PURCHASE";
                    ms += "TRANSACTION SUCCESSFULL! Thank you for supporting us";
                    ms1 += "Please check your E-mail for detail information";
                } catch (SQLException ex) {
                    Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                title += "PURCHASE FAILED";
                ms += "TRANSACTION FAILED! Check your PAYMENT DETAIL CAREFULLY!!";
            }

        } else {
            title += "PURCHASE FAILED";
            ms += "TRANSACTION FAILED! Invalid Signature";
        }
        request.setAttribute("title", title);
        request.setAttribute("ms1", ms1);
        request.setAttribute("ticket", ticket);
        request.setAttribute("payment", payment);
        request.setAttribute("totalPaid", final_price);
        request.setAttribute("message", ms);
        RequestDispatcher rd = request.getRequestDispatcher("transaction.jsp");
        rd.forward(request, response);
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

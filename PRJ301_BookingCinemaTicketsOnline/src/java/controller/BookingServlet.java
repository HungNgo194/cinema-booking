package controller;

import account.AccountDTO;
import booking.BookingDAO;
import booking.BookingDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import membership.MembershipDAO;
import movie.MovieDAO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    // hàm để xử lí số 
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String movieName = request.getParameter("movieName");
//       lấy thông tin về ngày, suất chiếu được đặt
        String date = request.getParameter("showDate");
        String time = request.getParameter("showTime");
//        
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        LocalDate currentDate = LocalDate.now();
        String numOfBook_raw = request.getParameter("numOfBook"); // lấy tổng số vé từ trang booking
        String totalPrice_raw = request.getParameter("total"); // lấy tổng tiền từ trang booking
        String totalPrice_withoutCommas = totalPrice_raw.replaceAll(",", ""); // bỏ dấu phẩy trong số tiền
        String selectedSeat = request.getParameter("seat"); // lấy chuỗi các ghế ngồi 
        request.setAttribute("seatArray", selectedSeat);
        String[] seats = selectedSeat.split(","); // tách chuỗi thành từng ghế khác nhau

//        List availableSeats = new ArrayList<SeatDTO>();
        request.setAttribute("availableSeats", seats); // gửi attribute 
        String url = "";
        try {
            MovieDAO Mdao = new MovieDAO();

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int numOfBookings = Integer.parseInt(numOfBook_raw);
            int totalPrice = Integer.parseInt(totalPrice_withoutCommas);
//            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate); // xử lí thời gian đặt chỗ vào đúng lúc mua vé 
            int eachTicketPrice = (int) ((double) totalPrice / numOfBookings); // chia lấy tiền của từng vé 
            // MEMBERSHIPDAO
            MembershipDAO mDao = new MembershipDAO();
            int discount = mDao.getMemberShipDiscount(account.getUserName()); // lấy discount của user 
            int discountPortion = (totalPrice * discount) / 100; // chia lấy tổng tiền giảm được 
            int priceAfterDiscount = totalPrice - discountPortion; // tổng số tiền sau khi được giảm 
            request.setAttribute("discountPortion", convertedWithComma(discountPortion));
            request.setAttribute("priceAfterDiscount", convertedWithComma(priceAfterDiscount));
            // END MEMBERSHIPDAO            
            request.setAttribute("totalPrice", convertedWithComma(eachTicketPrice));
            request.setAttribute("totalPaid", totalPrice);
            UUID bookingID = UUID.randomUUID(); // hàm để lấy ID ngẫu nhiền, khỏi phải gọi hàm ngẫu nhiên từ database 
            if (numOfBookings != 0) {

                BookingDTO booking = new BookingDTO(bookingID.toString(), numOfBookings, totalPrice, currentDate, account.getUserName()); // tạo booking để gửi về trang cuối 
                session.setAttribute("booking", booking);
                request.setAttribute("SHOWDATE", date);
                request.setAttribute("SHOWTIME", time);
                request.setAttribute("MOVIE", Mdao.checkExistMovie(movieName));
                request.setAttribute(time, mDao);
                url = "purchase.jsp"; // bấm nút thanh toán bên purchase sẽ nhảy vô ticketServlet
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                url = "booking.jsp?error=numOfBookingsZero";
                response.sendRedirect(url);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

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

package controller;

import account.AccountDTO;
import booking.BookingDAO;
import booking.BookingDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import membership.MembershipDAO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

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
        LocalDate currentDate = LocalDate.now();
        String numOfBook_raw = request.getParameter("numOfBook");
        String totalPrice_raw = request.getParameter("total");

        String totalPrice_withoutCommas = totalPrice_raw.replaceAll(",", "");
        String selectedSeat = request.getParameter("seat");
        String[] seats = selectedSeat.split(",");
        // each ticket's price

//        List availableSeats = new ArrayList<SeatDTO>();
        session.setAttribute("availableSeats", seats);
        String url = "";
        try (PrintWriter out = response.getWriter()) {

            int numOfBookings = Integer.parseInt(numOfBook_raw);
            int totalPrice = Integer.parseInt(totalPrice_withoutCommas);
            request.setAttribute("totalPaid", convertedWithComma(totalPrice));
            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
            int eachTicketPrice = (int) ((double) totalPrice / numOfBookings);
            // MEMBERSHIPDAO
            MembershipDAO mDao = new MembershipDAO();
            int discount = mDao.getMemberShipDiscount(account.getUserName());
            int discountPortion = (totalPrice * discount) / 100;
            int priceAfterDiscount = totalPrice - discountPortion;
            request.setAttribute("discountPortion", convertedWithComma(discountPortion));
            request.setAttribute("priceAfterDiscount", convertedWithComma(priceAfterDiscount));
            // end MEMBERSHIPDAO
//            Check seat and locked 
            session.setAttribute("totalPrice", convertedWithComma(eachTicketPrice));
            UUID bookingID = UUID.randomUUID();
            BookingDAO bk = new BookingDAO();
            if (numOfBookings != 0) {

                BookingDTO createBooking = bk.createBooking(bookingID.toString(), numOfBookings, totalPrice, sqlDate, account.getUserName());
                if (createBooking != null) {

                    session.setAttribute("booking", createBooking);
                    url = "purchase.jsp";
//                    url = "ShowTimeServlet";
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }

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

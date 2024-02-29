/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import account.AccountDTO;
import booking.BookingDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import seat.SeatDAO;
import seat.SeatDTO;
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
        BookingDTO booking = (BookingDTO) session.getAttribute("booking");
        
        String[] availableSeats = (String[]) session.getAttribute("availableSeats");
        String bookingId = booking.getBookingID();
        int numOfBook = booking.getNumberOfBooking();
        ArrayList tickets = new ArrayList<TicketDTO>();
        try{
            TicketDAO tk = new TicketDAO();
            for (int i = 0; i < numOfBook; i++) {
                TicketDTO ticket = tk.createTicket(5, availableSeats[i], bookingId);

                if (ticket != null) {
                    tickets.add(ticket);
                }
            }
            SeatDAO sd = new SeatDAO();
            for (String availableSeat : availableSeats) {
                SeatDTO seat = sd.modifiedStatus(availableSeat, 2);
            }
          
            if (!tickets.isEmpty()) {
                response.sendRedirect("transaction.jsp");
            } else {
                response.sendRedirect("purchase.jsp?error=notchecked");
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

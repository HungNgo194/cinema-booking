/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import booking.BookingDAO;
import cinema.CinemaDAO;
import cinema.CinemaDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import seatDetail.SeatDetailDAO;
import showTime.ShowTimeDAO;
import showTime.ShowTimeDTO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "LoadMovieServlet", urlPatterns = {"/LoadMovieServlet"})
public class LoadMovieServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String url = "";
        String movieName = request.getParameter("button");
        try {
            MovieDAO dao = new MovieDAO();
            CinemaDAO Cdao = new CinemaDAO();
            SeatDetailDAO SDdao = new SeatDetailDAO();
            ShowTimeDAO Sdao = new ShowTimeDAO();
            List<CinemaDTO> cinemaList = Cdao.getAllCinemas();
            List<ShowTimeDTO> showTimeStringList = Sdao.getAllShowTimes(dao.checkExistMovie(movieName).getMovieID());
            List<LocalDate> sendedTimeList = new ArrayList<>();
            Date date2 = Calendar.getInstance().getTime();
            LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // ngăn việc reset hàng ghế khi có booking 
            BookingDAO bdao = new BookingDAO();
            boolean move = bdao.checkAll();

            for (ShowTimeDTO showtime : showTimeStringList) {
                LocalDate currentDate = showtime.getOpenDate();
                int comparison = currentDate.compareTo(localDate2);

                // duyệt khi currentDate = hoặc > localDate2
                while (comparison < 0) {

                    if (!move) {
                        SDdao.modifiedAllStatus(showtime.getShowTimeID());
                    }
                    // update ngày của showtime 
                    currentDate = currentDate.plusDays(1);
                    showtime.setOpenDate(currentDate);
                    // gán lại giá trị của comp
                    comparison = currentDate.compareTo(localDate2);
                }
                sendedTimeList.add(showtime.getOpenDate());
            }
            Set<LocalDate> uniqueTimes = new HashSet<>(sendedTimeList);
            sendedTimeList.clear();
            sendedTimeList.addAll(uniqueTimes);
            request.setAttribute("CINEMA", cinemaList);
            session.setAttribute("SHOWTIMELIST", sendedTimeList);
            request.setAttribute("MOVIE", dao.checkExistMovie(movieName));
            request.setAttribute("movieName", movieName);
            url = "booking.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(LoadMovieServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

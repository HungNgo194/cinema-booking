/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cinema.CinemaDAO;
import cinema.CinemaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import showTime.ShowTimeDAO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "LoadAllShowTimeServlet", urlPatterns = {"/LoadAllShowTimeServlet"})
public class LoadAllShowTimeServlet extends HttpServlet {

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
        String movieName = request.getParameter("movieName");
        String url = request.getParameter("url");
        String showtime = request.getParameter("showtime");
        // servlet thu hai

        try {
            MovieDAO dao = new MovieDAO();
            ShowTimeDAO Sdao = new ShowTimeDAO();
            CinemaDAO Cdao = new CinemaDAO();
            List<CinemaDTO> cinemaList = Cdao.getAllCinemas();
            List<String> showTimeStringList = Sdao.getAllShowTimes(dao.checkExistMovie(movieName).getMovieID());
            List<String> sendedTimeList = new ArrayList<>();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date date2 = Calendar.getInstance().getTime();
            LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            for (String origin_date : showTimeStringList) {
                LocalDate compare_date = LocalDate.parse(origin_date, dateFormatter);
                int comparison = compare_date.compareTo(localDate2);
                if (comparison > 0 || comparison == 0) {
                    sendedTimeList.add(origin_date);
                } else {
                    Sdao.setShowTimeStatus(dao.checkExistMovie(movieName).getMovieID(), compare_date);
                }
            }
            request.setAttribute("CINEMA", cinemaList);
            request.setAttribute("show", showtime);
            session.setAttribute("SHOWTIMELIST", sendedTimeList);

        } catch (SQLException ex) {
            Logger.getLogger(LoadAllShowTimeServlet.class.getName()).log(Level.SEVERE, null, ex);
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

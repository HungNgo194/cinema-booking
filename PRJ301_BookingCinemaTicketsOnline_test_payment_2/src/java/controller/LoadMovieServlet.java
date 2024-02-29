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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
        HttpSession session = request.getSession();
        String url = "";
        String movieName = request.getParameter("movieName");
        try {
            MovieDAO dao = new MovieDAO();
            MovieDTO result = dao.checkExistMovie(movieName);
            CinemaDAO Cdao = new CinemaDAO();
            ShowTimeDAO Sdao = new ShowTimeDAO();
            List<CinemaDTO> cinemaList = Cdao.getAllCinemas();
            List<ShowTimeDTO> showTimeList = Sdao.getAll();
            List<String> showTimeStringList = Sdao.getAllShowTimes(result.getMovieID());

            List<ShowTimeDTO> showTimeList2 = new ArrayList<>();

            for (ShowTimeDTO show : showTimeList) {
                if (show.getMovieID() == result.getMovieID()) {
                    showTimeList2.add(show);

                }
            }

            request.setAttribute("CINEMA", cinemaList);
            request.setAttribute("SHOWTIME", showTimeList2);
            session.setAttribute("SHOWTIMELIST", showTimeStringList);
            session.setAttribute("MOVIE", result);
            if (result != null) {
                url = "booking.jsp";

            }
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

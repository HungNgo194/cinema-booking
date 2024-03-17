/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cinema.CinemaDAO;
import cinema.CinemaDTO;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import movie.MovieDTO;
import room.RoomDAO;
import seatDetail.SeatDetailDAO;
import seatDetail.SeatDetailDTO;
import showTime.ShowTimeDAO;
import showTime.ShowTimeDTO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "LoadSeatServlet", urlPatterns = {"/LoadSeatServlet"})
public class LoadSeatServlet extends HttpServlet {

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
        String detailTime = request.getParameter("detailTime");
        String movieName = request.getParameter("movie");
        String roomID = request.getParameter("roomID");
        String lay = request.getParameter("lay");
        try {
            ShowTimeDAO Sdao = new ShowTimeDAO();
            MovieDAO Mdao = new MovieDAO();;
//            RoomDAO Rdao = new RoomDAO();
////          XU LI DATE
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(lay, formatter);
////          xu li time
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(detailTime, formatter2);
            int movieID = Mdao.checkExistMovie(movieName).getMovieID();
//        
            List<Integer> showTimeIDs = Sdao.getShowTimeWithopenDateAndRoomID(date, Integer.parseInt(roomID), movieID);
            List<ShowTimeDTO> listOfShowTimes = new ArrayList<>();
            for (Integer id : showTimeIDs) {
                ShowTimeDTO result = Sdao.getShowTimesWithID(id);
                if (result.getHourStart().equals(time)) {
                    listOfShowTimes.add(result);
                }
            }
            CinemaDAO Cdao = new CinemaDAO();
            List<CinemaDTO> cinemaList = Cdao.getAllCinemas();
            SeatDetailDAO seatCheck = new SeatDetailDAO();
            if (listOfShowTimes.size() == 1) {
                for (ShowTimeDTO stid : listOfShowTimes) {
                    int showTimeID = stid.getShowTimeID();
                    ArrayList<SeatDetailDTO> availableSeats = seatCheck.getAvailableSeats(Integer.parseInt(roomID), showTimeID);
                    ArrayList<SeatDetailDTO> lockedSeats = seatCheck.getUnavailableSeats(Integer.parseInt(roomID), showTimeID);
                    request.setAttribute("ArraySeats", availableSeats);
                    request.setAttribute("lockSeats", lockedSeats);
                    request.setAttribute("detailTime", stid.getHourStart());
                    request.setAttribute("show", lay);
                    request.setAttribute("CINEMA", cinemaList);
                }
            }
            // MOVIE PART
            request.setAttribute("MOVIE", Mdao.checkExistMovie(movieName));
        } catch (SQLException ex) {
            Logger.getLogger(LoadSeatServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DateTimeException e) {
            System.out.println("sai");
            e.printStackTrace();
        } finally {

            RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
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

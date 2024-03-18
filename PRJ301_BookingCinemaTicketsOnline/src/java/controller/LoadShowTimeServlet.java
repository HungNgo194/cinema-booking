package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import movie.MovieDTO;
import showTime.ShowTimeDAO;
import showTime.ShowTimeDTO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "LoadShowTimeServlet", urlPatterns = {"/LoadShowTimeServlet"})
public class LoadShowTimeServlet extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        String movieName = request.getParameter("id");
        String showtime = request.getParameter("showtime");
        String url = "booking.jsp";
        try {
            ShowTimeDAO dao = new ShowTimeDAO();
            MovieDAO Mdao = new MovieDAO();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date date2 = Calendar.getInstance().getTime();
            LocalTime localTime2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

            LocalDate localDate = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate date = LocalDate.parse(showtime, formatter);

            List<ShowTimeDTO> result = null;
            result = dao.getShowTimesWithOpenDateAndMovieId(date, Mdao.checkExistMovie(movieName).getMovieID());
            if (result.size() == 0) {
                result = dao.getAllShowTimesUnique(Mdao.checkExistMovie(movieName).getMovieID());
                
            }
            List<ShowTimeDTO> haha = new ArrayList<>();
            List<LocalTime> hoho = new ArrayList<>();
            for (ShowTimeDTO localTime : result) {
                int comparison = date.compareTo(localDate);
                if (comparison == 0) {
                    if (localTime.getHourStart().isAfter(localTime2)) {
                        haha.add(localTime);
                    }
                } else if (comparison > 0) {
                    haha.add(localTime);
                    hoho.add(localTime.getHourStart());
                }
            }
            request.setAttribute("showTimes", haha);
            request.setAttribute("allShowTimes", hoho);
            request.setAttribute("showtime", showtime);
            request.setAttribute("url", url);
            request.setAttribute("movieName", movieName);
//            request.setAttribute("SHOWTIMELIST", sendedTimeList);

            // MOVIE PART
            request.setAttribute("MOVIE", Mdao.checkExistMovie(movieName));
        } catch (SQLException ex) {
            Logger.getLogger(LoadShowTimeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("LoadAllShowTimeServlet?url=booking.jsp&movieName=" + movieName + "&showtime=" + showtime);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import movie.MovieDTO;
import seatDetail.SeatDetailDAO;
import showTime.ShowTimeDAO;
import showTime.ShowTimeDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddNewShowTimeAdminServlet", urlPatterns = {"/AddNewShowTimeAdminServlet"})
public class AddNewShowTimeAdminServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String openDate_raw = request.getParameter("openDate").trim();
            String closeDate_raw = request.getParameter("closeDate").trim();
            String hourStart_raw = request.getParameter("hourStart").trim();
            String hourEnd_raw = request.getParameter("hourEnd").trim();
            String showStatus_raw = request.getParameter("showStatus");
            String roomID_raw = request.getParameter("roomID");
            String movieID_raw = request.getParameter("movieID");
            LocalDate openDate;
            LocalDate closeDate;
            LocalTime hourStart;
            LocalTime hourEnd;
            boolean showStatus;
            int roomID;
            int movieID;
            boolean overlapchecked = false;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                openDate = LocalDate.parse(openDate_raw, formatter);
                closeDate = LocalDate.parse(closeDate_raw, formatter);

                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                hourStart = LocalTime.parse(hourStart_raw, formatter2);
                hourEnd = LocalTime.parse(hourEnd_raw, formatter2);

                showStatus = Boolean.parseBoolean(showStatus_raw);
                roomID = Integer.parseInt(roomID_raw);
                movieID = Integer.parseInt(movieID_raw);
                ShowTimeDAO dao = new ShowTimeDAO();
                List<ShowTimeDTO> existingShowTimes = dao.getShowTimesForRoomAndDateRangeForAddAction(movieID, roomID, openDate, closeDate);
                // ngay bat dau < ngay ket thuc
                if (!openDate.isAfter(closeDate)) {
                    for (ShowTimeDTO existingShowTime : existingShowTimes) {
                        LocalTime existingStart = existingShowTime.getHourStart().minusMinutes(30);  // coi lai
                        LocalTime existingEnd = existingShowTime.getHourEnd().plusMinutes(30);
                        // 4 trường hợp - check thử các trường hợp
                        if (existingShowTime.equals("") || existingShowTime == null) {
                            request.setAttribute("notExist", "phòng phim không tồn tại");
                        }
                        if (hourStart.isBefore(existingEnd) && hourEnd.isAfter(existingStart)) {
                            System.out.println("overlap showTime");
                            request.setAttribute("existingShowTime", existingShowTime);
                            overlapchecked = true;
                        }
                    }
                    if (hourStart.isAfter(hourEnd)) {
                        System.out.println("ngu");
                        request.setAttribute("hourStartAfterHourEnd", hourStart.isAfter(hourEnd));
                        overlapchecked = true;
                    }
                    if (!overlapchecked) {
                        Boolean showTime = dao.insertNewShowTime(openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                        request.setAttribute("showTime", showTime);
                        if (showTime) {
                            ShowTimeDTO getLastestShowTime = dao.getLatestShowTime(); 
                            SeatDetailDAO seatDao = new SeatDetailDAO();
                            int numSeatsToAdd = 20;

                            for (char row = 'A'; row <= 'B'; row++) {
                                for (int seatNumber = 0; seatNumber < 10; seatNumber++) {
                                    String seatID = String.format("%c%d", row, seatNumber);
                                    Boolean success = seatDao.addSeat(seatID, true, roomID, getLastestShowTime.getShowTimeID());
                                    if (!success) {
                                        System.out.println("Failed to add seat: " + seatID);
                                    }
                                    numSeatsToAdd--;
                                    if (numSeatsToAdd == 0) {
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Failed to insert show time.");
                        }
                    } else {
                        System.out.println("sai ngay");
                        request.setAttribute("openDateAfterCloseDate", openDate.isAfter(closeDate));
                    }
                    request.getRequestDispatcher("addNewShowTime-Admin.jsp").forward(request, response);

                }
            } catch (StackOverflowError | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input provided");
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                request.setAttribute("timeFormatError", "Sai format của giờ bắt đầu và giờ kêt thúc phim. Nhập lại theo HH:mm format.");
                request.setAttribute("dateFormatError", "Sai format của ngày bắt đầu và ngày kết thúc. Nhập lại theo yyy-MM-dd format.");
                request.getRequestDispatcher("addNewShowTime-Admin.jsp").forward(request, response);
            }
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
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(AddNewShowTimeAdminServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String openDate_raw = request.getParameter("openDate").trim();
//        String closeDate_raw = request.getParameter("closeDate").trim();
//        String hourStart_raw = request.getParameter("hourStart").trim();
//        String hourEnd_raw = request.getParameter("hourEnd").trim();
//        String showStatus_raw = request.getParameter("showStatus");
//        String roomID_raw = request.getParameter("roomID");
//        String movieID_raw = request.getParameter("movieID");
//        LocalDate openDate;
//        LocalDate closeDate;
//        LocalTime hourStart;
//        LocalTime hourEnd;
//        boolean showStatus;
//        int roomID;
//        int movieID;
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
//            formatter = formatter.withLocale(Locale.US);
//            openDate = LocalDate.parse(openDate_raw, formatter);
//            closeDate = LocalDate.parse(closeDate_raw, formatter);
//            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
//            hourStart = LocalTime.parse(hourStart_raw, formatter2);
//            hourEnd = LocalTime.parse(hourEnd_raw, formatter2);
////            openDate = Date.valueOf(LocalDate.parse(openDate_raw));
////            closeDate = Date.valueOf(LocalDate.parse(closeDate_raw));
////            hourStart = Time.valueOf("hourStart_raw");
////            hourEnd = Time.valueOf("hourEnd_raw");
//            showStatus = Boolean.parseBoolean("showStatus_raw");
//            roomID = Integer.parseInt("roomID_raw");
//            movieID = Integer.parseInt("movieID_raw");
//            ShowTimeDAO dao = new ShowTimeDAO();
//            ShowTimeDTO showTime = dao.insertNewShowTime(openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
//            request.getRequestDispatcher("addNewShowTime-Admin.jsp").forward(request, response);
//        } catch (IllegalArgumentException | SQLException e) {
//            System.out.println("Wrong: ");
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input provided");
//        }
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
//        String openDate_raw = request.getParameter("openDate").trim();
//        String closeDate_raw = request.getParameter("closeDate").trim();
//        String hourStart_raw = request.getParameter("hourStart").trim();
//        String hourEnd_raw = request.getParameter("hourEnd").trim();
//        String showStatus_raw = request.getParameter("showStatus");
//        String roomID_raw = request.getParameter("roomID");
//        String movieID_raw = request.getParameter("movieID");
//        LocalDate openDate;
//        LocalDate closeDate;
//        LocalTime hourStart;
//        LocalTime hourEnd;
//        boolean showStatus;
//        int showTimeID;
//        int roomID;
//        int movieID;
//        boolean overlapchecked = false;
//        try {
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            openDate = LocalDate.parse(openDate_raw, formatter);
//            closeDate = LocalDate.parse(closeDate_raw, formatter);
//
//            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
//            hourStart = LocalTime.parse(hourStart_raw, formatter2);
//            hourEnd = LocalTime.parse(hourEnd_raw, formatter2);
//
//            showStatus = Boolean.parseBoolean(showStatus_raw);
//            roomID = Integer.parseInt(roomID_raw);
//            movieID = Integer.parseInt(movieID_raw);
//
//            ShowTimeDAO dao = new ShowTimeDAO();
//            //SeatDetailDAO seatDao = new SeatDetailDAO();
//
//            // session truyen tu SearchMovieAdminServlet
//            HttpSession session = request.getSession();
//            List<MovieDTO> allMovies = (List<MovieDTO>) session.getAttribute("allMovies");    // list
//            MovieDTO oneMovieByName = (MovieDTO) session.getAttribute("oneMovieByName");      // 1
//
//            List<ShowTimeDTO> existingShowTimes = dao.getShowTimesForRoomAndDateRangeForAddAction(oneMovieByName.getMovieID(), roomID, openDate, closeDate);
//            ShowTimeDTO temp = dao.getShowTime(oneMovieByName.getMovieID(), roomID, openDate, closeDate, hourStart, hourEnd);
//
//            if (existingShowTimes.isEmpty()) {
//                request.setAttribute("notExist", "Phim hoặc rạp phim không tồn tại");
//            }
//            // ngay bat dau < ngay ket thuc
//            if (!openDate.isAfter(closeDate)) {
//                for (ShowTimeDTO existingShowTime : existingShowTimes) {
//                    LocalTime existingStart = existingShowTime.getHourStart().minusMinutes(30);  // coi lai
//                    LocalTime existingEnd = existingShowTime.getHourEnd().plusMinutes(30);
//                    // 4 trường hợp - check thử các trường hợp
//                    if (hourStart.isBefore(existingEnd) && hourEnd.isAfter(existingStart)) {
//                        System.out.println("overlap showTime");
//                        request.setAttribute("existingShowTime", existingShowTime);
//                        overlapchecked = true;
//                    }
//                }
//                if (hourStart.isAfter(hourEnd)) {
//                    System.out.println("ngu");
//                    request.setAttribute("hourStartAfterHourEnd", hourStart.isAfter(hourEnd));
//                    overlapchecked = true;
//                }
//                if (!overlapchecked) {
//                    Boolean showTime = dao.insertNewShowTime(openDate, closeDate, hourStart, hourEnd, showStatus, roomID, oneMovieByName.getMovieID());
//
////                    int numSeatsToAdd = 20;
////
////                    for (char row = 'A'; row <= 'B'; row++) {
////                        for (int seatNumber = 0; seatNumber < 10; seatNumber++) {
////                            String seatID = String.format("%c%d", row, seatNumber);
////                            Boolean success = seatDao.addSeat(seatID, true, roomID, showTimeID);
////                            if (!success) {
////                                System.out.println("add" + numSeatsToAdd);
////                            }
////                            numSeatsToAdd--;
////                            if (numSeatsToAdd == 0) {
////                                break;
////                            }
////                        }
////                    }
//                    request.setAttribute("showTime", showTime);
//                }
//            } else {
//                System.out.println("sai ngay");
//                request.setAttribute("openDateAfterCloseDate", openDate.isAfter(closeDate));
//            }
//            request.getRequestDispatcher("addNewShowTime-Admin.jsp").forward(request, response);
//
//        } catch (StackOverflowError | SQLException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input provided");
//        } catch (DateTimeParseException e) {
//            e.printStackTrace();
//            request.setAttribute("timeFormatError", "Sai format của giờ bắt đầu và giờ kêt thúc phim. Nhập lại theo HH:mm format.");
//            request.setAttribute("dateFormatError", "Sai format của ngày bắt đầu và ngày kết thúc. Nhập lại theo yyy-MM-dd format.");
//            request.getRequestDispatcher("addNewShowTime-Admin.jsp").forward(request, response);
//        }

        } catch (SQLException ex) {
            Logger.getLogger(AddNewShowTimeAdminServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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

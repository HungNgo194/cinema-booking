/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import showTime.ShowTimeDAO;
import showTime.ShowTimeDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ModifyShowTimeAdminServlet", urlPatterns = {"/ModifyShowTimeAdminServlet"})
public class ModifyShowTimeAdminServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModifyShowTimeAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModifyShowTimeAdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String showTimeID_raw = request.getParameter("showTimeID");
        String openDate_raw = request.getParameter("openDate").trim();
        String closeDate_raw = request.getParameter("closeDate").trim();
        String hourStart_raw = request.getParameter("hourStart").trim();
        String hourEnd_raw = request.getParameter("hourEnd").trim();
        String showStatus_raw = request.getParameter("showStatus");
        String roomID_raw = request.getParameter("roomID");
        String movieID_raw = request.getParameter("movieID");
        int showTimeID;
        LocalDate openDate;
        LocalDate closeDate;
        LocalTime hourStart;
        LocalTime hourEnd;
        boolean showStatus;
        int roomID;
        int movieID;
        boolean overlapchecked = false;
        try {
            showTimeID = Integer.parseInt(showTimeID_raw);
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
            
            List<ShowTimeDTO> existingShowTimes = dao.getShowTimesForRoomAndDateRange(roomID, openDate, closeDate);

            List<ShowTimeDTO> getAllShowTimesFromSearch = (List<ShowTimeDTO>) request.getAttribute("getAllShowTimes");
            if (getAllShowTimesFromSearch == null || getAllShowTimesFromSearch.isEmpty()) {
                System.out.println("hello");
            }
            // ngay bat dau < ngay ket thuc
            if (!openDate.isAfter(closeDate)) {
                for (ShowTimeDTO existingShowTime : existingShowTimes) {
                    LocalTime existingStart = existingShowTime.getHourStart();
                    LocalTime existingEnd = existingShowTime.getHourEnd().plusMinutes(30);
                    // 4 trường hợp - check thử các trường hợp
                    if (hourStart.isBefore(existingEnd) && hourEnd.isAfter(existingStart)) {
                        System.out.println("overlap showTime");
                        request.setAttribute("existingShowTime", existingShowTime);
                        request.setAttribute("getAllShowTimesFromSearch", getAllShowTimesFromSearch);
                        //request.getRequestDispatcher("modifyShowTime-Admin.jsp").forward(request, response);
                        overlapchecked = true;
                    }
                }
                if (hourStart.isAfter(hourEnd)) {
                    System.out.println("ngu");
                    request.setAttribute("hourStartAfterHourEnd", hourStart.isAfter(hourEnd));
                    request.setAttribute("getAllShowTimesFromSearch", getAllShowTimesFromSearch);
                    //request.getRequestDispatcher("modifyShowTime-Admin.jsp").forward(request, response);
                    overlapchecked = true;
                }
                if (!overlapchecked) {
                    Boolean showTime = dao.modifyShowTime(openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID, showTimeID);
                    request.setAttribute("showTime", showTime);
                    request.setAttribute("getAllShowTimesFromSearch", getAllShowTimesFromSearch);
                    //request.getRequestDispatcher("modifyShowTime-Admin.jsp").forward(request, response);
                }
            } else {
                System.out.println("sai ngay");
                request.setAttribute("getAllShowTimesFromSearch", getAllShowTimesFromSearch);
                request.setAttribute("openDateAfterCloseDate", openDate.isAfter(closeDate));
                //request.getRequestDispatcher("modifyShowTime-Admin.jsp").forward(request, response);
            }
            request.getRequestDispatcher("modifyShowTime-Admin.jsp").forward(request, response);
        } catch (DateTimeParseException | SQLException e) {
            System.out.println("Wrong: ");
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input provided");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import account.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import movie.MovieDAO;
import movie.MovieDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "addNewMovieAdminServlet", urlPatterns = {"/AddNewMovieAdminServlet"})
public class AddNewMovieAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet addNewMovieAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addNewMovieAdminServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        MovieDAO dao = new MovieDAO();
        HttpSession session = request.getSession();
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");

        String movieName = request.getParameter("movieName");
        String movieContent = request.getParameter("movieContent");
        String actor = request.getParameter("actor");
        String director = request.getParameter("director");
        String age_raw = request.getParameter("age");
        int age = 0;
        String url = "";
        try {
            age = Integer.parseInt(age_raw);
            MovieDTO existingMovie = dao.checkExistMovie(movieName);
            request.setAttribute("existingMovie", existingMovie);
            if (existingMovie == null) {
                Boolean movieCreated = dao.addNewMovie(movieName, movieContent, actor, director, age);
                System.out.println("Create success");
                request.setAttribute("movieCreated", movieCreated);
            } else {
                System.out.println("Phim đã tồn tại, nhập lại");
            }
            url = "addNewMovie-Admin.jsp";
            request.getRequestDispatcher(url).forward(request, response);

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Error : ");
            e.printStackTrace();
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

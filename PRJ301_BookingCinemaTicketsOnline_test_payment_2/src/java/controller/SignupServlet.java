/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import account.AccountDAO;
import account.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import membership.MembershipDAO;
import membership.MembershipDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

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
            out.println("<title>Servlet SignupServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupServlet at " + request.getContextPath() + "</h1>");
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
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String userName = request.getParameter("email");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        HttpSession session = request.getSession();
//        String url = "";
//        try {
//            AccountDAO dao = new AccountDAO();
//            AccountDTO signup = dao.createAnNormalAccount(userName, password, email);
//            session.setAttribute("account", signup);
//            response.sendRedirect("LoginActionServlet");
//        } catch (SQLException e) {
//            System.out.println("SQL: ");
//            e.printStackTrace();
//        }
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("email");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        String url = "";
        try {
            // an
            UUID membershipID = UUID.randomUUID();
            MembershipDAO Mdao = new MembershipDAO();
//
            AccountDAO dao = new AccountDAO();
            AccountDTO signup = dao.createAnNormalAccount(userName, password, email);
            MembershipDTO member = Mdao.createMemberShip(membershipID.toString(), 0, 0, userName);
            session.setAttribute("account", signup);
            response.sendRedirect("LoginActionServlet");
        } catch (SQLException e) {
            System.out.println("SQL: ");
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

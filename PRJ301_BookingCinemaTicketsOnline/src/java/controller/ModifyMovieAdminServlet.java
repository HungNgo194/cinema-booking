/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.MovieDAO;
import movie.MovieDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ModifyMovieAdminServlet", urlPatterns = {"/ModifyMovieAdminServlet"})
public class ModifyMovieAdminServlet extends HttpServlet {

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
        String url = "modifyMovie-Admin.jsp";
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest(request);
                Hashtable<String, String> params = new Hashtable<>();
                String img = null;
                String extension = null;
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                        img = (String) params.get("movieImage");
                    } else {
                        try {
                            String itemName = item.getName();
                            String fileName = itemName.substring(
                                    itemName.lastIndexOf("\\") + 1);
                            String RealPath = getPath() + "/web/img/" + fileName;
                            File saveFile = new File(RealPath);
                            if (saveFile.exists() && !saveFile.isDirectory()) {
                                saveFile.delete();
                                System.out.println("File exists! Delete!!!");
                            }
                            item.write(saveFile);
                            extension = fileName.substring(fileName.lastIndexOf('.'));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                String movieID_raw = params.get("movieID");
                String movieName = params.get("movieName");
                String movieContent = params.get("movieContent");
                String actor = params.get("actor");
                String director = params.get("director");
                String age_raw = params.get("age");
                int age = 0;
                int movieID = 0;
                try {
                    movieID = Integer.parseInt(movieID_raw);
                    age = Integer.parseInt(age_raw);
                    MovieDAO dao = new MovieDAO();
                    MovieDTO existingMovie = dao.checkExistMovieById(movieID);
                    request.setAttribute("existingMovie", existingMovie);
                    if (existingMovie != null) {   // da ton tai
                        boolean modifyMovie = dao.modifyMovie(movieID, movieName, movieContent, actor, director, age, img + extension);
                        request.setAttribute("modifyMovie", modifyMovie);
                    } else {
                        request.setAttribute("ERRORS", "Phim chưa tồn tại, nhập lại !");
                    }
                } catch (NumberFormatException ex) {
                    log("NumberFormatException: " + ex.getMessage());
                    request.setAttribute("ERRORS", "NumberFormatException");
                }
            }
        } catch (FileUploadException ex) {
            log("Error occurred during file upload: " + ex.getMessage());
            request.setAttribute("ERRORS", "An error occurred during file upload.");
        } catch (Exception ex) {
            log("Unexpected error occurred: " + ex.getMessage());
            request.setAttribute("ERRORS", "An unexpected error occurred.");
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    public String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String pathArr[] = fullPath.split("/build/web/WEB-INF/classes/");
        fullPath = pathArr[0];
        return fullPath;
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

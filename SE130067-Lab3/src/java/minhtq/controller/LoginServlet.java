/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtq.user.UserDAO;
import minhtq.user.UserDTO;
import minhtq.utils.HashSHA256;


/**
 *
 * @author admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    static final String INVALED_PAGE = "invalid.html";
    static final String PROCESS_REQUEST_CONTROLLER = "ProcessRequestServlet";
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LoginServlet.class);
    

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
        
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        System.out.println(email);
        System.out.println(password);
        
        try {
            password = HashSHA256.toHexString(HashSHA256.getSHA(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String url = INVALED_PAGE;

        try {
            UserDAO dao = new UserDAO();
            boolean result = dao.checkLogin(email, password);
            UserDTO dto = dao.getAccount(email, password);
            
            if (result) {

                int role = dao.getUserRole(email);
                System.out.println(role);
                if (role == 1) {
                    url = PROCESS_REQUEST_CONTROLLER;
                    LOGGER.info("Login Admin success");
                } else {
                    url = PROCESS_REQUEST_CONTROLLER;
                    LOGGER.info("Login success");
                }

                //mo session 
                HttpSession session = request.getSession();
                session.setAttribute("USERID", email);

                String fullname = dao.getFullName(email);
                session.setAttribute("FULLNAME", fullname);
                
                session.setAttribute("USER", dto);
            }

        } catch (NamingException e) {
            LOGGER.error(e);
        } catch (SQLException e) {
            LOGGER.error(e);
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

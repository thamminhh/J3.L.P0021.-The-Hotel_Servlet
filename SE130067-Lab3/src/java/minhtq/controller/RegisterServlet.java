/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtq.user.UserDAO;
import minhtq.user.UsersRegisterError;
import minhtq.utils.HashSHA256;

/**
 *
 * @author admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String REGISTER_ACCOUNT_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.html";

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RegisterServlet.class);

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

        response.setContentType("text/html;charset=UTF-8");;

        String url = REGISTER_ACCOUNT_PAGE;

        UsersRegisterError errors = new UsersRegisterError();
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        try {
            boolean error = false;

            if (email.trim().length() <= 0) {
                error = true;
                errors.setEmailLengErr("Email khong duoc de trong ");
            }
            if (email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
                error = true;
                errors.setEmailFormatErr("Email contain only one “@” character, not contain other special character");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                error = true;
                errors.setPasswordLengErr("Password phai co kich thuoc tu 6 - 30 ky tu");
            } else if (!confirm.trim().equals(password.trim())) {
                error = true;
                errors.setConfirmNotMatch("Password va confirm phai giong nhau");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                error = true;
                errors.setFullnameLengErr("Fullname phai co kich thuoc tu 2 - 50 ky tu");
            }
            if (error) {
                request.setAttribute("INSERTERR", errors);
            } else {
                password = HashSHA256.toHexString(HashSHA256.getSHA(password));
                UserDAO dao = new UserDAO();
                boolean result = dao.insertAccount(email, password, fullname, "Active");
                dao.insertDefaultRole(email);
                if (result) {
                    
                    LOGGER.info("User create new account suscess");
                    url = LOGIN_PAGE;
                }

            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (SQLException ex) {

            log("CreateAccountServlet_SQL " + ex.getMessage());

            errors.setEmailIsExist(email + " da ton tai");
            request.setAttribute("INSERTERR", errors);

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

            LOGGER.error(ex);
        } catch (NamingException ex) {
            LOGGER.error(ex);
            log("CreateAccountServlet_JNDI " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.error(ex);
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

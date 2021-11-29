/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtq.hotel.HotelDAO;
import minhtq.hotel.HotelDTO;
import minhtq.user.UserDAO;
import minhtq.user.UserDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProcessRequestServlet", urlPatterns = {"/ProcessRequestServlet"})
public class ProcessRequestServlet extends HttpServlet {
    private static final String HOME_PAGE = "homepage.jsp";
    private static final String MEMBER_PAGE = "memberpage.jsp";
    private static final String ADMIN_PAGE = "adminpage.jsp";
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ProcessRequestServlet.class);

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
        String url = HOME_PAGE;
        
         try  {
           
            UserDTO account = null;
            HttpSession session = request.getSession();
            if (session != null) {
                account = (UserDTO) session.getAttribute("USER");
            }
            HotelDAO hoteldao = new HotelDAO();
            hoteldao.getAllHotel();
            List<HotelDTO> result = hoteldao.getList();
            request.setAttribute("RESULT", result);
            if (account == null) {
                url = HOME_PAGE ;
            }
            if (account != null) {
                UserDAO dao = new UserDAO();
                int role = dao.getUserRole(account.getEmail());
                if(role == 1){
                    url = ADMIN_PAGE;
                }if(role == 2){
                    url = MEMBER_PAGE;
                }
            }
            
        }catch (NamingException e) {
            LOGGER.error(e);
        }  catch (SQLException e) {
            LOGGER.error(e);
        }
            finally{
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

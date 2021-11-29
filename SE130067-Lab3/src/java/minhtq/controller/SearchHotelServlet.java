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
@WebServlet(name = "SearchHotelServlet", urlPatterns = {"/SearchHotelServlet"})
public class SearchHotelServlet extends HttpServlet {

    private final String HOME_PAGE = "homepage.jsp";
    private final String MEMBER_PAGE = "memberpage.jsp";
    private final String ADMIN_PAGE = "adminpage.jsp";

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SearchHotelServlet.class);

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

        String searchValue = request.getParameter("txtSearchValue");
        String searchOption = request.getParameter("searchOption");

        try {
            HttpSession session = request.getSession();
            UserDTO account = (UserDTO) session.getAttribute("USER");

            if (account == null) {
                url = HOME_PAGE;
                if (searchValue.trim().length() == 0) {
                    HotelDAO dao = new HotelDAO();
                    dao.getAllHotel();
                    List<HotelDTO> result = dao.getList();
                    request.setAttribute("RESULT", result);
                }
                if (searchValue.trim().length() > 0) {
                    if (searchOption.equalsIgnoreCase("Hotel Name")) {
                        HotelDAO dao = new HotelDAO();
                        dao.getListHotelByName(searchValue);
                        List<HotelDTO> result = dao.getList();
                        request.setAttribute("RESULT", result);
                    }
                    if (searchOption.equalsIgnoreCase("Hotel Address")) {
                        HotelDAO dao = new HotelDAO();
                        dao.getListHotelByAdd(searchValue);
                        List<HotelDTO> result = dao.getList();
                        request.setAttribute("RESULT", result);
                    }
                    if (searchOption.equalsIgnoreCase("Hotel Star")) {
                        HotelDAO dao = new HotelDAO();
                        dao.getListHotelByStar(searchValue);
                        List<HotelDTO> result = dao.getList();
                        request.setAttribute("RESULT", result);
                    }
                }
            }
            if (account != null) {
                UserDAO userdao = new UserDAO();
                int role = userdao.getUserRole(account.getEmail());
                if (role == 1) {
                    url = ADMIN_PAGE;
                    if (searchValue.trim().length() == 0) {
                        HotelDAO dao = new HotelDAO();
                        dao.getAllHotel();
                        List<HotelDTO> result = dao.getList();
                        request.setAttribute("RESULT", result);
                    }
                    if (searchValue.trim().length() > 0) {
                        if (searchOption.equalsIgnoreCase("Hotel Name")) {
                            HotelDAO dao = new HotelDAO();
                            dao.getListHotelByName(searchValue);
                            List<HotelDTO> result = dao.getList();
                            request.setAttribute("RESULT", result);
                        }
                        if (searchOption.equalsIgnoreCase("Hotel Address")) {
                            HotelDAO dao = new HotelDAO();
                            dao.getListHotelByAdd(searchValue);
                            List<HotelDTO> result = dao.getList();
                            request.setAttribute("RESULT", result);
                        }
                        if (searchOption.equalsIgnoreCase("Hotel Star")) {
                            HotelDAO dao = new HotelDAO();
                            dao.getListHotelByStar(searchValue);
                            List<HotelDTO> result = dao.getList();
                            request.setAttribute("RESULT", result);
                        }
                    }
                }
                if (role == 2) {
                    url = MEMBER_PAGE;
                    if (searchValue.trim().length() == 0) {
                        HotelDAO dao = new HotelDAO();
                        dao.getAllHotel();
                        List<HotelDTO> result = dao.getList();
                        request.setAttribute("RESULT", result);
                    }
                    if (searchValue.trim().length() > 0) {
                        if (searchOption.equalsIgnoreCase("Hotel Name")) {
                            HotelDAO dao = new HotelDAO();
                            dao.getListHotelByName(searchValue);
                            List<HotelDTO> result = dao.getList();
                            request.setAttribute("RESULT", result);
                        }
                        if (searchOption.equalsIgnoreCase("Hotel Address")) {
                            HotelDAO dao = new HotelDAO();
                            dao.getListHotelByAdd(searchValue);
                            List<HotelDTO> result = dao.getList();
                            request.setAttribute("RESULT", result);
                        }
                        if (searchOption.equalsIgnoreCase("Hotel Star")) {
                            HotelDAO dao = new HotelDAO();
                            dao.getListHotelByStar(searchValue);
                            List<HotelDTO> result = dao.getList();
                            request.setAttribute("RESULT", result);
                        }
                    }
                }
            }

        } catch (NamingException ex) {
            LOGGER.error(ex);
        } catch (SQLException ex) {
            LOGGER.error(ex);
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

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtq.hotel.HotelDAO;
import minhtq.hotel.HotelDTO;
import minhtq.room.RoomDAO;
import minhtq.room.RoomDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "ViewDetailServlet", urlPatterns = {"/ViewDetailServlet"})
public class ViewDetailServlet extends HttpServlet {
    
     private final String VIEW_DETAIL_PAGE = "viewroom.jsp";

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
        
        String url = VIEW_DETAIL_PAGE;
        
        String hotelIDString = request.getParameter("txtHotelID").trim();
        int hotelID = Integer.parseInt(hotelIDString);

        try {
            HotelDAO hotelDAO = new HotelDAO();
            HotelDTO hotelDTO = new HotelDTO();
            
            hotelDTO = hotelDAO.getHotelByID(hotelID);
            request.setAttribute("HOTEL", hotelDTO);
            
            RoomDAO roomDAO = new RoomDAO();
            roomDAO.getListRoomByHotelID(hotelID);
            List<RoomDTO> cmtDTO = roomDAO.getList();
            request.setAttribute("ROOM", cmtDTO);

        } catch (NamingException ex) {
             Logger.getLogger(ViewDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ViewDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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

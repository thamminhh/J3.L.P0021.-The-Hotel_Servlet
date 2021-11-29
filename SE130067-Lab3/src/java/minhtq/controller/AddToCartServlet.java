/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
import minhtq.cart.CartObjectDTO;
import minhtq.hotel.HotelDAO;
import minhtq.hotel.HotelDTO;
import minhtq.room.RoomDAO;
import minhtq.room.RoomDTO;
import minhtq.user.UserDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String VIEW_ROOM_PAGE = "viewroom.jsp";

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AddToCartServlet.class);

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

        String url = VIEW_ROOM_PAGE;
        
        String hotelIDString = request.getParameter("txtHotelID").trim();
        int hotelID = Integer.parseInt(hotelIDString);

        String roomIDString = request.getParameter("txtRoomID").trim();
        int roomID = Integer.parseInt(roomIDString);

        String option = request.getParameter("searchRoomOption");
        
        System.out.println(hotelID);

        String checkInDate = request.getParameter("txtCheckInDate");
        String checkOutDate = request.getParameter("txtCheckOutDate");
        
        if (checkInDate.length() > 0) {

            String[] checkIn = checkInDate.split("/");
            String checkInDay = checkIn[1] + " " + checkIn[0] + " " + checkIn[2];
            String[] checkOut = checkOutDate.split("/");
            String checkOutDay = checkOut[1] + " " + checkOut[0] + " " + checkOut[2];

            System.out.println(checkInDay);
            System.out.println(checkOutDay);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
            String inputString1 = checkInDay;
            String inputString2 = checkOutDay;

            LocalDate date1 = LocalDate.parse(inputString1, dtf);
            LocalDate date2 = LocalDate.parse(inputString2, dtf);
            long daysBetween = ChronoUnit.DAYS.between(date2, date1);
            System.out.println("Days: " + daysBetween);
            long days = daysBetween * -1;
            if (days < 0) {
                String inputdayErr = "Please choose valid Day";
                request.setAttribute("INPUTDAYERR", inputdayErr);
            }
            if (days > 0) {
                request.setAttribute("DAYS", days);
            }
        }

        try {
            HotelDAO hotelDAO = new HotelDAO();
            HotelDTO hotelDTO = new HotelDTO();
            
            hotelDTO = hotelDAO.getHotelByID(hotelID);
            request.setAttribute("HOTEL", hotelDTO);

            HttpSession session = request.getSession();
            UserDTO account = (UserDTO) session.getAttribute("USER");
            if (account != null) {
                CartObjectDTO cart = (CartObjectDTO) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObjectDTO();
                }
                RoomDAO dao = new RoomDAO();
                RoomDTO dto = dao.getRoomByID(roomID);
                cart.addRoomToCart(dto);
                session.setAttribute("CART", cart);

                url = "MainController?"
                        + "btAction=Search Room"
                        + "&searchRoomOption=" + option;

                LOGGER.info(account.getEmail() + " Add to cart");

            } else {
                CartObjectDTO cart = (CartObjectDTO) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObjectDTO();
                }
                RoomDAO dao = new RoomDAO();
                RoomDTO dto = dao.getRoomByID(roomID);
                cart.addRoomToCart(dto);
                session.setAttribute("CART", cart);

                url = "MainController?"
                        + "btAction=Search Room"
                        + "&searchRoomOption=" + option;

                LOGGER.info("End-User Add to cart");
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

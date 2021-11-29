/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
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
import minhtq.BookingHistory.BookingHistoryDAO;
import minhtq.BookingHistory.BookingHistoryDTO;
import minhtq.BookingHistory.CartDetailDTO;
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
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    private final String VIEW_CART_PAGE = "viewcart.jsp";
    private final String LOGIN_PAGE = "login.html";
    private final String BOOKING_SUCCESS_PAGE = "bookingsuccess.html";

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BookingServlet.class);

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
        String url = VIEW_CART_PAGE;

        HttpSession session = request.getSession();

        String inputdayErr;
        String checkInDate = request.getParameter("txtCheckInDate");
        String checkOutDate = request.getParameter("txtCheckOutDate");

        String status = "Booking";
        long millis = System.currentTimeMillis();
        java.sql.Date bookingDate = new java.sql.Date(millis);

        if (checkInDate.length() > 0 && checkOutDate.length() > 0) {

            String[] checkIn = checkInDate.split("/");
            String checkInDay = checkIn[1] + " " + checkIn[0] + " " + checkIn[2];
            String[] checkOut = checkOutDate.split("/");
            String checkOutDay = checkOut[1] + " " + checkOut[0] + " " + checkOut[2];

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
            String inputString1 = checkInDay;
            String inputString2 = checkOutDay;

            LocalDate date1 = LocalDate.parse(inputString1, dtf);
            LocalDate date2 = LocalDate.parse(inputString2, dtf);
            long daysBetween = ChronoUnit.DAYS.between(date2, date1);
            
            long days = daysBetween * -1;
            if (days < 0) {
                inputdayErr = "Please choose valid Day";
                request.setAttribute("INPUTDAYERR", inputdayErr);
            }
            if (days > 0) {
                request.setAttribute("DAYS", days);
            }
        } else {
            if (checkOutDate.length() == 0 && checkInDate.length() == 0) {
                inputdayErr = "Please choose Check in and out date";
                request.setAttribute("INPUTDAYERR", inputdayErr);
            }
            if (checkInDate.length() == 0 && checkOutDate.length() > 0) {
                inputdayErr = "Please choose Check in date";
                request.setAttribute("INPUTDAYERR", inputdayErr);

            }
            if (checkOutDate.length() == 0 && checkInDate.length() > 0) {
                inputdayErr = "Please choose Check out date";
                request.setAttribute("INPUTDAYERR", inputdayErr);
            }
        }
        try {
//            HttpSession session = request.getSession();
            UserDTO account = (UserDTO) session.getAttribute("USER");

            if (account == null) {
                url = LOGIN_PAGE;
            }
            if (account != null) {

                java.util.Date utilCheckInDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkInDate);
                java.sql.Date sqlCheckInDate = new java.sql.Date(utilCheckInDate.getTime());
                java.util.Date utilCheckOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkOutDate);
                java.sql.Date sqlCheckOutDate = new java.sql.Date(utilCheckOutDate.getTime());

                BookingHistoryDAO bookingDAO = new BookingHistoryDAO();
                BookingHistoryDTO bookingDTO = new BookingHistoryDTO(account.getEmail(), status, bookingDate, sqlCheckInDate, sqlCheckOutDate);
                boolean result = bookingDAO.updateHistory(bookingDTO);
                if (result) {

                    CartObjectDTO cartObjectDTO = (CartObjectDTO) session.getAttribute("CART");
                    Map<Integer, RoomDTO> items = cartObjectDTO.getItems();
                    for (Map.Entry<Integer, RoomDTO> entry : items.entrySet()) {
                        Integer key = entry.getKey();
                        RoomDTO value = entry.getValue();

                        BookingHistoryDAO dao = new BookingHistoryDAO();
                        int cartID = dao.getMaxCartID();

                        CartDetailDTO dto = new CartDetailDTO(cartID, value.getRoomID(), value.getAmount());
                        boolean detailResult = dao.updateCartDetail(dto.getCardID(), dto.getRoomID(), dto.getQuantity());

                        if(detailResult){
                            url = BOOKING_SUCCESS_PAGE;
                            
                            
                            dao.getHistoryByEmail(account.getEmail());
                            List<BookingHistoryDTO> bookingList = dao.getList();
                            request.setAttribute("HISTORY", bookingList);
                            
                            dao.getRoomDetailByCartID(dto.getCardID());
                            List<CartDetailDTO> roomList = dao.getListDetail();
                            request.setAttribute("ROOMBOOKED", roomList);
                            
                        }
                    }
                }
            }

        } catch (ParseException ex) {
            LOGGER.error(ex);
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

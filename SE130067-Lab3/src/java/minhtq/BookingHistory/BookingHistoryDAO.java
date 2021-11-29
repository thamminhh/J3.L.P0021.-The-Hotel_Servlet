/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.BookingHistory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtq.hotel.HotelDTO;
import minhtq.utils.DBHealper;

/**
 *
 * @author admin
 */
public class BookingHistoryDAO {

    List<BookingHistoryDTO> bookingList;
    List<CartDetailDTO> detailList;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<BookingHistoryDTO> getList() {
        return bookingList;
    }

    public List<CartDetailDTO> getListDetail() {
        return detailList;
    }

    private void closeDB() throws NamingException, SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean updateHistory(BookingHistoryDTO dto) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "Insert into Cart(email, status, booking_date, check_in_date, check_out_date) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getStatus());
                stm.setDate(3, dto.getBookingdate());
                stm.setDate(4, dto.getCheckInDate());
                stm.setDate(5, dto.getCheckOutDate());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeDB();
        }
        return false;
    }

    public int getMaxCartID() throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {

                Statement stmt = null;
                stmt = con.createStatement();
                    String sql = "SELECT MAX(cart_id) FROM Cart";
                rs = stmt.executeQuery(sql);
                //Extact result from ResultSet rs
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            closeDB();
        }
        return -1;
    }

    public boolean updateCartDetail(int cartID, int roomID, int quantity) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "Insert into Cart_detail (cart_id, room_id, quantity) "
                        + "Values(?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, cartID);
                stm.setInt(2, roomID);
                stm.setInt(3, quantity);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeDB();
        }
        return false;
    }

    public void getHistoryByEmail(String email) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT cart_id, status, booking_date, check_in_date, check_out_date "
                        + "FROM Cart "
                        + "Where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int cartID = rs.getInt("card_id");
                    String status = rs.getString("status");
                    Date bookingDate = rs.getDate("booking_date");
                    Date checkInDate = rs.getDate("check_in_date");
                    Date checkOutdate = rs.getDate("check_out_date");
                    BookingHistoryDTO dto = new BookingHistoryDTO(email, status, bookingDate, checkInDate, checkOutdate);
                    if (bookingList == null) {
                        this.bookingList = new ArrayList<>();
                    }
                    this.bookingList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }

    public void getRoomDetailByCartID(int cartID) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT cart_id, room_id, quantity"
                        + "FROM Cart_detail "
                        + "Where cartID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, cartID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int roomID = rs.getInt("room_id");
                    int quantity = rs.getInt("quantity");
                    CartDetailDTO dto = new CartDetailDTO(cartID, roomID, quantity);
                    if (detailList == null) {
                        this.detailList = new ArrayList<>();
                    }
                    this.detailList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }
}

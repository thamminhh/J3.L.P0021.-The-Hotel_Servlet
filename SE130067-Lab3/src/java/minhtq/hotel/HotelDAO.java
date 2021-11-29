/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.hotel;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtq.utils.DBHealper;

/**
 *
 * @author admin
 */
public class HotelDAO implements Serializable{

    List<HotelDTO> hotelList;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<HotelDTO> getList() {
        return hotelList;
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

    public void getAllHotel() throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT hotel_id, hotel_name, hotel_address, star "
                        + "FROM Hotel";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int hotelID = rs.getInt("hotel_id");
                    String hotelName = rs.getString("hotel_name");
                    String address = rs.getString("hotel_address");
                    int star = rs.getInt("star");
                    HotelDTO dto = new HotelDTO(hotelID, hotelName, address, star);
                    if (hotelList == null) {
                        this.hotelList = new ArrayList<>();
                    }
                    this.hotelList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }

    public void getListHotelByName(String name) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            System.out.println(name);
            if (con != null) {
                String sql = "SELECT hotel_id, hotel_name, hotel_address, star "
                        + "FROM Hotel "
                        + "Where hotel_name LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int hotelID = rs.getInt("hotel_id");
                    String hotelName = rs.getString("hotel_name");
                    String address = rs.getString("hotel_address");
                    int star = rs.getInt("star");
                    HotelDTO dto = new HotelDTO(hotelID, hotelName, address, star);
                    if (hotelList == null) {
                        this.hotelList = new ArrayList<>();
                    }
                    this.hotelList.add(dto);
                }
            }
        } finally {
            closeDB();
        }

    }

    public void getListHotelByAdd(String add) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT hotel_id, hotel_name, hotel_address, star "
                        + "FROM Hotel "
                        + "Where hotel_address like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, add);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int hotelID = rs.getInt("hotel_id");
                    String hotelName = rs.getString("hotel_name");
                    String address = rs.getString("hotel_address");
                    int star = rs.getInt("star");
                    HotelDTO dto = new HotelDTO(hotelID, hotelName, address, star);
                    if (hotelList == null) {
                        this.hotelList = new ArrayList<>();
                    }
                    this.hotelList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }

    public void getListHotelByStar(String searchValue) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT hotel_id, hotel_name, hotel_address, star "
                        + "FROM Hotel "
                        + "Where star like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, searchValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int hotelID = rs.getInt("hotel_id");
                    String hotelName = rs.getString("hotel_name");
                    String address = rs.getString("hotel_address");
                    int star = rs.getInt("star");
                    HotelDTO dto = new HotelDTO(hotelID, hotelName, address, star);
                    if (hotelList == null) {
                        this.hotelList = new ArrayList<>();
                    }
                    this.hotelList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }

    public HotelDTO getHotelByID(int hotelID) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT hotel_id, hotel_name, hotel_address, star "
                        + "FROM Hotel "
                        + "Where hotel_id like ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, hotelID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String hotelName = rs.getString("hotel_name");
                    String address = rs.getString("hotel_address");
                    int star = rs.getInt("star");
                    HotelDTO dto = new HotelDTO(hotelID, hotelName, address, star);
                    return dto;
                }
            }
        } finally {
            closeDB();
        }
        return null;
    }

}

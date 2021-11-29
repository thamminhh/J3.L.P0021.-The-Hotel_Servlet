/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.room;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtq.hotel.HotelDTO;
import minhtq.utils.DBHealper;

/**
 *
 * @author admin
 */
public class RoomDAO implements Serializable{
    
     List<RoomDTO> roomList;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<RoomDTO> getList() {
        return roomList;
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
    
    public void getListRoomByHotelID(int hotelID) throws NamingException, SQLException{
        try{
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "SELECT room_id, hotel_id, room_name, kind_of_room, amount, price, status "
                        + "From Room "
                        + "Where hotel_id = ? "
                        + "And status = 'Available'";  
                stm = con.prepareStatement(sql);
                stm.setInt(1, hotelID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int roomID = rs.getInt("room_id");
                    String roomName = rs.getString("room_name");
                    String kindOfRoom = rs.getString("kind_of_room");
                    int amount = rs.getInt("amount");
                    double price = rs.getDouble("price");
                    String status = rs.getString("status");
                    RoomDTO dto = new RoomDTO(roomID, hotelID, roomName, kindOfRoom, amount, price, status);
                    if (roomList == null) {
                        this.roomList = new ArrayList<>();
                    }
                    this.roomList.add(dto);
                }
            }
        }finally{
            closeDB();
        }
    }
    
    public void getListRoomByKindOfRoom(int hotelID, String kindOfRoom) throws NamingException, SQLException{
        try{
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "SELECT room_id, hotel_id, room_name, kind_of_room, amount, price, status "
                        + "From Room "
                        + "Where hotel_id = ? "
                        + "And kind_of_room = ? "
                        + "And status = 'Available' " ; 
                stm = con.prepareStatement(sql);
                stm.setInt(1, hotelID);
                stm.setString(2, kindOfRoom);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int roomID = rs.getInt("room_id");
                    String roomName = rs.getString("room_name");
                    int amount = rs.getInt("amount");
                    double price = rs.getDouble("price");
                    String status = rs.getString("status");
                    RoomDTO dto = new RoomDTO(roomID, hotelID, roomName, kindOfRoom, amount, price, status);
                    if (roomList == null) {
                        this.roomList = new ArrayList<>();
                    }
                    this.roomList.add(dto);
                }
            }
        }finally{
            closeDB();
        }
    }
    
    public RoomDTO getRoomByID(int roomID) throws NamingException, SQLException{
        try{
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "SELECT room_id, hotel_id, room_name, kind_of_room, amount, price, status "
                        + "From Room "
                        + "Where room_id = ? "
                        + "And status = 'Available'";  
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int hotelID = rs.getInt("hotel_id");
                    String roomName = rs.getString("room_name");
                    String kindOfRoom = rs.getString("kind_of_room");
                    int amount = rs.getInt("amount");
                    double price = rs.getDouble("price");
                    String status = rs.getString("status");
                    RoomDTO dto = new RoomDTO(roomID, hotelID, roomName, kindOfRoom, amount, price, status);
                    return dto;
                }
            }
        }finally{
            closeDB();
        }
        return null;
    }
    
    public int getAmountByRoomID(int roomID) throws NamingException, SQLException{
        try{
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "SELECT amount FROM Room "
                        + "WHERE room_id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomID);
                rs = stm.executeQuery();
                while (rs.next()){
                    int amount = rs.getInt("amount");
                    return amount;
                }
            }
        }finally{
            closeDB();
        }
        return -1;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.room;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class RoomDTO implements Serializable{
    int roomID;
    int hotelID;
    String roomName;
    String kindOfRoom;
    int amount;
    double price ;
    String status;
    Date checkInDate;
    Date checkOutDate;

    public RoomDTO() {
    }

    public RoomDTO(int roomID, int hotelID, String roomName, String kindOfRoom, int amount, double price, String status, Date checkInDate, Date checkOutDate) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomName = roomName;
        this.kindOfRoom = kindOfRoom;
        this.amount = amount;
        this.price = price;
        this.status = status;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public RoomDTO(int roomID, int hotelID, String roomName, String kindOfRoom, int amount, double price, String status) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomName = roomName;
        this.kindOfRoom = kindOfRoom;
        this.amount = amount;
        this.price = price;
        this.status = status;
    }

    public RoomDTO(int roomID, String roomName, String kindOfRoom, int amount, double price) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.kindOfRoom = kindOfRoom;
        this.amount = amount;
        this.price = price;
    }
    
    
    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getKindOfRoom() {
        return kindOfRoom;
    }

    public void setKindOfRoom(String kindOfRoom) {
        this.kindOfRoom = kindOfRoom;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    
    
}

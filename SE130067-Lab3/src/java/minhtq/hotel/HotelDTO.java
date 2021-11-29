/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.hotel;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class HotelDTO implements Serializable{
    private int hotelID;
    private String hotelName;
    private String address;
    int star;

    public HotelDTO() {
    }

    public HotelDTO(int hotelID, String hotelName, String address, int star) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.address = address;
        this.star = star;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
    
    
    
}

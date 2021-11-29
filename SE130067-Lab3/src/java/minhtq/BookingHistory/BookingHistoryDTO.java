/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.BookingHistory;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class BookingHistoryDTO {
    String email;
    String status;
    Date bookingdate;
    Date checkInDate;
    Date checkOutDate;

    public BookingHistoryDTO() {
    }

    public BookingHistoryDTO(String email, String status, Date bookingdate, Date checkInDate, Date checkOutDate) {
        this.email = email;
        this.status = status;
        this.bookingdate = bookingdate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
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

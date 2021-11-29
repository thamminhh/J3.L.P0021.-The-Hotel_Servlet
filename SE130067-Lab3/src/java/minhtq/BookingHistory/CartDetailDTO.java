/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.BookingHistory;

/**
 *
 * @author admin
 */
public class CartDetailDTO {
    int cardID;
    int roomID;
    int quantity;

    public CartDetailDTO() {
    }

    public CartDetailDTO(int cardID, int roomID, int quantity) {
        this.cardID = cardID;
        this.roomID = roomID;
        this.quantity = quantity;
    }

    public CartDetailDTO(int roomID, int quantity) {
        this.roomID = roomID;
        this.quantity = quantity;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import minhtq.room.RoomDTO;


/**
 *
 * @author Khoa Pham
 */
public class CartObjectDTO implements Serializable{
    
    private Map<Integer, RoomDTO> items;
    
    public CartObjectDTO() {
    }
    
    public CartObjectDTO(Map<Integer, RoomDTO> items) {
        this.items = items;
    }
    
    public Map<Integer, RoomDTO> getItems() {
        return items;
    }
    
    public void setItems(Map<Integer, RoomDTO> items) {
        this.items = items;
    }
    

    public void addRoomToCart(RoomDTO dto) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        if (this.items.containsKey(dto.getRoomID())) {
            int tmp = dto.getAmount()- items.get(dto.getRoomID()).getAmount();
            dto.setAmount(dto.getAmount()- tmp + 1);
        } else {
            dto.setAmount(1);
        }
        this.items.put(dto.getRoomID(), dto);
    }
    
    public void removeRoomFromCart(int roomID) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(roomID)) {
            items.remove(roomID);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public void updateCart(RoomDTO dto){
        if(items != null){
            if(items.containsKey(dto.getRoomID())){
                this.items.replace(dto.getRoomID(), dto);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static model.ActiveEnum.*;

public class Seat {
    private int seatNum;
    private ActiveEnum isActive = ACTIVE;
    
    public Seat(){
        
    }

    public Seat(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
    
    public void insertPassenger(){
        this.isActive = INACTIVE;
    }
}

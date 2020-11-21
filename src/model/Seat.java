/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static model.UsedEnum.*;

public class Seat {
    private String idSeat;
    private int seatNum;
    private UsedEnum isUsed;
    
    public Seat(){
        
    }

    public Seat(String idSeat, int seatNum, UsedEnum isUsed) {
        this.idSeat = idSeat;
        this.seatNum = seatNum;
        this.isUsed = isUsed;
    }

    public String getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(String idSeat) {
        this.idSeat = idSeat;
    }
    
    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public UsedEnum getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(UsedEnum isUsed) {
        this.isUsed = isUsed;
    }
    
}

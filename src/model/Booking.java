/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static model.ActiveEnum.*;
import static model.PaidEnum.*;
import java.util.ArrayList;

public class Booking {
    private String idBooking;
    private String dateBooking;
    private int totalPrice;
    private JadwalPesawat schedule = null;
//    public Passenger[] passenger = new Passenger[Global.getPassengerAmount()-1];
    private PaidEnum isPaid;
    private ActiveEnum isActive;
    
    public Booking(){
        
    }

    public Booking(String idBooking, String dateBooking, int totalPrice, PaidEnum isPaid, ActiveEnum isActive) {
        this.idBooking = idBooking;
        this.dateBooking = dateBooking;
        this.totalPrice = totalPrice;
        this.isPaid = isPaid;
        this.isActive = isActive;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void insertSchedule(JadwalPesawat schedule){
        this.isActive = ACTIVE;
        this.schedule = schedule;
    }

    public JadwalPesawat getSchedule() {
        return schedule;
    }

    public void setSchedule(JadwalPesawat schedule) {
        this.schedule = schedule;
    }

//    public Passenger[] getPassenger() {
//        return passenger;
//    }
//
//    public void setPassenger(Passenger[] passenger) {
//        this.passenger = passenger;
//    }
    
    public PaidEnum isPaid() {
        return isPaid;
    }

    public void setIsPaid(PaidEnum isPaid) {
        this.isPaid = isPaid;
    }
    
    public ActiveEnum isActive() {
        return isActive;
    }

    public void setIsActive(ActiveEnum isActive) {
        this.isActive = isActive;
    }
    
    public void deactivateTransaction() {
        this.isActive = INACTIVE;
    }
    
//    public void setEachPassenger(int i, Passenger p){
//        passenger[i] = p;
//    }
}

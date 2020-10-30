/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Booking {
    private String idBooking;
    private String dateBooking;
    private int totalPrice;
    private JadwalPesawat schedule;
    ArrayList<Passenger> passenger = new ArrayList<Passenger>();
    
    public Booking(){
        
    }
    
    public Booking(String idBooking, String dateBooking, int totalPrice) {
        this.idBooking = idBooking;
        this.dateBooking = dateBooking;
        this.totalPrice = totalPrice;
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
    
    
}

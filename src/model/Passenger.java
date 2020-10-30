/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Passenger {
    private String fullName;
    private String title;
    private Seat seat;
    private int baggagePerKg;
    private String nationality;

    public Passenger(){
        
    }
    
    public Passenger(String fullName, String title, Seat seat, int baggagePerKg, String nationality) {
        this.fullName = fullName;
        this.title = title;
        this.seat = seat;
        this.baggagePerKg = baggagePerKg;
        this.nationality = nationality;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getBaggagePerKg() {
        return baggagePerKg;
    }

    public void setBaggagePerKg(int baggagePerKg) {
        this.baggagePerKg = baggagePerKg;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    
}

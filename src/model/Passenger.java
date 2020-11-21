/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Passenger {
    private String idPassenger;
    private String fullName;
    private String panggilan;
    private Seat seat;
    private int bagasiPerKg;
    private String nationality;

    public Passenger(){
        
    }
    
    public Passenger(String idPassenger, String fullName, String panggilan, Seat seat, int bagasiPerKg, String nationality) {
        this.idPassenger = idPassenger;
        this.fullName = fullName;
        this.panggilan = panggilan;
        this.seat = seat;
        this.bagasiPerKg = bagasiPerKg;
        this.nationality = nationality;
    }

    public String getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(String idPassenger) {
        this.idPassenger = idPassenger;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPanggilan() {
        return panggilan;
    }

    public void setPanggilan(String panggilan) {
        this.panggilan = panggilan;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getBagasiPerKg() {
        return bagasiPerKg;
    }

    public void setBagasiPerKg(int bagasiPerKg) {
        this.bagasiPerKg = bagasiPerKg;
    }
    
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    
}

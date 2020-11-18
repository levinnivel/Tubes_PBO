/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Member extends User{
    private String address;
    private int point;
    private int balance;
    ArrayList<Booking> bookings = new ArrayList<Booking>();
    
    public Member(){
    }

    public Member(String email, String fullName, String password, String phoneNum, String address, int point, int balance, ArrayList<Booking> bookings) {
        super(email, fullName, password, phoneNum);
        this.address = address;
        this.point = point;
        this.balance = balance;
        this.bookings = bookings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }
    
}

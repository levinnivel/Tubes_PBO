/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SKY-PC
 */
public class Member extends User{
    private String address;
    private int point;
    
    public Member(){
    }
    
    public Member(String email, String fullName, String password, String phoneNum, String address, int point){
        super(email, fullName, password, phoneNum);
        this.address = address;
        this.point = point;
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
    
    
}

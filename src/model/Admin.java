/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Admin extends User{
    private String kodeAdmin;
    private int payroll;
    
    public Admin(){
        
    }

    public Admin(String email, String fullName, String password, String phoneNum, String kodeAdmin, int payroll) {
        super(email, fullName, password, phoneNum);
        this.kodeAdmin = kodeAdmin;
        this.payroll = payroll;
    }

    public String getKodeAdmin() {
        return kodeAdmin;
    }

    public int getPayroll() {
        return payroll;
    }

    public void setKodeAdmin(String kodeAdmin) {
        this.kodeAdmin = kodeAdmin;
    }

    public void setPayroll(int payroll) {
        this.payroll = payroll;
    }

}

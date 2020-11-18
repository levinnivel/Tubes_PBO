/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Payment {
    private String paymentType;
    private int amount;
    
    public Payment(){
        
    }

    public Payment(String paymentType, int amount) {
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void paymentByTransfer(){
        
    }
    
    public void paymentByOVOGopay(){
        
    }
    
    public void paymentByAlfamart(){
        
    }
}

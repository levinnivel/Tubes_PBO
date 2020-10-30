/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Payment {
    private String paymentType;
    
    public Payment(){
        
    }

    public Payment(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    public void paymentByTransfer(){
        
    }
    
    public void paymentByOVOGopay(){
        
    }
    
    public void paymentByAlfamart(){
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Global{
    private static int passengerAmount = 1;
    private static int passengerCounter = 0;

    public static int getPassengerAmount(){
        return Global.passengerAmount;
    }

    //If you do not want to change the var ever then do not include this
    public static void setPassengerAmount(int passengerAmount){
        Global.passengerAmount = passengerAmount;
    }

    public static int getPassengerCounter() {
        return passengerCounter;
    }

    public static void setPassengerCounter(int passengerCounter) {
        Global.passengerCounter = passengerCounter;
    }
    
}
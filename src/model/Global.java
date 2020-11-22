/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//variabel yang dibutuhkan oleh beberapa kelas tapi tidak berupa model
public class Global{
    //jumlah penumpang
    private static int passengerAmount = 1;
    //untuk menghitung looping frame pada passenger screen
    private static int passengerCounter = 0;
    private static int totalPrice = 0;
    private static String selectedIDSchedule;

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

    public static String getSelectedIDSchedule() {
        return selectedIDSchedule;
    }

    public static void setSelectedIDSchedule(String selectedIDSchedule) {
        Global.selectedIDSchedule = selectedIDSchedule;
    }

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        Global.totalPrice = totalPrice;
    }
    
}
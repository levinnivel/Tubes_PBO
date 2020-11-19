/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu5;

import controller.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;
import view.*;

public class RefundScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    Booking booking = BookingManager.getInstance().getBooking();
    
    JFrame refFrame = new JFrame();
    
    JLabel labTitle = new JLabel("Cancel Your Booking");
    JLabel labCartDesc = new JLabel("Your Booking");
    JLabel labPoint = new JLabel("Your Points : ");
    JLabel labIDBooking = new JLabel("ID Booking : ");
    JLabel labPrice = new JLabel("Total Price : ");
    JLabel labPointValue;
    JLabel labIDValue;
    JLabel labPriceValue;
    JLabel labPayMeth = new JLabel("Payment Method  : ");
    JLabel labPayAmount = new JLabel("Amount                   : ");
    JTextField tfPayAmount = new JTextField();
    JLabel labBalance = new JLabel("Balance                  : ");
    JLabel labBalanceAmount;
    JRadioButton rmethod1 = new JRadioButton("Transfer ATM");
    JRadioButton rmethod2 = new JRadioButton("OVO");
    JRadioButton rmethod3 = new JRadioButton("GO-PAY");
    JRadioButton rmethod4 = new JRadioButton("Alfamart");
    JRadioButton rmethod5 = new JRadioButton("Indomaret");
    JRadioButton rmethod6 = new JRadioButton("Wagon-Pay");
    ButtonGroup methodTypes = new ButtonGroup();
    JButton buttonCancel = new JButton("Refund/Cancel");
    
    public RefundScreen(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Search":
                break;
        }
    }
}

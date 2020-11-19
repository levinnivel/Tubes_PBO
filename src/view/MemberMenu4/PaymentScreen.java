/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu4;

import static model.ActiveEnum.*;
import view.*;
import view.MemberMenu1.*;
import view.MemberMenu2.*;
import view.MemberMenu3.*;
import view.MemberMenu4.*;
import view.MemberMenu5.*;
import view.GuestMenu.*;
import model.*;
import controller.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class PaymentScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    Booking booking = BookingManager.getInstance().getBooking();
    
    JFrame payFrame = new JFrame();
    
    JLabel labTitle = new JLabel("Payment Bill");
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
    JButton buttonPay = new JButton("Pay Now");
    JButton buttonBack = new JButton("Back");
    
    public PaymentScreen(){
        payFrame.setSize(500,500);
        payFrame.setLocationRelativeTo(null);
        payFrame.setLayout(null);
        payFrame.setVisible(true);
        
        String strPoint = Integer.toString(member.getPoint());
        String strBalance = Integer.toString(member.getBalance());
        String strIDBook = booking.getIdBooking();
        String strTotal =  Integer.toString(booking.getTotalPrice());
        labPointValue = new JLabel(strPoint);
        labIDValue = new JLabel(strIDBook);
        labPriceValue = new JLabel(strTotal);
        
        labTitle.setBounds(150,30, 200,20);
        
        labCartDesc.setBounds(50,70, 100,20);
        labPoint.setBounds(50,90, 100,20);
        labIDBooking.setBounds(80,110, 100,20);
        labPrice.setBounds(80,130, 100,20);
        labPointValue.setBounds(200,90, 100,20);
        labIDValue.setBounds(200,110, 100,20);
        labPriceValue.setBounds(200,130, 100,20);
        
        labPayMeth.setBounds(50,200, 150,30);
        labPayAmount.setBounds(50,280, 150,30);
        
        methodTypes.add(rmethod1);
        methodTypes.add(rmethod2);
        methodTypes.add(rmethod3);
        methodTypes.add(rmethod4);
        methodTypes.add(rmethod5);
        methodTypes.add(rmethod6);
        rmethod1.setBounds(200,200, 120,25);
        rmethod2.setBounds(200,220, 120,25);
        rmethod3.setBounds(200,240, 120,25);
        rmethod4.setBounds(350,200, 120,25);
        rmethod5.setBounds(350,220, 120,25);
        rmethod6.setBounds(350,240, 120,25);
        tfPayAmount.setBounds(200,280, 200,30);
        
        labBalanceAmount = new JLabel(strBalance);
        labBalance.setBounds(50,280, 150,30);
        labBalanceAmount.setBounds(200,280, 200,30);
        labBalance.setVisible(false);
        labBalanceAmount.setVisible(false);
        
        buttonPay.setBounds(215,340, 100,25);
        buttonPay.setActionCommand("Pay");
        buttonPay.addActionListener(this);
        
        buttonBack.setBounds(215,370, 100,25);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        rmethod6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rmethod6.isSelected()){
                    labPayAmount.setVisible(false);
                    tfPayAmount.setVisible(false);
                    labBalance.setVisible(true);
                    labBalanceAmount.setVisible(true);
                }
            }
        });
        rmethod1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rmethod1.isSelected()){
                    labPayAmount.setVisible(true);
                    tfPayAmount.setVisible(true);
                    labBalance.setVisible(false);
                    labBalanceAmount.setVisible(false);
                }
            }
        });
        rmethod2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rmethod2.isSelected()){
                    labPayAmount.setVisible(true);
                    tfPayAmount.setVisible(true);
                    labBalance.setVisible(false);
                    labBalanceAmount.setVisible(false);
                }
            }
        });
        rmethod3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rmethod3.isSelected()){
                    labPayAmount.setVisible(true);
                    tfPayAmount.setVisible(true);
                    labBalance.setVisible(false);
                    labBalanceAmount.setVisible(false);
                }
            }
        });
        rmethod4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rmethod4.isSelected()){
                    labPayAmount.setVisible(true);
                    tfPayAmount.setVisible(true);
                    labBalance.setVisible(false);
                    labBalanceAmount.setVisible(false);
                }
            }
        });
        rmethod5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rmethod5.isSelected()){
                    labPayAmount.setVisible(true);
                    tfPayAmount.setVisible(true);
                    labBalance.setVisible(false);
                    labBalanceAmount.setVisible(false);
                }
            }
        });
        
        payFrame.add(labTitle);
        payFrame.add(labPoint);
        payFrame.add(labPointValue);
        payFrame.add(labCartDesc);
        payFrame.add(labIDBooking);
        payFrame.add(labPrice);
        payFrame.add(labIDValue);
        payFrame.add(labPriceValue);
        payFrame.add(labPayMeth);
        payFrame.add(labPayAmount);
        payFrame.add(labBalance);
        payFrame.add(labBalanceAmount);
        payFrame.add(rmethod1);
        payFrame.add(rmethod2);
        payFrame.add(rmethod3);
        payFrame.add(rmethod4);
        payFrame.add(rmethod5);
        payFrame.add(rmethod6);
        payFrame.add(tfPayAmount);
        payFrame.add(buttonPay);
        payFrame.add(buttonBack);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Pay":
                conn.connect();
                
                int priceValue = booking.getTotalPrice();
                int pointValue = member.getPoint();
                
                String[] opt = {"Ya", "Tidak"};
                int isUsePoint = JOptionPane.showOptionDialog(payFrame, "Ingin menggunakan poin anda? ", "Pilih", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);

                if(isUsePoint==0){
                    priceValue -= pointValue;
                    if(pointValue<=priceValue){
                        pointValue = 0;
                    }else{
                        pointValue -= priceValue;
                    }
                    member.setPoint(pointValue);
                }
                
                int amountPaid = 0;
                if(rmethod6.isSelected()){
                    amountPaid = Integer.parseInt(labBalanceAmount.getText()) - priceValue; //not the amount, change after being paid
                }else{
                    amountPaid = Integer.parseInt(tfPayAmount.getText()) - priceValue;
                }
                
                boolean isSuccess = false;
                
                if(amountPaid>=0){
                    isSuccess = true;
                }else{
                    isSuccess = false;
                }
                
                if(isSuccess){
                    if(rmethod6.isSelected()){
                        String query = "UPDATE member SET balance='" + amountPaid + "', "
                        + "poinMember='" + member.getPoint() + "' "
                        + "WHERE emailMember='" + member.getEmail() + "'";
                        try {
                            Statement stmt = conn.con.createStatement();
                            stmt.executeUpdate(query);
                            member.setBalance(amountPaid);
                            JOptionPane.showMessageDialog(payFrame,"Balance berhasil diperbaharui!");
                        } catch (SQLException excUp) {
                            excUp.printStackTrace();
                            JOptionPane.showMessageDialog(payFrame, "Balance gagal diperbaharui!", "Payment Error",JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        String query = "UPDATE member SET poinMember='" + member.getPoint() + "', "
                        + "WHERE emailMember='" + member.getEmail() + "'";
                        try {
                            Statement stmt = conn.con.createStatement();
                            stmt.executeUpdate(query);
                            JOptionPane.showMessageDialog(payFrame,"Poin anda sekarang : " + member.getPoint());
                        } catch (SQLException excUp) {
                            excUp.printStackTrace();
                            JOptionPane.showMessageDialog(payFrame, "Poin gagal diperbaharui!", "Payment Error",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    
                    String query = "UPDATE booking SET statusBayar='INACTIVE' "
                        + "WHERE emailMember='" + member.getEmail() + "' "
                        + "AND dateBooking='" + booking.getDateBooking() + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        stmt.executeUpdate(query);
                        booking.setIsInactive(INACTIVE);
                        JOptionPane.showMessageDialog(payFrame,"Pembayaran berhasil!");
                    } catch (SQLException excUp) {
                        excUp.printStackTrace();
                        JOptionPane.showMessageDialog(payFrame, "Pembayaran gagal!", "Payment Error",JOptionPane.WARNING_MESSAGE);
                    }
                    
                    BookingManager.getInstance().setBooking(null);
                    
                    conn.disconnect();
                    
                    payFrame.dispose();
                    new GuestMemberMenu();
                }else{
                    JOptionPane.showMessageDialog(payFrame, "Pembayaran belum mencukupi!", "Register Error",JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "Back":
                payFrame.dispose();
                new GuestMemberMenu();
                break;
        }
    }
}

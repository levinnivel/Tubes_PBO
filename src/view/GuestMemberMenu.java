/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class GuestMemberMenu implements ActionListener{
    JFrame GMFrame = new JFrame();
    JLabel labName = new JLabel();
    JButton buttonRegister = new JButton("Register");
    JButton buttonTopUp = new JButton("Top Up");
    JButton buttonProfile = new JButton("Cek Profil");
    JButton buttonBookTicket = new JButton("Book Tiket Penerbangan");
    JButton buttonPayment = new JButton("Pembayaran");
    JButton buttonHistoryBook = new JButton("Cek Sejarah Booking");
    JButton buttonExchange = new JButton("Tukar Voucher");
    JButton buttonRefund = new JButton("Refund Booking");
    JButton buttonRescheduleBook = new JButton("Reschedule Booking");
    JButton buttonLogout = new JButton("Logout");
    
    public GuestMemberMenu(User user){
        GMFrame.setSize(500,600);
        GMFrame.setLocationRelativeTo(null);
        GMFrame.setLayout(null);
        GMFrame.setVisible(true);
        
        String name = user.getFullName();
        labName = new JLabel("Hello, " + name + "!");
        
        
        if(user instanceof Member){
            buttonProfile.setActionCommand("Profile");
            buttonProfile.addActionListener(this);
            buttonProfile.setBounds(100,150, 300,40);
            GMFrame.add(buttonProfile);
        }else{
            buttonRegister.setActionCommand("Register");
            buttonRegister.addActionListener(this);
            buttonRegister.setBounds(100,150, 300,40);
            GMFrame.add(buttonRegister);
        }
        
        buttonTopUp.setActionCommand("Top Up");
        buttonTopUp.addActionListener(this);
        buttonTopUp.setBounds(100,200, 300,40);
        
        buttonBookTicket.setActionCommand("Book Ticket");
        buttonBookTicket.addActionListener(this);
        buttonBookTicket.setBounds(100,250, 300,40);
        
        buttonPayment.setActionCommand("Payment");
        buttonPayment.addActionListener(this);
        buttonPayment.setBounds(100,300, 300,40);
        
        buttonExchange.setActionCommand("Exchange");
        buttonExchange.addActionListener(this);
        buttonExchange.setBounds(100,350, 300,40);
        
        buttonRefund.setActionCommand("Refund");
        buttonRefund.addActionListener(this);
        buttonRefund.setBounds(100,400, 300,40);
        
        buttonRescheduleBook.setActionCommand("Reschedule Book");
        buttonRescheduleBook.addActionListener(this);
        buttonRescheduleBook.setBounds(100,450, 300,40);
        
        buttonLogout.setActionCommand("Logout");
        buttonLogout.addActionListener(this);
        buttonLogout.setBounds(100,500, 300,40);
        
        GMFrame.add(buttonTopUp);
        GMFrame.add(buttonBookTicket);
        GMFrame.add(buttonPayment);
        GMFrame.add(buttonExchange);
        GMFrame.add(buttonRefund);
        GMFrame.add(buttonRescheduleBook);
        GMFrame.add(buttonLogout);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Profile":
                GMFrame.dispose();
                break;
            case "Register":
                GMFrame.dispose();
                break;
            case "Top Up":
                GMFrame.dispose();
                break;
            case "Book Ticket":
                GMFrame.dispose();
                break;
            case "Payment":
                GMFrame.dispose();
                break;
            case "Exchange":
                GMFrame.dispose();
                break;
            case "Refund":
                GMFrame.dispose();
                break;
            case "Reschedule Book":
                GMFrame.dispose();
                break;
            case "Logout":
                if (JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk logout?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    GMFrame.dispose();
                    JOptionPane.showMessageDialog(GMFrame,"Akun telah di-logout.");
                    new LoginScreen();
                }
                break;
        }
    }
    
//    public static void main(String[] args){
//        ArrayList<Booking> bookingsExample = new ArrayList<Booking>();
//        User memTest = new Member("levin@gmail.com", "Levin Martinus Budiarto", "levin", "081802014646", "Jl. Moh Toha 12", 100, bookingsExample);
//        
//        User gueTest = new User();
//        
//        new GuestMemberMenu(memTest);
//    }
}

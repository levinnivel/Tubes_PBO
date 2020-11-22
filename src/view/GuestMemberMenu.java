/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static model.ActiveEnum.*;
import static model.PaidEnum.*;
import view.MemberMenu1.*;
import view.MemberMenu2.*;
import view.MemberMenu3.*;
import view.MemberMenu4.*;
import view.MemberMenu5.*;
import view.GuestMenu.*;
import model.*;
import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GuestMemberMenu implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    //saat login member user null, saat guest member null 
    User user = UserManager.getInstance().getUser();
    User member = UserManager.getInstance().getMember();
    
    JFrame GMFrame = new JFrame();
    JLabel labName = new JLabel();
    JLabel labCartDesc = new JLabel("Your Cart : ");
    JLabel labIDBook = new JLabel();
    JLabel labTotalPrice = new JLabel();
    JButton buttonRegister = new JButton("Register");
    JButton buttonTopUp = new JButton("Top Up");
    JButton buttonProfile = new JButton("Cek Profil");
    JButton buttonBookTicket = new JButton("Book Tiket Penerbangan");
    JButton buttonPayment = new JButton("Pembayaran");
    JButton buttonRefund = new JButton("Refund or Cancel Booking");
    JButton buttonLogout = new JButton("Logout");
    
    public GuestMemberMenu(){
        GMFrame.setSize(500,500);
        GMFrame.setLocationRelativeTo(null);
        GMFrame.setLayout(null);
        GMFrame.setVisible(true);
        
        if(user == null){
//            saat login member akan menampilkan nama member di menu
            Booking book = BookingManager.getInstance().getBooking();
            BookingManager.getInstance().setBooking(book);

            String name = member.getFullName();
            
            labName = new JLabel("Hello, " + name + "!");
            if(book!=null){
                if(book.isPaid()==BELUM_LUNAS){
                String idBook = book.getIdBooking();
                int priceBook = book.getTotalPrice();
//              setelah memesan maka akan menampilkan id booking member dan total harga
                labIDBook = new JLabel("ID Booking : " + idBook);
                labTotalPrice = new JLabel("Total Price : " + priceBook);
                labIDBook.setBounds(75,100, 200,20);
                labTotalPrice.setBounds(75,125, 200,20);
                GMFrame.add(labIDBook);
                GMFrame.add(labTotalPrice);
                }
            }
            
            buttonProfile.setActionCommand("Profile");
            buttonProfile.addActionListener(this);
            buttonProfile.setBounds(100,150, 300,40);
            GMFrame.add(buttonProfile);
        }else{
            labName = new JLabel("Hello, Guest!");
            buttonRegister.setActionCommand("Register");
            buttonRegister.addActionListener(this);
            buttonRegister.setBounds(100,150, 300,40);
            GMFrame.add(buttonRegister);
        }
        
        labName.setBounds(75,50, 200,20);
        labCartDesc.setBounds(75,75, 200,20);
        
        buttonTopUp.setActionCommand("Top Up");
        buttonTopUp.addActionListener(this);
        buttonTopUp.setBounds(100,200, 300,40);
        
        buttonBookTicket.setActionCommand("Book Ticket");
        buttonBookTicket.addActionListener(this);
        buttonBookTicket.setBounds(100,250, 300,40);
        
        buttonPayment.setActionCommand("Payment");
        buttonPayment.addActionListener(this);
        buttonPayment.setBounds(100,300, 300,40);
        
        buttonRefund.setActionCommand("Refund");
        buttonRefund.addActionListener(this);
        buttonRefund.setBounds(100,350, 300,40);
        
        buttonLogout.setActionCommand("Logout");
        buttonLogout.addActionListener(this);
        buttonLogout.setBounds(100,400, 300,40);
        
        GMFrame.add(labName);
        GMFrame.add(labCartDesc);
        GMFrame.add(buttonTopUp);
        GMFrame.add(buttonBookTicket);
        GMFrame.add(buttonPayment);
        GMFrame.add(buttonRefund);
        GMFrame.add(buttonLogout);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Profile":
                GMFrame.dispose();
                new ProfileMenu();
                break;
            case "Register":
                GMFrame.dispose();
                new RegisterScreen();
                break;
            case "Top Up":
                if(user!=null){
//                  jika guest maka akan diminta register dahulu
                    JOptionPane.showMessageDialog(GMFrame, "Register terlebih dahulu!", "Menu Error",JOptionPane.WARNING_MESSAGE);
                    GMFrame.dispose();
                    new RegisterScreen();
                }else{
                    GMFrame.dispose();
                    new TopUp();
                }
                break;
            case "Book Ticket":
                if(user!=null){
                    JOptionPane.showMessageDialog(GMFrame, "Register terlebih dahulu!", "Menu Error",JOptionPane.WARNING_MESSAGE);
                    GMFrame.dispose();
                    new RegisterScreen();
                }else{
                    GMFrame.dispose();
                    new SearchPesawat();
                }
                break;
            case "Payment":
                if(user!=null){
                    JOptionPane.showMessageDialog(GMFrame, "Register terlebih dahulu!", "Menu Error",JOptionPane.WARNING_MESSAGE);
                    GMFrame.dispose();
                    new RegisterScreen();
                }else{
                    if(BookingManager.getInstance().getBooking()!=null){
                        GMFrame.dispose();
                        new PaymentScreen();
                    }else{
                        JOptionPane.showMessageDialog(GMFrame, "SIlahkan booking terlebih dahulu.", "Payment Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "Refund":
                if(user!=null){
                    JOptionPane.showMessageDialog(GMFrame, "Register terlebih dahulu!", "Menu Error",JOptionPane.WARNING_MESSAGE);
                    GMFrame.dispose();
                    new RegisterScreen();
                }else{
                    //jika member akan menampilkan menu refund
                    if(BookingManager.getInstance().getBooking()!=null){
                        GMFrame.dispose();
                        new RefundScreen();
                    }else{
                        conn.connect();
                        String idLastBooking = ("B-" + Integer.toString(Controller.getLastIDBookingInteger()));
                        String query = "SELECT * FROM booking "
                                + "WHERE emailMember='" + member.getEmail() + "' "
                                + "AND idBooking='" + idLastBooking + "'";
                        
                        Booking booking = new Booking();
                        booking.setIsActive(ACTIVE);
                        booking.setIsPaid(LUNAS);
                        try {
                            Statement stmt = conn.con.createStatement();
                            ResultSet rs = stmt.executeQuery(query);
                            while (rs.next()) {
                                booking.setIdBooking(rs.getString("idBooking"));
                            }
                            BookingManager.getInstance().setBooking(booking);
                            GMFrame.dispose();
                            new RefundScreen();
                        } catch (SQLException excGet) {
                            excGet.printStackTrace();
                            JOptionPane.showMessageDialog(GMFrame, "Pengambilan database error!", "DB Error",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                break;
            case "Logout":
                if (JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk logout?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    GMFrame.dispose();
                    UserManager.getInstance().setMember(null);
                    UserManager.getInstance().setUser(null);
                    JOptionPane.showMessageDialog(GMFrame,"Akun telah di-logout.");
                    new LoginScreen();
                }
                break;
        }
    }
}

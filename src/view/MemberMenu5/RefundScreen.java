/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu5;

import model.*;
import view.*;
import static model.ActiveEnum.*;
import static model.PaidEnum.*;
import controller.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import static controller.Controller.buildTableModel;
import javax.swing.table.DefaultTableModel;

public class RefundScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    Booking booking = BookingManager.getInstance().getBooking();
    
    JFrame refFrame = new JFrame();
    
    JLabel labTitle = new JLabel("Refund Your Booking");
    JLabel labCartDesc = new JLabel("Your Booking");
    
    JTable tabBooking;
    
    JButton buttonRefund = new JButton("Refund Booking");
    JButton buttonCancel = new JButton("Cancel Booking");
    JButton buttonBack = new JButton("Back");
    
    public RefundScreen(){
        refFrame.setSize(1300, 400);
        refFrame.setLocationRelativeTo(null);
        refFrame.setLayout(null);
        refFrame.setVisible(true);
        
        conn.connect();
        String query = "SELECT a.idBooking, b.idJadwal, c.idPesawat, d.destinasiAsal, d.destinasiAkhir, "
                + "b.jamKeberangkatan, b.jamKedatangan, b.dateKeberangkatan, b.dateKedatangan, "
                + "c.tipePesawat, c.hargaKursi, c.kapasitasBagasi, c.hargaBagasiPerKg "
                + "FROM booking a JOIN jadwal b ON a.idJadwal=b.idJadwal "
                + "JOIN pesawat c ON b.idPesawat=c.idPesawat JOIN rute d ON b.idRute=d.idRute "
                + "WHERE a.idBooking='" + booking.getIdBooking() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            tabBooking = new JTable(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        labTitle.setBounds(600, 30, 200, 30);
        labCartDesc.setBounds(30, 70, 100, 20);
        JScrollPane sp = new JScrollPane(tabBooking);
        sp.setBounds(30, 100, 1230, 50);
        
        buttonRefund.setBounds(900,260, 200,25);
        buttonRefund.setActionCommand("Refund");
        buttonRefund.addActionListener(this);
        
        buttonCancel.setBounds(600,260, 200,25);
        buttonCancel.setActionCommand("Cancel");
        buttonCancel.addActionListener(this);
        
        buttonBack.setBounds(300,260, 200,25);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        refFrame.add(labTitle);
        refFrame.add(labCartDesc);
        refFrame.add(sp);
        refFrame.add(buttonRefund);
        refFrame.add(buttonCancel);
        refFrame.add(buttonBack);
        
        conn.disconnect();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Refund":
                int originalBalance = member.getBalance();
                int refundPenalty = booking.getTotalPrice()/10;
                int refundedBalance = originalBalance-refundPenalty;
                
                if(booking.isPaid()==LUNAS && booking.isActive()==ACTIVE){
                    if(refundedBalance>=0){
                        conn.connect();
                        String query = "UPDATE member SET balance='" + refundedBalance + "' "
                        + "WHERE emailMember='" + member.getEmail() + "'";
                        
                        try {
                            Statement stmt = conn.con.createStatement();
                            stmt.executeUpdate(query);
                            member.setBalance(refundedBalance);
                            JOptionPane.showMessageDialog(refFrame,"Refund telah berhasil!");
                            JOptionPane.showMessageDialog(refFrame,"Saldo anda sekarang sebesar " + member.getBalance() + "!");
                        } catch (SQLException excUp) {
                            excUp.printStackTrace();
                            JOptionPane.showMessageDialog(refFrame, "Saldo gagal diperbaharui!", "Register Error",JOptionPane.WARNING_MESSAGE);
                        }
                        
                        String query2 = "UPDATE booking SET statusTransaksi='INACTIVE' "
                        + "WHERE emailMember='" + member.getEmail() + "'";
                        
                        try {
                            Statement stmt = conn.con.createStatement();
                            stmt.executeUpdate(query2);
                            booking.deactivateTransaction();
                            JOptionPane.showMessageDialog(refFrame,"Booking telah di-refund.");
                        } catch (SQLException excUp) {
                            excUp.printStackTrace();
                            JOptionPane.showMessageDialog(refFrame, "Booking gagal di-refund!", "Register Error",JOptionPane.WARNING_MESSAGE);
                        }
                        
                        conn.disconnect();
                        
                        refFrame.dispose();
                        new GuestMemberMenu();
                    }else{
                        JOptionPane.showMessageDialog(refFrame, "Saldo anda tidak mencukupi untuk refund!", "Refund Error",JOptionPane.WARNING_MESSAGE);
                    }
                }else if(booking.isPaid()==BELUM_LUNAS){
                    JOptionPane.showMessageDialog(refFrame, "Anda belum membayar sebelumnya, harap gunakan tombol Cancel jika ingin dibatalkan.", "Refund Error",JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "Cancel":
                if(booking.isPaid()==BELUM_LUNAS && booking.isActive()==ACTIVE){
                    conn.connect();

                    String query = "DELETE FROM member WHERE emailMember='" + member.getEmail() + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        stmt.executeUpdate(query);
                        JOptionPane.showMessageDialog(refFrame,"Booking telah dibatalkan.");
                    } catch (SQLException excDel) {
                        excDel.printStackTrace();
                    }
                    
                    conn.disconnect();    
                }else if(booking.isPaid()==LUNAS){
                    JOptionPane.showMessageDialog(refFrame, "Anda sudah membayar sebelumnya, harap gunakan tombol Refund jika ingin me-refund.", "Cancel Error",JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "Back":
                refFrame.dispose();
                new GuestMemberMenu();
                break;
        }
    }
}

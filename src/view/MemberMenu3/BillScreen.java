/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu3;

import controller.Controller;
import static controller.Controller.buildTableModel;
import controller.DatabaseHandler;
import model.*;
import view.*;
import controller.DateLabelFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static model.ActiveEnum.*;
import static model.UsedEnum.*;

public class BillScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    Booking booking = BookingManager.getInstance().getBooking();
    
    JFrame billFrame = new JFrame();
    JFrame schFrame = new JFrame();
    
    //jlabel
    JLabel labTitle = new JLabel("Data Penumpang Anda");
    JLabel labHargaKursi = new JLabel("Seat Price : ");
    JLabel labJumlahPesan = new JLabel("Ordered Seat(s) : ");
    JLabel labHargaBagasi = new JLabel("Baggage Price/Kg : ");
    JLabel labJumlahBagasi = new JLabel("Your Baggage(s) : ");
    JLabel labTotal = new JLabel("Total Price : ");
    
    JLabel labValHargaKursi;
    JLabel labValJumlahPesan;
    JLabel labValHargaBagasi;
    JLabel labValJumlahBagasi;
    JLabel labValTotal;
    
    JTable tabPenumpang;
    
    int valHargaKursi = 0;
    int valJumlahPesan = 0;
    int valHargaBagasi = 0;
    int valJumlahBagasi = 0;
    int valTotal = Global.getTotalPrice();
    
    JButton buttonConfirm = new JButton("Confirm Booking");
    
    public BillScreen(){
        billFrame.setSize(1300, 550);
        billFrame.setLocationRelativeTo(null);
        billFrame.setLayout(null);
        billFrame.setVisible(true);
        
        conn.connect();
        
        String query1 = "SELECT hargaKursi, hargaBagasiPerKg FROM pesawat "
                + "WHERE idPesawat='" + booking.getSchedule().getPesawat().getIdPesawat() + "'";
        
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            while(rs.next()){
                valHargaKursi = rs.getInt("hargaKursi");
                valHargaBagasi = rs.getInt("hargaBagasiPerKg");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String query2 = "SELECT COUNT(idPenumpang) FROM detail_penumpang "
                + "WHERE idBooking='" + booking.getIdBooking() + "'";
        
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query2);
            while(rs.next()){
               valJumlahPesan = rs.getInt("COUNT(idPenumpang)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String query3 = "SELECT SUM(b.bagasiPerKg) "
                + "FROM detail_penumpang a JOIN penumpang b ON a.idPenumpang=b.idPenumpang "
                + "WHERE a.idBooking='" + booking.getIdBooking() + "'";
        
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query3);
            while(rs.next()){
               valJumlahBagasi = rs.getInt("SUM(b.bagasiPerKg)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String query4 = "SELECT a.idPenumpang, b.idKursi, b.namaPenumpang, "
                + "b.titlePenumpang, b.bagasiPerKg, b.nationalityPenumpang "
                + "FROM detail_penumpang a JOIN penumpang b ON a.idPenumpang=b.idPenumpang "
                + "WHERE a.idBooking='" + booking.getIdBooking() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query4);
            tabPenumpang = new JTable(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        valTotal = (valHargaKursi * valJumlahPesan) + (valHargaBagasi * valJumlahBagasi);
        Global.setTotalPrice(valTotal);
        
        labValHargaKursi = new JLabel("IDR " + Integer.toString(valHargaKursi));
        labValJumlahPesan = new JLabel(Integer.toString(valJumlahPesan));
        labValHargaBagasi = new JLabel("IDR " + Integer.toString(valHargaBagasi));
        labValJumlahBagasi = new JLabel(Integer.toString(valJumlahBagasi) + " Kg");
        labValTotal = new JLabel("IDR " + Integer.toString(valTotal));
        
        labTitle.setBounds(600,10, 200,15);
        labHargaKursi.setBounds(30,30, 200,20);
        labJumlahPesan.setBounds(30,60, 200,20);
        labHargaBagasi.setBounds(30,90, 200,20);
        labJumlahBagasi.setBounds(30,120, 200,20);
        labTotal.setBounds(30,150, 200,20);
        labValHargaKursi.setBounds(300,30, 200,20);
        labValJumlahPesan.setBounds(300,60, 200,20);
        labValHargaBagasi.setBounds(300,90, 200,20);
        labValJumlahBagasi.setBounds(300,120, 200,20);
        labValTotal.setBounds(300,150, 200,20);
        
        JScrollPane sp = new JScrollPane(tabPenumpang);
        sp.setBounds(30, 270, 1230, 100);
        
        buttonConfirm.setActionCommand("Confirm");
        buttonConfirm.addActionListener(this);
        buttonConfirm.setBounds(600,420, 200,40);
        
        billFrame.add(labTitle);
        billFrame.add(labHargaKursi);
        billFrame.add(labJumlahPesan);
        billFrame.add(labHargaBagasi);
        billFrame.add(labJumlahBagasi);
        billFrame.add(labValHargaKursi);
        billFrame.add(labValJumlahPesan);
        billFrame.add(labValHargaBagasi);
        billFrame.add(labValJumlahBagasi);
        billFrame.add(sp);
        billFrame.add(buttonConfirm);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Confirm":
                String query = "UPDATE booking SET totalPrice='" + Global.getTotalPrice() + "', "
                + "statusTransaksi='ACTIVE' "
                + "WHERE idBooking='" + booking.getIdBooking() + "'";
                try {
                    Statement stmt = conn.con.createStatement();
                    stmt.executeUpdate(query);
                    booking.setTotalPrice(Global.getTotalPrice());
                    booking.setIsActive(ACTIVE);
                    JOptionPane.showMessageDialog(billFrame,"Booking telah berhasil!");
                    billFrame.dispose();
                    new GuestMemberMenu();
                } catch (SQLException excUp) {
                    excUp.printStackTrace();
                    JOptionPane.showMessageDialog(billFrame, "Status aktif gagal!", "Update Error",JOptionPane.WARNING_MESSAGE);
                }
                break;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu3;

import controller.Controller;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static model.UsedEnum.*;

public class PassengerFormScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    Booking booking = BookingManager.getInstance().getBooking();
    
    int passAmount = Global.getPassengerAmount();
    
    JFrame passFrame = new JFrame();
    
    JLabel labTitle = new JLabel("Insert your Data");
    JLabel labNama = new JLabel("Full Name : ");
    JLabel labPanggilan = new JLabel("Title : ");
    JLabel labKursi = new JLabel("Available Seat Number : ");
    JLabel labBagasi = new JLabel("Your Baggage (in KG, set 0 if not used) : ");
    JLabel labWarganegara = new JLabel("Nationality : ");
    
    JTextField tfNama = new JTextField();
    String[] opsiPanggilan = {"Bapak", "Ibu", "Tuan", "Nyonya", "Nona"};
    JComboBox cbPanggilan = new JComboBox(opsiPanggilan);
    ArrayList<String> listKursi = Controller.getAllAvailableSeats(booking.getSchedule().getPesawat().getIdPesawat());
    String[] opsiKursi = Controller.getStringArray(listKursi);
    JComboBox cbKursi = new JComboBox(opsiKursi);
    JTextField tfBagasi = new JTextField();
    JTextField tfWarganegara = new JTextField();
    
    JButton buttonReg = new JButton("Register");
    
    int count = Global.getPassengerCounter();
    
    public PassengerFormScreen(){
        System.out.println("Jumlah Penumpang " + passAmount);
        System.out.println("Form ke " + Global.getPassengerCounter());
        System.out.println("Form ke " + count);
        
        passFrame.setSize(800,350);
        passFrame.setLocationRelativeTo(null);
        passFrame.setLayout(null);
        passFrame.setVisible(true);
        
        labTitle.setBounds(450,30, 100,20);
        labNama.setBounds(50,70, 300,20);
        labPanggilan.setBounds(50,100, 300,20);
        labKursi.setBounds(50,130, 300,20);
        labBagasi.setBounds(50,160, 300,20);
        labWarganegara.setBounds(50,190, 300,20);
        
        tfNama.setBounds(500,70, 200,20);
        cbPanggilan.setBounds(500,100, 200,20);
        cbKursi.setBounds(500,130, 200,20);
        tfBagasi.setBounds(500,160, 200,20);
        tfWarganegara.setBounds(500,190, 200,20);
        
        buttonReg.setBounds(550,250, 150,20);
        buttonReg.setActionCommand("Register");
        buttonReg.addActionListener(this);
        
        passFrame.add(labTitle);
        passFrame.add(labNama);
        passFrame.add(labPanggilan);
        passFrame.add(labKursi);
        passFrame.add(labBagasi);
        passFrame.add(labWarganegara);
        passFrame.add(tfNama);
        passFrame.add(cbPanggilan);
        passFrame.add(cbKursi);
        passFrame.add(tfBagasi);
        passFrame.add(tfWarganegara);
        passFrame.add(buttonReg);
        
        Global.setPassengerCounter(Global.getPassengerCounter()+1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Register":
                conn.connect();
                
                Passenger passenger = new Passenger();
                Seat seat = new Seat();
                
                String idPesawat = booking.getSchedule().getPesawat().getIdPesawat();
                
                int i = Controller.getLastIDPenumpangInteger()+1;
                
                passenger.setIdPassenger("P-" + i);
                passenger.setFullName(tfNama.getText());
                passenger.setPanggilan(cbPanggilan.getSelectedItem().toString());
                passenger.setBagasiPerKg(Integer.parseInt(tfBagasi.getText()));
                passenger.setNationality(tfWarganegara.getText());
                
                seat.setSeatNum(Integer.parseInt(cbKursi.getSelectedItem().toString()));
                seat.setIsUsed(TERISI);
                
                String query = "SELECT idKursi from kursi "
                        + "WHERE idPesawat='" + idPesawat + "' "
                        + "AND noKursi='" + Integer.parseInt(cbKursi.getSelectedItem().toString()) + "'";
                
                try {
                    Statement stmt = conn.con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        seat.setIdSeat(rs.getString("idKursi"));
                    }
                } catch (SQLException excGet) {
                    excGet.printStackTrace();
                    JOptionPane.showMessageDialog(passFrame, "Pengambilan kursi gagal!", "Seat Error",JOptionPane.WARNING_MESSAGE);
                }
                
                String query2 = "UPDATE kursi SET statusKursi='TERISI' WHERE idKursi='" + seat.getIdSeat() + "'";
                try {
                    Statement stmt = conn.con.createStatement();
                    stmt.executeUpdate(query2);
                    JOptionPane.showMessageDialog(passFrame,"Booking kursi berhasil!");
                } catch (SQLException excUp) {
                    excUp.printStackTrace();
                    JOptionPane.showMessageDialog(passFrame, "Booking kursi gagal!", "Seat Error",JOptionPane.WARNING_MESSAGE);
                }
                
                passenger.setSeat(seat);
                
                String query3 = "INSERT INTO penumpang VALUES(?,?,?,?,?,?)";
                try {
                    PreparedStatement stmt = conn.con.prepareStatement(query3);
                    stmt.setString(1, passenger.getIdPassenger());
                    stmt.setString(2, passenger.getSeat().getIdSeat());
                    stmt.setString(3, passenger.getFullName());
                    stmt.setString(4, passenger.getPanggilan());
                    stmt.setInt(5, passenger.getBagasiPerKg());
                    stmt.setString(6, passenger.getNationality());
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(passFrame,"Data passenger telah berhasil didaftarkan!");
                } catch (SQLException excIns) {
                    excIns.printStackTrace();
                    JOptionPane.showMessageDialog(passFrame, "Pendaftaran gagal!", "Register Error",JOptionPane.WARNING_MESSAGE);
                }
                
                String query4 = "INSERT INTO detail_penumpang VALUES(?,?)";
                try {
                    PreparedStatement stmt = conn.con.prepareStatement(query4);
                    stmt.setString(1, booking.getIdBooking());
                    stmt.setString(2, passenger.getIdPassenger());
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(passFrame,"Detail penumpang telah berhasil didaftarkan!");
                } catch (SQLException excIns) {
                    excIns.printStackTrace();
                    JOptionPane.showMessageDialog(passFrame, "Detail gagal!", "Detail Error",JOptionPane.WARNING_MESSAGE);
                }
                
                if(passAmount==1 || Global.getPassengerCounter()==passAmount){
                    Global.setPassengerCounter(0);
                    passFrame.dispose();
                    JOptionPane.showMessageDialog(passFrame,"BILL SCREEN!");
                    new BillScreen();
                }else if(Global.getPassengerCounter()<passAmount){
                    passFrame.dispose();
                    new PassengerFormScreen();
                }
                break;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu3;

import controller.Controller;
import javax.swing.*;
import view.*;
import static controller.Controller.buildTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class HistoryMainScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame historyFrame = new JFrame();
    
//    SELECT a.idJadwal, b.idPesawat, a.jamKeberangkatan, a.jamKedatagan, a.dateKeberangkatan, a.dateKedatangan, b.tipePesawat, b.hargaKursi, b.kapasitasBagasi, b.hargaBagasiPerKg, c.destinasiAsal, c.destinasiAkhir FROM jadwal a JOIN pesawat b ON a.idPesawat=b.idPesawat JOIN rute c ON a.idRute=c.idRute WHERE c.destinasic.destinasiAsal, c.destinasiAkhir FROM jadwal a JOIN pesawat b ON a.idPesawat=b.idPesawat JOIN rute c ON a.idRute=c.idRute WHERE c.destinasiAsal='Bandung' AND c.destinasiAkhir='Jakarta'
    //jlabel
    JLabel labTitle = new JLabel("All Booking History");
    
//    Object data[][]={{"P001","First Class",800000,100000,12,"Bandung","Jakarta","2020-12-19","2020-12-19","09:00","12:00"},
//                        {"P002","Economy",600000,90000,10,"Bandung","Jakarta","2020-12-19","2020-12-19","10:00","13:00"},
//                        {"P003","Business",700000,95000,11,"Bandung","Jakarta","2020-12-19","2020-12-19","11:00","14:00"},
//                        {"P004","Business",700000,95000,11,"Bandung","Jakarta","2020-12-19","2020-12-19","14:00","17:00"},
//                        {"P005","Economy",600000,90000,10,"Bandung","Jakarta","2020-12-19","2020-12-19","16:00","19:00"},
//                        {"P007","First Class",800000,100000,12,"Bandung","Jakarta","2020-12-19","2020-12-19","18:00","20:00"}};
//    String column[]={"ID Pesawat","Type Pesawat","Harga","Harga bagasi","Kapasitas Bagasi","Kota Asal","Kota Tujuan","Tanggal Berangkat","Tanggal Datang","Jam Berangkat","Jam Datang"};
    JTable listPesawat;
    
    
    JButton buttonBack = new JButton("Back");
    

    public HistoryMainScreen(JTable listPesawat) {
        this.listPesawat = listPesawat;
    }
    public HistoryMainScreen(){
        historyFrame.setSize(1300, 500);
        historyFrame.setLocationRelativeTo(null);
        historyFrame.setLayout(null);
        historyFrame.setVisible(true);
        
        conn.connect();
        String query = "SELECT * from booking";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            listPesawat = new JTable(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        labTitle.setBounds(600, 30, 200, 30);
        JScrollPane sp = new JScrollPane(listPesawat);
        sp.setBounds(30, 90, 1230, 300);
        
        buttonBack.setBounds(1100, 400, 100, 30);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        historyFrame.add(labTitle);
        historyFrame.add(sp);
        historyFrame.add(buttonBack);
        
        conn.disconnect();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Back":
                historyFrame.dispose();
                new AdminMenu();
                break;
        }
    }
}

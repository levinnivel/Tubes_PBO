/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu3;

import controller.Controller;
import javax.swing.*;
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

public class ChooseSchedule implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame schFrame = new JFrame();
    
//    SELECT a.idJadwal, b.idPesawat, a.jamKeberangkatan, a.jamKedatagan, a.dateKeberangkatan, a.dateKedatangan, b.tipePesawat, b.hargaKursi, b.kapasitasBagasi, b.hargaBagasiPerKg, c.destinasiAsal, c.destinasiAkhir FROM jadwal a JOIN pesawat b ON a.idPesawat=b.idPesawat JOIN rute c ON a.idRute=c.idRute WHERE c.destinasic.destinasiAsal, c.destinasiAkhir FROM jadwal a JOIN pesawat b ON a.idPesawat=b.idPesawat JOIN rute c ON a.idRute=c.idRute WHERE c.destinasiAsal='Bandung' AND c.destinasiAkhir='Jakarta'
    //jlabel
    JLabel labTitle = new JLabel("Pesawat yang Tersedia");
    
//    Object data[][]={{"P001","First Class",800000,100000,12,"Bandung","Jakarta","2020-12-19","2020-12-19","09:00","12:00"},
//                        {"P002","Economy",600000,90000,10,"Bandung","Jakarta","2020-12-19","2020-12-19","10:00","13:00"},
//                        {"P003","Business",700000,95000,11,"Bandung","Jakarta","2020-12-19","2020-12-19","11:00","14:00"},
//                        {"P004","Business",700000,95000,11,"Bandung","Jakarta","2020-12-19","2020-12-19","14:00","17:00"},
//                        {"P005","Economy",600000,90000,10,"Bandung","Jakarta","2020-12-19","2020-12-19","16:00","19:00"},
//                        {"P007","First Class",800000,100000,12,"Bandung","Jakarta","2020-12-19","2020-12-19","18:00","20:00"}};
//    String column[]={"ID Pesawat","Type Pesawat","Harga","Harga bagasi","Kapasitas Bagasi","Kota Asal","Kota Tujuan","Tanggal Berangkat","Tanggal Datang","Jam Berangkat","Jam Datang"};
    JTable tabPesawat;
    
    
    JButton submit = new JButton("Submit");
    
    public ChooseSchedule(String kotaAsal, String kotaTujuan, String tglBerangkat,String tipe){
        schFrame.setSize(1300, 500);
        schFrame.setLocationRelativeTo(null);
        schFrame.setLayout(null);
        schFrame.setVisible(true);
        
        conn.connect();
        String query = "SELECT a.idJadwal, b.idPesawat,c.destinasiAsal, c.destinasiAkhir, "
                + "a.jamKeberangkatan, a.jamKedatangan, a.dateKeberangkatan, a.dateKedatangan, "
                + "b.tipePesawat, b.hargaKursi, b.kapasitasBagasi, b.hargaBagasiPerKg "
                + "FROM jadwal a JOIN pesawat b ON a.idPesawat=b.idPesawat JOIN rute c ON a.idRute=c.idRute "
                + "WHERE c.destinasiAsal='" + kotaAsal + "' AND c.destinasiAkhir='" + kotaTujuan +"' AND b.tipePesawat='" + tipe + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            tabPesawat = new JTable(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        labTitle.setBounds(600, 30, 200, 30);
        JScrollPane sp = new JScrollPane(tabPesawat);
        sp.setBounds(30, 90, 1230, 300);
        
        submit.setBounds(1100, 400, 100, 30);
        submit.setActionCommand("Submit");
        submit.addActionListener(this);
        
        schFrame.add(labTitle);
        schFrame.add(sp);
        schFrame.add(submit);
        
        conn.disconnect();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}

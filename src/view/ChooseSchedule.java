/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ChooseSchedule implements ActionListener{
    JFrame scheduleFrame = new JFrame();
    //jlabel
    JLabel chscTitle = new JLabel("Pesawat yang Tersedia");
    Object data[][]={{"P001","First Class",800000,100000,12,"Bandung","Jakarta","2020-12-19","2020-12-19","09:00","12:00"},
                        {"P002","Economy",600000,90000,10,"Bandung","Jakarta","2020-12-19","2020-12-19","10:00","13:00"},
                        {"P003","Business",700000,95000,11,"Bandung","Jakarta","2020-12-19","2020-12-19","11:00","14:00"},
                        {"P004","Business",700000,95000,11,"Bandung","Jakarta","2020-12-19","2020-12-19","14:00","17:00"},
                        {"P005","Economy",600000,90000,10,"Bandung","Jakarta","2020-12-19","2020-12-19","16:00","19:00"},
                        {"P007","First Class",800000,100000,12,"Bandung","Jakarta","2020-12-19","2020-12-19","18:00","20:00"}};
    String column[]={"ID Pesawat","Type Pesawat","Harga","Harga bagasi","Kapasitas Bagasi","Kota Asal","Kota Tujuan","Tanggal Berangkat","Tanggal Datang","Jam Berangkat","Jam Datang"};
    JTable jdwlPesawat = new JTable(data,column);
    JButton submit = new JButton("Submit");
    public ChooseSchedule(/*JadwalPesawat dataJdwl --> database*/){
        scheduleFrame.setSize(1300, 500);
        scheduleFrame.setLocationRelativeTo(null);
        scheduleFrame.setLayout(null);
        scheduleFrame.setVisible(true);
        
        chscTitle.setBounds(600, 30, 200, 30);
        JScrollPane sp = new JScrollPane(jdwlPesawat);
        sp.setBounds(30, 90, 1230, 300);
        
        submit.setBounds(1100, 400, 100, 30);
        submit.addActionListener(this);
        
        scheduleFrame.add(chscTitle);
        scheduleFrame.add(sp);
        scheduleFrame.add(submit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}

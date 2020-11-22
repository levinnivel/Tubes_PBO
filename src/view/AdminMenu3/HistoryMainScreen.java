/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu3;

import controller.*;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryMainScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame historyFrame = new JFrame();
    
    //jlabel
    JLabel labTitle = new JLabel("All Booking History");
    
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
//        menampilkan semua data booking dari database
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

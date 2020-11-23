/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu1;

import controller.Controller;
import javax.swing.*;
import view.*;
import static controller.Controller.buildTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import model.*;

public class MemberHistoryScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    
    JFrame historyFrame = new JFrame();
    
    //jlabel
    JLabel labTitle = new JLabel(member.getFullName() + " Booking History");
    
    JTable listPesawat;
    
    JButton buttonBack = new JButton("Back");

    public MemberHistoryScreen(JTable listPesawat) {
        this.listPesawat = listPesawat;
    }
    public MemberHistoryScreen(){
        historyFrame.setSize(1300, 500);
        historyFrame.setLocationRelativeTo(null);
        historyFrame.setLayout(null);
        historyFrame.setVisible(true);
        historyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        conn.connect();
        String query = "SELECT * from booking WHERE emailMember='" + member.getEmail() + "'";
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
                new ProfileMenu();
                break;
        }
    }
}

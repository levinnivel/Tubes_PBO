/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import model.*;
import view.GuestMemberMenu;

public class TopUp implements ActionListener {
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    
    JFrame topUpFrame = new JFrame();
    
    JLabel labTitle = new JLabel("Top-up Menu!");
    JLabel labPayMeth = new JLabel("Payment Method  : ");
    JLabel labTopAmount = new JLabel("Amount                   : ");
    JTextField tfTopAmount = new JTextField();
    JRadioButton rmethod1 = new JRadioButton("Transfer ATM");
    JRadioButton rmethod2 = new JRadioButton("OVO");
    JRadioButton rmethod3 = new JRadioButton("GO-PAY");
    JRadioButton rmethod4 = new JRadioButton("Alfamart");
    JRadioButton rmethod5 = new JRadioButton("Indomaret");
    ButtonGroup methodTypes = new ButtonGroup();
    JButton buttonTopUp = new JButton("Top-Up"); //Login by inserting username and password
    JButton buttonBack = new JButton("Back");
    
    public TopUp(){
        topUpFrame.setSize(500,350);
        topUpFrame.setLocationRelativeTo(null);
        topUpFrame.setLayout(null);
        topUpFrame.setVisible(true);
        
        labTitle.setBounds(150,30, 200,20);
        
        labPayMeth.setBounds(50,100, 150,30);
        labTopAmount.setBounds(50,180, 150,30);
        
        methodTypes.add(rmethod1);
        methodTypes.add(rmethod2);
        methodTypes.add(rmethod3);
        methodTypes.add(rmethod4);
        methodTypes.add(rmethod5);
        rmethod1.setBounds(200,100, 120,25);
        rmethod2.setBounds(200,120, 120,25);
        rmethod3.setBounds(200,140, 120,25);
        rmethod4.setBounds(350,100, 120,25);
        rmethod5.setBounds(350,120, 120,25);
        tfTopAmount.setBounds(200,180, 200,30);
        
        buttonTopUp.setBounds(215,240, 100,25);
        buttonTopUp.setActionCommand("Top-Up");
        buttonTopUp.addActionListener(this);
        
        buttonBack.setBounds(215,270, 100,25);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        topUpFrame.add(labTitle);
        topUpFrame.add(labPayMeth);
        topUpFrame.add(labTopAmount);
        topUpFrame.add(rmethod1);
        topUpFrame.add(rmethod2);
        topUpFrame.add(rmethod3);
        topUpFrame.add(rmethod4);
        topUpFrame.add(rmethod5);
        topUpFrame.add(tfTopAmount);
        topUpFrame.add(buttonTopUp);
        topUpFrame.add(buttonBack);

    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Top-Up":
                conn.connect();
                
                int topUpAmount = Integer.parseInt(tfTopAmount.getText());
                int topUpPoint = 0;
                
                String valMethod = "";
                if(rmethod1.isSelected()){
                    valMethod = "Transfer ATM";
                }else if (rmethod2.isSelected()){
                    valMethod = "OVO";
                    topUpPoint = topUpAmount/10;
                }else if (rmethod3.isSelected()){
                    valMethod = "GO-PAY";
                    topUpPoint = topUpAmount/20;
                }else if (rmethod4.isSelected()){
                    valMethod = "Alfamart";
                    topUpPoint = topUpAmount/5;
                }else if (rmethod5.isSelected()){
                    valMethod = "Indomaret";
                    topUpPoint = topUpAmount/15;
                }
                
                Date date = Calendar.getInstance().getTime();  
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
                String strDate = dateFormat.format(date);  
                
                String query1 = "INSERT INTO payment VALUES(?,?,?,?)";
                try {
                    PreparedStatement stmt = conn.con.prepareStatement(query1);
                    stmt.setString(1, member.getEmail());
                    stmt.setString(2, valMethod);
                    stmt.setInt(3, topUpAmount);
                    stmt.setString(4, strDate);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(topUpFrame,"Top up telah berhasil!");
                    
                } catch (SQLException excIns) {
                    excIns.printStackTrace();
                    JOptionPane.showMessageDialog(topUpFrame, "Top up gagal!", "Register Error",JOptionPane.WARNING_MESSAGE);
                }
                
                int newAmount = member.getBalance() + topUpAmount;
                int newPoint = member.getPoint() + topUpPoint;
                
                String query2 = "UPDATE member SET balance='" + newAmount + "', "
                        + "poinMember='" + newPoint + "' "
                        + "WHERE emailMember='" + member.getEmail() + "'";
                try {
                    Statement stmt = conn.con.createStatement();
                    stmt.executeUpdate(query2);
                    member.setBalance(newAmount);
                    member.setPoint(newPoint);
                    JOptionPane.showMessageDialog(topUpFrame,"Saldo berhasil diperbaharui!");
                    JOptionPane.showMessageDialog(topUpFrame,"Anda mendapatkan poin sebesar " +topUpPoint + "!");
                } catch (SQLException excUp) {
                    excUp.printStackTrace();
                    JOptionPane.showMessageDialog(topUpFrame, "Saldo gagal diperbaharui!", "Register Error",JOptionPane.WARNING_MESSAGE);
                }
                
                conn.disconnect();
                topUpFrame.dispose();
                new GuestMemberMenu();
                break;
            case "Back":
                topUpFrame.dispose();
                new GuestMemberMenu();
                break;
        }
    }
}

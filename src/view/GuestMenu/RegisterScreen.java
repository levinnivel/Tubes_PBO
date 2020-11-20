/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.GuestMenu;

import model.*;
import view.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame regFrame = new JFrame();
    
    JLabel labTitle = new JLabel("Insert your Data");
    JLabel labFullName = new JLabel("Full Name : ");
    JLabel labEmail = new JLabel("Email : ");
    JLabel labPassword = new JLabel("Password : ");
    JLabel labPhone = new JLabel("Phone Number : ");
    JLabel labAddress = new JLabel("Address : ");
    
    JTextField tfFullName = new JTextField();
    JTextField tfEmail = new JTextField();
    JTextField tfPassword = new JTextField();
    JTextField tfPhone = new JTextField();
    JTextField tfAddress = new JTextField();
    
    JButton buttonReg = new JButton("Register Now");
    JButton buttonBack = new JButton("Back");
    
    public RegisterScreen(){
        regFrame.setSize(600,350);
        regFrame.setLocationRelativeTo(null);
        regFrame.setLayout(null);
        regFrame.setVisible(true);
        
        labTitle.setBounds(150,30, 100,20);
        labFullName.setBounds(50,70, 100,20);
        labEmail.setBounds(50,100, 100,20);
        labPassword.setBounds(50,130, 100,20);
        labPhone.setBounds(50,160, 100,20);
        labAddress.setBounds(50,190, 100,20);
        
        tfFullName.setBounds(300,70, 200,20);
        tfEmail.setBounds(300,100, 200,20);
        tfPassword.setBounds(300,130, 200,20);
        tfPhone.setBounds(300,160, 200,20);
        tfAddress.setBounds(300,190, 200,20);
        
        buttonReg.setBounds(350,250, 150,20);
        buttonReg.setActionCommand("Register");
        buttonReg.addActionListener(this);
        
        buttonBack.setBounds(50,250, 100,20);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        regFrame.add(labTitle);
        regFrame.add(labFullName);
        regFrame.add(labEmail);
        regFrame.add(labPassword);
        regFrame.add(labPhone);
        regFrame.add(labAddress);
        regFrame.add(tfFullName);
        regFrame.add(tfEmail);
        regFrame.add(tfPassword);
        regFrame.add(tfPhone);
        regFrame.add(tfAddress);
        regFrame.add(buttonReg);
        regFrame.add(buttonBack);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Register":
                conn.connect();
                UserManager.getInstance().setUser(null);
                Member newMember = new Member();
                newMember.setFullName(tfFullName.getText());
                newMember.setEmail(tfEmail.getText());
                newMember.setPassword(tfPassword.getText());
                newMember.setPhoneNum(tfPhone.getText());
                newMember.setAddress(tfAddress.getText());

                String query = "INSERT INTO member VALUES(?,?,?,?,?,?)";
                try {
                    PreparedStatement stmt = conn.con.prepareStatement(query);
                    stmt.setString(1, newMember.getEmail());
                    stmt.setString(2, newMember.getFullName());
                    stmt.setString(3, newMember.getPassword());
                    stmt.setString(4, newMember.getPhoneNum());
                    stmt.setString(5, newMember.getAddress());
                    stmt.setInt(6, 0);
                    stmt.executeUpdate();
                    UserManager.getInstance().setMember(newMember);
                    regFrame.dispose();
                    JOptionPane.showMessageDialog(regFrame,"Akun telah berhasil dibuat!");
                    new GuestMemberMenu();
                } catch (SQLException excIns) {
                    excIns.printStackTrace();
                    JOptionPane.showMessageDialog(regFrame, "Email sudah pernah terdaftar!", "Register Error",JOptionPane.WARNING_MESSAGE);
                }
                
                conn.disconnect();
                break;
            case "Back":
                regFrame.dispose();
                new GuestMemberMenu();
                break;
        }
    }
}

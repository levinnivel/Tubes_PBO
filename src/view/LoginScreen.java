/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import controller.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Member;
import model.Admin;
import model.User;


public class LoginScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame loginFrame = new JFrame();
    JLabel labTitle = new JLabel("Welcome to Our Booking Apps!");
    JLabel labCredit = new JLabel("By Levin, Aristo, Andreas V");
    JLabel labUsername = new JLabel("Username : ");
    JLabel labPassword = new JLabel("Password : ");
    JTextField tfUsername = new JTextField();
    JTextField tfPassword = new JTextField();
    JButton buttonLoginDefault = new JButton("Login"); //Login by inserting username and password
    JButton buttonLoginGuest = new JButton("Login as Guest");
    JButton buttonExit = new JButton("Exit");
    
    public LoginScreen(){
        loginFrame.setSize(500,350);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        
        labTitle.setBounds(150,30, 200,20);
        labCredit.setBounds(160,50, 200,20);
        
        labUsername.setBounds(100,140, 80,30);
        labPassword.setBounds(100,180, 80,30);
        
        tfUsername.setBounds(200,140, 200,30);
        tfPassword.setBounds(200,180, 200,30);
        
        buttonLoginDefault.setBounds(100,250, 100,25);
        buttonLoginDefault.setActionCommand("Login");
        buttonLoginDefault.addActionListener(this);
        
        buttonLoginGuest.setBounds(200,250, 200,25);
        buttonLoginGuest.setActionCommand("Login as Guest");
        buttonLoginGuest.addActionListener(this);
        
        buttonExit.setBounds(150,270, 100,25);
        buttonExit.setActionCommand("Exit");
        buttonExit.addActionListener(this);
        
        loginFrame.add(labTitle);
        loginFrame.add(labCredit);
        loginFrame.add(labUsername);
        loginFrame.add(labPassword);
        loginFrame.add(tfUsername);
        loginFrame.add(tfPassword);
        loginFrame.add(buttonLoginDefault);
        loginFrame.add(buttonLoginGuest);
        loginFrame.add(buttonExit);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Login":
                String[] opt = {"Admin", "Member"};
                int logType = JOptionPane.showOptionDialog(loginFrame, "Masuk sebagai ", "Pilih", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                conn.connect();
                if(logType==0){
                    try {
                        String query = "SELECT * FROM admin WHERE emailAdmin='" + tfUsername.getText() + "'AND passwordAdmin='" + tfPassword.getText() + "'";
                        Statement stmt = conn.con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next()) {
                            Admin logUser = new Admin();
                            logUser.setEmail(rs.getString("emailAdmin"));
                            logUser.setFullName(rs.getString("fullNameAdmin"));
                            logUser.setPassword(rs.getString("passwordAdmin"));
                            logUser.setPhoneNum(rs.getString("phoneNumAdmin"));
                            logUser.setKodeAdmin(rs.getString("kodeAdmin"));
                            logUser.setPayroll(rs.getInt("payrollAdmin"));
                            loginFrame.dispose();
                            JOptionPane.showMessageDialog(loginFrame,"Selamat datang " + logUser.getFullName() + "!");
                            new AdminMenu(logUser);
                        }
                        else{
                            JOptionPane.showMessageDialog(loginFrame, "Pengisian ada yang salah!", "Login Error",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException excLog) {
                        excLog.printStackTrace();
                    }
                }else{
                    try {
                        String query = "SELECT * FROM member WHERE emailMember='" + tfUsername.getText() + "'AND passwordMember='" + tfPassword.getText() + "'";
                        Statement stmt = conn.con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next()) {
                            Member logUser = new Member();
                            logUser.setEmail(rs.getString("emailMember"));
                            logUser.setFullName(rs.getString("fullNameMember"));
                            logUser.setPassword(rs.getString("passwordMember"));
                            logUser.setPhoneNum(rs.getString("phoneNumMember"));
                            logUser.setAddress(rs.getString("addressMember"));
                            logUser.setPoint(rs.getInt("poinMember"));
                            loginFrame.dispose();
                            JOptionPane.showMessageDialog(loginFrame,"Selamat datang " + logUser.getFullName() + "!");
                            new GuestMemberMenu(logUser);
                        }
                        else{
                            JOptionPane.showMessageDialog(loginFrame, "Pengisian ada yang salah!", "Login Error",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException excLog) {
                        excLog.printStackTrace();
                    }
                }
                break;
            case "Login as Guest":
                loginFrame.dispose();
                JOptionPane.showMessageDialog(loginFrame,"Selamat datang Guest!");
                break;
            case "Exit":
                loginFrame.dispose();
                JOptionPane.showMessageDialog(loginFrame,"Terima kasih sudah menggunakan!");
                break;
        }
    }
}

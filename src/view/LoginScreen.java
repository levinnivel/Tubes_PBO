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
import java.awt.event.WindowListener;
import controller.*;
import controller.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Member;
import model.Admin;
import model.User;
import model.UserManager;


public class LoginScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame loginFrame = new JFrame();
    JLabel labTitle = new JLabel("WELCOME TO Speedwagon Apps!");
    JLabel labCredit = new JLabel("By Levin, Aristo, Andreas V");
    JLabel labUsername = new JLabel("Username : ");
    JLabel labPassword = new JLabel("Password : ");
    JTextField tfUsername = new JTextField();
    JPasswordField pfPassword = new JPasswordField();
    JButton buttonLoginDefault = new JButton("Login"); //Login by inserting username and password
    JButton buttonLoginGuest = new JButton("Login as Guest");
    JButton buttonExit = new JButton("Exit");
    
    public LoginScreen(){
        loginFrame.setTitle("Login");
        loginFrame.setSize(500,370);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        labTitle.setBounds(150,30, 200,20);
        labCredit.setBounds(170,50, 200,20);
        
        labUsername.setBounds(100,140, 80,30);
        labPassword.setBounds(100,180, 80,30);
        
        tfUsername.setBounds(200,140, 200,30);
        pfPassword.setBounds(200,180, 200,30);
        
        
        buttonLoginDefault.setBounds(100,250, 100,25);
        buttonLoginDefault.setActionCommand("Login");
        buttonLoginDefault.addActionListener(this);
        
        buttonLoginGuest.setBounds(250,250, 180,25);
        buttonLoginGuest.setActionCommand("Login as Guest");
        buttonLoginGuest.addActionListener(this);
        
        buttonExit.setBounds(330,290, 100,25);
        buttonExit.setActionCommand("Exit");
        buttonExit.addActionListener(this);
        
        loginFrame.add(labTitle);
        loginFrame.add(labCredit);
        loginFrame.add(labUsername);
        loginFrame.add(labPassword);
        loginFrame.add(tfUsername);
        loginFrame.add(pfPassword);
        loginFrame.add(buttonLoginDefault);
        loginFrame.add(buttonLoginGuest);
        loginFrame.add(buttonExit);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Login":
                if (tfUsername.getText().equals("")){
                    JOptionPane.showMessageDialog(loginFrame, "Mohon mengisi terlebih dahulu!", "Login Error",JOptionPane.WARNING_MESSAGE);
                }else{
    //                mengecek ingin login sebagai apa, jika 0 -> admin, 1 -> member
                    String[] opt = {"Admin", "Member"};
                    int logType = JOptionPane.showOptionDialog(loginFrame, "Masuk sebagai ", "Pilih", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                    conn.connect();
    //                cek admin di database
                    if(logType==0){
                        try {
                            String query = "SELECT * FROM admin WHERE kodeAdmin='" + tfUsername.getText() + "'AND passwordAdmin='" + pfPassword.getText() + "'";
                            Statement stmt = conn.con.createStatement();
                            ResultSet rs = stmt.executeQuery(query);
                            if(rs.next()) {
                                Admin logAdmin = new Admin();
                                logAdmin.setEmail(rs.getString("emailAdmin"));
                                logAdmin.setFullName(rs.getString("fullNameAdmin"));
                                logAdmin.setPassword(rs.getString("passwordAdmin"));
                                logAdmin.setPhoneNum(rs.getString("phoneNumAdmin"));
                                logAdmin.setKodeAdmin(rs.getString("kodeAdmin"));
                                logAdmin.setPayroll(rs.getInt("payrollAdmin"));

                                UserManager.getInstance().setAdmin(logAdmin);
                                loginFrame.dispose();
                                JOptionPane.showMessageDialog(loginFrame,"Selamat datang " + logAdmin.getFullName() + "!");
                                new AdminMenu();
                            }
                            else{
                            JOptionPane.showMessageDialog(loginFrame, "Pengisian ada yang salah!", "Login Error",JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (SQLException excLog) {
                            excLog.printStackTrace();
                        }
                    }else{
    //                cek member di database
                        try {
                            String query = "SELECT * FROM member WHERE emailMember='" + tfUsername.getText() + "'AND passwordMember='" + pfPassword.getText() + "'";
                            Statement stmt = conn.con.createStatement();
                            ResultSet rs = stmt.executeQuery(query);
                            if(rs.next()) {
                                Member logMember = new Member();
                                logMember.setEmail(rs.getString("emailMember"));
                                logMember.setFullName(rs.getString("fullNameMember"));
                                logMember.setPassword(rs.getString("passwordMember"));
                                logMember.setPhoneNum(rs.getString("phoneNumMember"));
                                logMember.setAddress(rs.getString("addressMember"));
                                logMember.setPoint(rs.getInt("poinMember"));
                                logMember.setBalance(rs.getInt("balance"));

                                UserManager.getInstance().setMember(logMember);

                                conn.disconnect();

                                loginFrame.dispose();
                                JOptionPane.showMessageDialog(loginFrame,"Selamat datang " + logMember.getFullName() + "!");
                                new GuestMemberMenu();
                            }
                            else{
                                JOptionPane.showMessageDialog(loginFrame, "Pengisian ada yang salah!", "Login Error",JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (SQLException excLog) {
                            excLog.printStackTrace();
                        }
                    }
                }
                break;
            case "Login as Guest":
                User logUser = new User();
                UserManager.getInstance().setUser(logUser); //Guest masuk sebagai user
                loginFrame.dispose();
                JOptionPane.showMessageDialog(loginFrame,"Selamat datang Guest!");
                new GuestMemberMenu();
                break;
            case "Exit":
                loginFrame.dispose();
                JOptionPane.showMessageDialog(loginFrame,"Terima kasih sudah menggunakan!");
                System.exit(0);
                break;
        }
    }
}
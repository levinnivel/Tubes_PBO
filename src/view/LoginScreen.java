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

public class LoginScreen implements ActionListener{
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
                loginFrame.dispose();
                JOptionPane.showMessageDialog(loginFrame,"Selamat datang <user>!");
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

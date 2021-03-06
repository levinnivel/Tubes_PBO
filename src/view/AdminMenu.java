/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import javax.swing.*;
import view.AdminMenu1.*;
import view.AdminMenu2.*;
import view.AdminMenu3.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AdminMenu implements ActionListener{
    //set data admin
    Admin admin = UserManager.getInstance().getAdmin();
    
    JFrame adminFrame = new JFrame();
    JLabel labName = new JLabel();
    JLabel labCode = new JLabel();
    JLabel labSalary = new JLabel();
    JButton buttonEdit = new JButton("Edit Jadwal Penerbangan");
    JButton buttonReschedule = new JButton("Reschedule Jadwal Penerbangan");
    JButton buttonHistoryAll = new JButton("Sejarah Transaksi");
    JButton buttonLogout = new JButton("Logout");
    
    public AdminMenu(){
        adminFrame.setTitle("Main Menu");
        adminFrame.setSize(500,450);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setLayout(null);
        adminFrame.setVisible(true);
        
        String name = admin.getFullName();
        String code = admin.getKodeAdmin();
        int payroll = admin.getPayroll();
        labName = new JLabel("Hello, " + name);
        labCode = new JLabel("Your Admin Code : " + code);
        labSalary = new JLabel("Salary : " + payroll);
        labName.setBounds(75,50, 200,20);
        labCode.setBounds(75,80, 200,20);
        labSalary.setBounds(75,110, 200,20);
        
        buttonEdit.setActionCommand("Edit");
        buttonEdit.addActionListener(this);
        buttonEdit.setBounds(100,150, 300,40);
        
        buttonReschedule.setActionCommand("Reschedule");
        buttonReschedule.addActionListener(this);
        buttonReschedule.setBounds(100,200, 300,40);
        
        buttonHistoryAll.setActionCommand("History All");
        buttonHistoryAll.addActionListener(this);
        buttonHistoryAll.setBounds(100,250, 300,40);
        
        buttonLogout.setActionCommand("Logout");
        buttonLogout.addActionListener(this);
        buttonLogout.setBounds(100,350, 300,40);
        
        adminFrame.add(labName);
        adminFrame.add(labCode);
        adminFrame.add(labSalary);
        adminFrame.add(buttonEdit);
        adminFrame.add(buttonReschedule);
        adminFrame.add(buttonHistoryAll);
        adminFrame.add(buttonLogout);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Edit":
                adminFrame.dispose();
                new EditScheduleMainScreen();
                break;
            case "Reschedule":
                adminFrame.dispose();
                new RescheduleMainScreen();
                break;
            case "History All":
                adminFrame.dispose();
                new HistoryMainScreen();
                break;
            case "Logout":
                if (JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk logout?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    UserManager.getInstance().setAdmin(null);
                    adminFrame.dispose();
                    JOptionPane.showMessageDialog(adminFrame,"Akun telah di-logout.");
                    new LoginScreen();
                }
                break;
        }
    }
}

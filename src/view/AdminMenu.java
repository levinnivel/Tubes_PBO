/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AdminMenu implements ActionListener{
    JFrame adminFrame = new JFrame();
    JLabel labWelcome = new JLabel();
    JButton buttonEdit = new JButton("Edit Jadwal Penerbangan");
    JButton buttonReschedule = new JButton("Reschedule Jadwal Penerbangan");
    JButton buttonHistoryAll = new JButton("Sejarah Transaksi");
    JButton buttonAddVoucher = new JButton("Tambah Voucher Tersedia");
    JButton buttonLogout = new JButton("Logout");
    
    public AdminMenu(Admin admin){
        adminFrame.setSize(500,450);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setLayout(null);
        adminFrame.setVisible(true);
        
        
        String name = admin.getFullName();
        String code = admin.getKodeAdmin();
        int payroll = admin.getPayroll();
        labWelcome = new JLabel("Hello, " + name + "!<br>" +
                                "Your Admin Code : " + code + "<br>" +
                                "Salary : " + payroll);
        
        
        buttonEdit.setActionCommand("Edit");
        buttonEdit.addActionListener(this);
        buttonEdit.setBounds(100,150, 300,40);
        
        buttonReschedule.setActionCommand("Reschedule");
        buttonReschedule.addActionListener(this);
        buttonReschedule.setBounds(100,200, 300,40);
        
        buttonHistoryAll.setActionCommand("History All");
        buttonHistoryAll.addActionListener(this);
        buttonHistoryAll.setBounds(100,250, 300,40);
        
        buttonAddVoucher.setActionCommand("Add Voucher");
        buttonAddVoucher.addActionListener(this);
        buttonAddVoucher.setBounds(100,300, 300,40);
        
        buttonLogout.setActionCommand("Logout");
        buttonLogout.addActionListener(this);
        buttonLogout.setBounds(100,350, 300,40);
        
        adminFrame.add(buttonEdit);
        adminFrame.add(buttonReschedule);
        adminFrame.add(buttonHistoryAll);
        adminFrame.add(buttonAddVoucher);
        adminFrame.add(buttonLogout);
        
        /*JFrame frame = new JFrame();
        frame.setLayout(new GridLayout());
        JLabel label = new JLabel("<html>First line<br>Second line</html>");
        frame.add(label);
        frame.pack();
        frame.setVisible(true);*/
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Edit":
                adminFrame.dispose();
                break;
            case "Reschedule":
                adminFrame.dispose();
                break;
            case "History All":
                adminFrame.dispose();
                break;
            case "Add Voucher":
                adminFrame.dispose();
                break;
            case "Logout":
                adminFrame.dispose();
                JOptionPane.showMessageDialog(adminFrame,"Terima kasih sudah menggunakan!");
                break;
        }
    }
    
    public static void main(String[] args){
        Admin admTest = new Admin("levin@gmail.com", "Levin Martinus Budiarto", "levin", "081802014646", "AD-01", 4500000);
        new AdminMenu(admTest);
    }
}

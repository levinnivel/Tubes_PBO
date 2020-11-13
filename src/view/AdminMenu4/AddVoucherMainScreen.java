/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu4;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddVoucherMainScreen implements ActionListener{
    JFrame voucherFrame = new JFrame();
    JLabel labTitle = new JLabel("Add Voucher Menu!");
//    JLabel labCredit = new JLabel("give us ur money plz");
    JLabel labVouchers = new JLabel("Vouchers       : ");
    JLabel labDiscount = new JLabel("Discount                   : ");
    JTextField tfDiscount = new JTextField();
    JRadioButton rvoucher1 = new JRadioButton("Voucher 11.11");
    JRadioButton rvoucher2 = new JRadioButton("Voucher 17 Agustus");
    JRadioButton rvoucher3 = new JRadioButton("Voucher Anniversary");
    JRadioButton rvoucher4 = new JRadioButton("Voucher Tahun Baru");
    JRadioButton rvoucher5 = new JRadioButton("Voucher Lebaran");
    JRadioButton rvoucher6 = new JRadioButton("Voucher Holiday");
    JButton buttonAddVoucher = new JButton("Add"); //Login by inserting username and password
    JButton buttonExit = new JButton("Exit");
    
    
    public AddVoucherMainScreen(){
        voucherFrame.setSize(500,400);
        voucherFrame.setLocationRelativeTo(null);
        voucherFrame.setLayout(null);
        voucherFrame.setVisible(true);
        
        labTitle.setBounds(180,30, 200,20);
//        labCredit.setBounds(160,50, 200,20);
        
        labVouchers.setBounds(50,100, 150,30);
        labDiscount.setBounds(50,240, 150,30);
        
        rvoucher1.setBounds(200,100, 160,25);
        rvoucher2.setBounds(200,120, 160,25);
        rvoucher3.setBounds(200,140, 160,25);
        rvoucher4.setBounds(200,160, 160,25);
        rvoucher5.setBounds(200,180, 160,25);
        rvoucher6.setBounds(200,200, 160,25);
        tfDiscount.setBounds(200,240, 30,30);
        
        buttonAddVoucher.setBounds(300, 280, 100,25);
        buttonAddVoucher.setActionCommand("Add");
        buttonAddVoucher.addActionListener(this);
        
        buttonExit.setBounds(300, 320, 100,25);
        buttonExit.setActionCommand("Exit");
        buttonExit.addActionListener(this);
        
        voucherFrame.add(labTitle);
//        voucherFrame.add(labCredit);
        voucherFrame.add(labVouchers);
        voucherFrame.add(labDiscount);
        voucherFrame.add(rvoucher1);
        voucherFrame.add(rvoucher2);
        voucherFrame.add(rvoucher3);
        voucherFrame.add(rvoucher4);
        voucherFrame.add(rvoucher5);
        voucherFrame.add(rvoucher6);
        voucherFrame.add(tfDiscount);
        voucherFrame.add(buttonAddVoucher);
        voucherFrame.add(buttonExit);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Add":
                voucherFrame.dispose();
                if(rvoucher1.isSelected()){
                    JOptionPane.showMessageDialog(voucherFrame,"Menambahkan Voucher 11.11 dengan Diskon"+ tfDiscount.getText() +"% Berhasil!");
                    break;
                }
                else if (rvoucher2.isSelected()){
                    JOptionPane.showMessageDialog(voucherFrame,"Menambahkan Voucher 17 Agustus dengan Diskon"+ tfDiscount.getText() +"% Berhasil!");
                    break;
                }
                else if (rvoucher3.isSelected()){
                    JOptionPane.showMessageDialog(voucherFrame,"Menambahkan Voucher Anniversary dengan Diskon"+ tfDiscount.getText() +"% Berhasil!");
                    break;
                }
                else if (rvoucher4.isSelected()){
                    JOptionPane.showMessageDialog(voucherFrame,"Menambahkan Voucher Tahun Baru dengan Diskon"+ tfDiscount.getText() +"% Berhasil!");
                    break;
                }
                else if (rvoucher5.isSelected()){
                    JOptionPane.showMessageDialog(voucherFrame,"Menambahkan Voucher Lebaran dengan Diskon"+ tfDiscount.getText() +"% Berhasil!");
                    break;
                }
                else if (rvoucher6.isSelected()){
                    JOptionPane.showMessageDialog(voucherFrame,"Menambahkan Voucher Holiday dengan Diskon"+ tfDiscount.getText() +"% Berhasil!");
                    break;
                }
                else {
                    JOptionPane.showMessageDialog(voucherFrame,"Belum Memilih Voucher!");
                    break;
                }
                
            case "Exit":
                voucherFrame.dispose();
                JOptionPane.showMessageDialog(voucherFrame,"Terima kasih!");
                break;
        }
    }
}

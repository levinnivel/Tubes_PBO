/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author SKY-PC
 */
public class TopUp implements ActionListener {
    JFrame TopUpFrame = new JFrame();
    JLabel labTitle = new JLabel("      Top-up Menu!");
    JLabel labCredit = new JLabel("give us ur money plz");
    JLabel labPayMeth = new JLabel("Payment Method  : ");
    JLabel labTopAmount = new JLabel("Amount                   : ");
    JTextField tfTopAmount = new JTextField();
    JRadioButton rmethod1 = new JRadioButton("Wagon-Pay");
    JRadioButton rmethod2 = new JRadioButton("Transfer ATM");
    JRadioButton rmethod3 = new JRadioButton("OVO");
    JRadioButton rmethod4 = new JRadioButton("GO-PAY");
    JRadioButton rmethod5 = new JRadioButton("Alfamart");
    JRadioButton rmethod6 = new JRadioButton("Indomaret");
    JButton buttonTopUp = new JButton("Top-Up"); //Login by inserting username and password
    JButton buttonExit = new JButton("Exit");
    
    public TopUp(){
        TopUpFrame.setSize(500,350);
        TopUpFrame.setLocationRelativeTo(null);
        TopUpFrame.setLayout(null);
        TopUpFrame.setVisible(true);
        
        labTitle.setBounds(150,30, 200,20);
        labCredit.setBounds(160,50, 200,20);
        
        labPayMeth.setBounds(50,100, 150,30);
        labTopAmount.setBounds(50,180, 150,30);
        
        rmethod1.setBounds(200,100, 120,25);
        rmethod2.setBounds(200,120, 120,25);
        rmethod3.setBounds(200,140, 120,25);
        rmethod4.setBounds(350,100, 120,25);
        rmethod5.setBounds(350,120, 120,25);
        rmethod6.setBounds(350,140, 120,25);
        tfTopAmount.setBounds(200,180, 200,30);
        
        buttonTopUp.setBounds(215,240, 100,25);
        buttonTopUp.setActionCommand("Top-Up");
        buttonTopUp.addActionListener(this);
        
        buttonExit.setBounds(215,270, 100,25);
        buttonExit.setActionCommand("Exit");
        buttonExit.addActionListener(this);
        
        TopUpFrame.add(labTitle);
        TopUpFrame.add(labCredit);
        TopUpFrame.add(labPayMeth);
        TopUpFrame.add(labTopAmount);
        TopUpFrame.add(rmethod1);
        TopUpFrame.add(rmethod2);
        TopUpFrame.add(rmethod3);
        TopUpFrame.add(rmethod4);
        TopUpFrame.add(rmethod5);
        TopUpFrame.add(rmethod6);
        TopUpFrame.add(tfTopAmount);
        TopUpFrame.add(buttonTopUp);
        TopUpFrame.add(buttonExit);

    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Top-Up":
                TopUpFrame.dispose();
                JOptionPane.showMessageDialog(TopUpFrame,"Top-Up Sejumlah Rp"+ tfTopAmount.getText() +" Berhasil!");
                break;
            case "Exit":
                TopUpFrame.dispose();
                JOptionPane.showMessageDialog(TopUpFrame,"Terima kasih!");
                break;
        }
    }
}

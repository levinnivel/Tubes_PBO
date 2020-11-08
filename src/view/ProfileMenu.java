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

public class ProfileMenu implements ActionListener{ 
    //belum pakai db
    String fullName = "Andreas",phoneNum = "08973021998",address = "Bandung",email = "andreas@gmail.com";
    int point = 100;
    //frame
    JFrame proFrame = new JFrame();
    //label
    JLabel labTitle = new JLabel("Profile Menu");
    JLabel labName = new JLabel("Name: " + fullName);
    JLabel labPhoneNum = new JLabel("Phone Number: " + phoneNum);
    JLabel labEmail = new JLabel("Email: " + address);
    JLabel labAddress = new JLabel("Address: " + email);
    JLabel labPoint = new JLabel("Poin: " + point);
//    JLabel labBookings = new JLabel("Bookings");(history member)
    //tombol back
    JButton butBack = new JButton("Back");
    public ProfileMenu(){
        proFrame.setSize(600, 400);
        proFrame.setLocationRelativeTo(null);
        proFrame.setLayout(null);
        proFrame.setVisible(true);
        proFrame.setResizable(false);
        
        labTitle.setBounds(260, 10, 200, 30);
        labName.setBounds(50, 50, 200, 30);
        labPhoneNum.setBounds(50, 100, 200, 30);
        labEmail.setBounds(50, 150, 150, 30);
        labAddress.setBounds(50, 200, 200, 30);
        labPoint.setBounds(50, 250, 200, 30);
        
        butBack.setBounds(400, 300, 100, 30);
        
        proFrame.add(labTitle);
        proFrame.add(labName);
        proFrame.add(labPhoneNum);
        proFrame.add(labEmail);
        proFrame.add(labAddress);
        proFrame.add(labPoint);
        proFrame.add(butBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    }
}

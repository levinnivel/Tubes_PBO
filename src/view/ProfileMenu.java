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
import model.*;

public class ProfileMenu implements ActionListener{
//    String fullName = "Andreas",phoneNum = "08973021998",address = "Bandung",email = "andreas@gmail.com";
//    int point = 100;
    Member member = UserManager.getInstance().getMember();
    
    //frame
    JFrame proFrame = new JFrame();
    //label
    JLabel labTitle = new JLabel("Profile Menu");
    JLabel labName = new JLabel("Name: " + member.getFullName());
    JLabel labPhoneNum = new JLabel("Phone Number: " + member.getPhoneNum());
    JLabel labEmail = new JLabel("Email: " + member.getEmail());
    JLabel labAddress = new JLabel("Address: " + member.getAddress());
    JLabel labPoint = new JLabel("Poin: " + member.getPoint());
    JLabel labBalance = new JLabel("Balance: " + member.getBalance());
//    JLabel labBookings = new JLabel("Bookings");(history member)
    //tombol back
    JButton butBack = new JButton("Back");
    public ProfileMenu(){
        proFrame.setSize(600, 450);
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
        labBalance.setBounds(50, 300, 200, 30);
        
        butBack.setBounds(400, 350, 100, 30);
        butBack.setActionCommand("Back");
        butBack.addActionListener(this);
        
        proFrame.add(labTitle);
        proFrame.add(labName);
        proFrame.add(labPhoneNum);
        proFrame.add(labEmail);
        proFrame.add(labAddress);
        proFrame.add(labPoint);
        proFrame.add(labBalance);
        proFrame.add(butBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Back":
                proFrame.dispose();
                new GuestMemberMenu();
                break;
        }
    }
}

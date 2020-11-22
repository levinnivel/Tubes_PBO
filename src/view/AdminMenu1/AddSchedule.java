/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu1;

import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddSchedule implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();

    JFrame addFrame = new JFrame();
    
    JLabel labTitle = new JLabel("Insert New Schedule Data");
    JLabel labIDSchedule = new JLabel("ID Schedule : ");
    JLabel labIDRoute = new JLabel("ID Route : ");
    JLabel labIDPlane = new JLabel("ID Plane : ");
    JLabel labDepartureTime = new JLabel("Departure Time : ");
    JLabel labArrivalTime = new JLabel("Arrival Time : ");
    JLabel labDepartureDate = new JLabel("Departure Date : ");
    JLabel labArrivalDate = new JLabel("Arrival Date : ");
    
    JTextField tfIDSchedule = new JTextField();
    JTextField tfIDRoute = new JTextField();
    JTextField tfIDPlane = new JTextField();
    JTextField tfDepartureTime = new JTextField();
    JTextField tfArrivalTime = new JTextField();
    JTextField tfDepartureDate = new JTextField();
    JTextField tfArrivalDate = new JTextField();
    
    JButton buttonAdd = new JButton("Add Schedule");
    JButton buttonBack = new JButton("Back");
    
    public AddSchedule(){
        addFrame.setSize(600,400);
        addFrame.setLocationRelativeTo(null);
        addFrame.setLayout(null);
        addFrame.setVisible(true);
        
        labTitle.setBounds(200,30, 200,20);
        labIDSchedule.setBounds(50,70, 100,20);
        labIDRoute.setBounds(50,100, 100,20);
        labIDPlane.setBounds(50,130, 100,20);
        labDepartureTime.setBounds(50,160, 100,20);
        labArrivalTime.setBounds(50,190, 100,20);
        labDepartureDate.setBounds(50,220, 100,20);
        labArrivalDate.setBounds(50,250, 100,20);
        
        tfIDSchedule.setBounds(300,70, 200,20);
        tfIDRoute.setBounds(300,100, 200,20);
        tfIDPlane.setBounds(300,130, 200,20);
        tfDepartureTime.setBounds(300,160, 200,20);
        tfArrivalTime.setBounds(300,190, 200,20);
        tfDepartureDate.setBounds(300,220, 200,20);
        tfArrivalDate.setBounds(300,250, 200,20);
        
        buttonAdd.setBounds(350,290, 150,20);
        buttonAdd.setActionCommand("Add");
        buttonAdd.addActionListener(this);
        
        buttonBack.setBounds(50,290, 150,20);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        addFrame.add(labTitle);
        addFrame.add(labIDSchedule);
        addFrame.add(labIDRoute);
        addFrame.add(labIDPlane);
        addFrame.add(labDepartureTime);
        addFrame.add(labArrivalTime);
        addFrame.add(labDepartureDate);
        addFrame.add(labArrivalDate);
        addFrame.add(tfIDSchedule);
        addFrame.add(tfIDRoute);
        addFrame.add(tfIDPlane);
        addFrame.add(tfDepartureTime);
        addFrame.add(tfArrivalTime);
        addFrame.add(tfDepartureDate);
        addFrame.add(tfArrivalDate);
        addFrame.add(buttonAdd);
        addFrame.add(buttonBack);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Add":
                conn.connect();
//                mengecek data yang diadd ada atau tidak di database
                ArrayList<String> listIDs = Controller.getListIDSchedules();
                int j = 0;
                boolean isExist = false;
                while (listIDs.size()>j && !isExist) {
                    if(tfIDSchedule.getText().equals(listIDs.get(j))){
                        isExist = true;
                    }
                    System.out.println(listIDs.get(j));
                    j++;
                }
                
                if(!isExist){
                    String query = "INSERT INTO jadwal VALUES(?,?,?,?,?,?,?)";
                    try {
                        PreparedStatement stmt = conn.con.prepareStatement(query);
                        stmt.setString(1, tfIDSchedule.getText());
                        stmt.setString(2, tfIDRoute.getText());
                        stmt.setString(3, tfIDPlane.getText());
                        stmt.setString(4, tfDepartureTime.getText());
                        stmt.setString(5, tfArrivalTime.getText());
                        stmt.setString(6, tfDepartureDate.getText());
                        stmt.setString(7, tfArrivalDate.getText());
                        stmt.executeUpdate();
                        addFrame.dispose();
                        JOptionPane.showMessageDialog(addFrame,"Jadwal baru telah berhasil dibuat!");
                        new EditScheduleMainScreen();
                    } catch (SQLException excIns) {
                        excIns.printStackTrace();
                        JOptionPane.showMessageDialog(addFrame, "Jadwal baru gagal dibuat!", "Register Error",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(addFrame, "ID jadwal sudah ada!", "Register Error",JOptionPane.WARNING_MESSAGE);
                    addFrame.dispose();
                    new AddSchedule();
                }
                
                conn.disconnect();
            case "Back":
                addFrame.dispose();
                new EditScheduleMainScreen();
                break;
        }
    }
}

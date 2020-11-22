/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu2;

import model.*;
import view.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import controller.DatabaseHandler;
import controller.DateLabelFormatter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class UpdateScheduleForm implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame upFrame = new JFrame();
    
    String idJadwal = Global.getSelectedIDSchedule();
    
    JLabel labTitle = new JLabel("Update Schedule");
    JLabel labIDSchedule;
    JLabel labDepartureTime = new JLabel("Departure Time : ");
    JLabel labArrivalTime = new JLabel("Arrival Time : ");
    JLabel labDepartureDate = new JLabel("Departure Date : ");
    JLabel labArrivalDate = new JLabel("Arrival Date : ");

    JTextField tfDepartureTime;
    JTextField tfArrivalTime;
    
    JDatePanelImpl datePanelDepart;
    JDatePickerImpl datePickerDepart;
    UtilDateModel modelDepart;
    
    JDatePanelImpl datePanelArrive;
    JDatePickerImpl datePickerArrive;
    UtilDateModel modelArrive;
    
    JButton buttonUp = new JButton("Update Now");
    JButton buttonBack = new JButton("Back");
    
    public UpdateScheduleForm(){
        upFrame.setSize(600,400);
        upFrame.setLocationRelativeTo(null);
        upFrame.setLayout(null);
        upFrame.setVisible(true);
        
        labTitle.setBounds(250,30, 200,20);
        labIDSchedule = new JLabel("ID : " + idJadwal);
        labIDSchedule.setBounds(50,30, 100,20);
        
        String valDepartureTime = "";
        String valArrivalTime = "";
        
        conn.connect();
        
        String query = "SELECT * FROM jadwal WHERE idJadwal='" + idJadwal + "'";
        
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
               valDepartureTime = rs.getString("jamKeberangkatan");
               valArrivalTime = rs.getString("jamKedatangan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(upFrame, "Pengambilan waktu error!", "Get Error",JOptionPane.WARNING_MESSAGE);

        }
        conn.disconnect();
        
        labDepartureTime.setBounds(50,60, 100,20);
        labArrivalTime.setBounds(50,90, 100,20);
        
        tfDepartureTime = new JTextField(valDepartureTime);
        tfArrivalTime = new JTextField(valArrivalTime);
        tfDepartureTime.setBounds(300,60, 200,20);
        tfArrivalTime.setBounds(300,90, 200,20);
        
        labDepartureDate.setBounds(50,120, 100,20);
        modelDepart = new UtilDateModel();
        Properties pDepart = new Properties();
        pDepart.put("text.today", "Today");
        pDepart.put("text.month", "Month");
        pDepart.put("text.year", "Year");
        datePanelDepart = new JDatePanelImpl(modelDepart, pDepart);
        datePickerDepart = new JDatePickerImpl(datePanelDepart, new DateLabelFormatter());
        datePickerDepart.setBounds(300,120, 200,30);
        
        labArrivalDate.setBounds(50,150, 100,20);
        modelArrive = new UtilDateModel();
        Properties pArrive = new Properties();
        pArrive.put("text.today", "Today");
        pArrive.put("text.month", "Month");
        pArrive.put("text.year", "Year");
        datePanelArrive = new JDatePanelImpl(modelArrive, pArrive);
        datePickerArrive = new JDatePickerImpl(datePanelArrive, new DateLabelFormatter());
        datePickerArrive.setBounds(300,150, 200,30);
        
        buttonUp.setBounds(350,190, 150,20);
        buttonUp.setActionCommand("Update");
        buttonUp.addActionListener(this);
        
        buttonBack.setBounds(50,190, 150,20);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        upFrame.add(labTitle);
        upFrame.add(labIDSchedule);
        upFrame.add(labDepartureTime);
        upFrame.add(labArrivalTime);
        upFrame.add(labDepartureDate);
        upFrame.add(labArrivalDate);
        upFrame.add(tfDepartureTime);
        upFrame.add(tfArrivalTime);
        upFrame.add(datePickerDepart);
        upFrame.add(datePickerArrive);
        upFrame.add(buttonUp);
        upFrame.add(buttonBack);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Update":
                conn.connect();
                
                String valDepartureTime = tfDepartureTime.getText();
                String valArrivalTime = tfArrivalTime.getText();
                String valDepartureDate = modelDepart.getYear() + "-" + (modelDepart.getMonth()+1) + "-" + modelDepart.getDay();;
                String valArrivalDate = modelArrive.getYear() + "-" + (modelArrive.getMonth()+1) + "-" + modelArrive.getDay();
                
                String query = "UPDATE jadwal SET jamKeberangkatan='" + valDepartureTime + "', "
                + "jamKedatangan='" + valArrivalTime + "', "
                + "dateKeberangkatan='" + valDepartureDate + "', "
                + "dateKedatangan='" + valArrivalDate + "' "
                + " WHERE idJadwal='" + idJadwal + "'";
                try {
                    Statement stmt = conn.con.createStatement();
                    stmt.executeUpdate(query);
                    upFrame.dispose();
                    JOptionPane.showMessageDialog(upFrame,"Jadwal telah berhasil diperbaharui!");
                    new RescheduleMainScreen();
                } catch (SQLException excUp) {
                    excUp.printStackTrace();
                    JOptionPane.showMessageDialog(upFrame, "Jadwal gagal diperbaharui!", "Update Error",JOptionPane.WARNING_MESSAGE);
                }
                
                conn.disconnect();
                break;
            case "Back":
                upFrame.dispose();
                new RescheduleMainScreen();
                break;
        }
    }
}

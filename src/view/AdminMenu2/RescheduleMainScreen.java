/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu2;

import static controller.Controller.buildTableModel;
import controller.*;
import model.*;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.event.ListSelectionEvent;

public class RescheduleMainScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame resFrame = new JFrame(); 
    
    JTable tabSchedule;
    
    JLabel labTitle = new JLabel("Edit Schedule");
    JLabel labList = new JLabel("List Pesawat");
    JLabel labNote = new JLabel("Harap klik idJadwal untuk memilih.");
    
    JButton buttonResch = new JButton("Reschedule");
    JButton buttonBack = new JButton("Back");
    
    String selectedData = null;
    
    public RescheduleMainScreen(){
        resFrame.setTitle("Reschedule");
        resFrame.setSize(1300, 550);
        resFrame.setLocationRelativeTo(null);
        resFrame.setLayout(null);
        resFrame.setVisible(true);
        
        conn.connect();
        String query = "SELECT a.idJadwal, b.idPesawat, c.idRute, c.destinasiAsal, c.destinasiAkhir, "
                + "a.jamKeberangkatan, a.jamKedatangan, a.dateKeberangkatan, a.dateKedatangan, "
                + "b.tipePesawat, b.hargaKursi, b.kapasitasBagasi, b.hargaBagasiPerKg "
                + "FROM jadwal a JOIN pesawat b ON a.idPesawat=b.idPesawat JOIN rute c ON a.idRute=c.idRute";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            tabSchedule = new JTable(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        untuk mengambbil data dari jtable onclick
        tabSchedule.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = tabSchedule.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //lambda expression?
        cellSelectionModel.addListSelectionListener((ListSelectionEvent eTab) -> {
            int[] selectedRow = tabSchedule.getSelectedRows();
            int[] selectedColumns = tabSchedule.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
                for (int j = 0; j < selectedColumns.length; j++) {
                    selectedData = (String) tabSchedule.getValueAt(selectedRow[i], selectedColumns[j]);
                }
            }
        });
        
        JScrollPane sp = new JScrollPane(tabSchedule);
        sp.setBounds(30, 90, 1230, 300);
        
        labTitle.setBounds(600,10, 100,15);
        labList.setBounds(600,30, 100,15);
        labList.setBounds(600,60, 100,15);
        
        buttonResch.setActionCommand("Reschedule");
        buttonResch.addActionListener(this);
        buttonResch.setBounds(300,420, 200,40);
        
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        buttonBack.setBounds(100,420, 100,40);
        
        resFrame.add(sp);
        resFrame.add(labTitle);
        resFrame.add(labList);
        resFrame.add(buttonResch);
        resFrame.add(buttonBack);
        
        conn.disconnect();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Reschedule":
                Global.setSelectedIDSchedule(selectedData);
                resFrame.dispose();
                new UpdateScheduleForm();
                break;
            case "Back":
                resFrame.dispose();
                new AdminMenu();
                break;
        }
    }
}
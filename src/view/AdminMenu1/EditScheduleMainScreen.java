/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu1;

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
import javax.swing.event.ListSelectionListener;

public class EditScheduleMainScreen implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    JFrame editFrame = new JFrame(); 
    
    JTable tabSchedule;
    
    JLabel labTitle = new JLabel("Edit Schedule");
    JLabel labList = new JLabel("List Pesawat");
    JLabel labNote = new JLabel("Harap klik idJadwal untuk memilih.");
    
    JButton buttonAdd = new JButton("Add");
    JButton buttonDelete = new JButton("Delete");
    JButton buttonBack = new JButton("Back");
    
    String selectedData = null;
    
    public EditScheduleMainScreen(){
        editFrame.setSize(1300, 550);
        editFrame.setLocationRelativeTo(null);
        editFrame.setLayout(null);
        editFrame.setVisible(true);
        
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
        labNote.setBounds(30,60, 250,15);
        
        buttonAdd.setActionCommand("Add");
        buttonAdd.addActionListener(this);
        buttonAdd.setBounds(600,420, 100,40);
        
        buttonDelete.setActionCommand("Delete");
        buttonDelete.addActionListener(this);
        buttonDelete.setBounds(1000,420, 100,40);
        
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        buttonBack.setBounds(100,420, 100,40);
        
        editFrame.add(sp);
        editFrame.add(labTitle);
        editFrame.add(labList);
        editFrame.add(labNote);
        editFrame.add(buttonAdd);
        editFrame.add(buttonDelete);
        editFrame.add(buttonBack);
        
        conn.disconnect();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Add":
                editFrame.dispose();
                new AddSchedule();
                break;
            case "Delete":
                String id = selectedData;
                conn.connect();
                
                String query = "DELETE FROM jadwal WHERE idJadwal='" + id + "'";
                try {
                    Statement stmt = conn.con.createStatement();
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(editFrame,"Penghapusan jadwal dengan ID " + id + " telah berhasil!");
                    editFrame.dispose();
                    new EditScheduleMainScreen();
                } catch (SQLException excDel) {
                    excDel.printStackTrace();
                }
                conn.disconnect();
                break;
            case "Back":
                editFrame.dispose();
                new AdminMenu();
                break;
        }
    }

//    public static void main(String[] args){
//        new EditScheduleMainScreen();
//    }
}

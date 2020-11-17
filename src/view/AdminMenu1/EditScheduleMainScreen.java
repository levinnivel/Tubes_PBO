/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu1;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class EditScheduleMainScreen implements ActionListener{
    JFrame editMainFrame = new JFrame();
    
    Object data[][]={
        {"CL01","Citilink-123",670000,25,20000}, 
        {"BA01","Batik Air-UB1",780000,20,25000},    
        {"BA02","Batik Air-AX2",700000,20,25000},
        {"SR01","Sriwijaya-ZZA12",500000,30,30000},
    };    
    String column[]={"ID","TIPE","HARGA_KURSI","KAPASITAS_BAGASI","HARGA_BAGASI_PER_KG"};   
    
    JTable tabSchedule = new JTable(data, column);
    
    JLabel labTitle = new JLabel("Edit Schedule");
    JLabel labList = new JLabel("List Pesawat");
    
    JButton buttonAdd = new JButton("Add");
    JButton buttonDelete = new JButton("Delete");
    
    public EditScheduleMainScreen(){
        editMainFrame.setSize(1000,700);
        editMainFrame.setLocationRelativeTo(null);
        editMainFrame.setLayout(null);
        editMainFrame.setVisible(true);
        
        
        JScrollPane sp = new JScrollPane(tabSchedule);
        sp.setBounds(50,50, 900,500);
        
        labTitle.setBounds(450,10, 100,15);
        labList.setBounds(450,30, 100,15);
        
        buttonAdd.setActionCommand("Add");
        buttonAdd.addActionListener(this);
        buttonAdd.setBounds(250,600, 100,40);
        
        buttonDelete.setActionCommand("Delete");
        buttonDelete.addActionListener(this);
        buttonDelete.setBounds(650,600, 100,40);
        
        editMainFrame.add(sp);
        editMainFrame.add(labTitle);
        editMainFrame.add(labList);
        editMainFrame.add(buttonAdd);
        editMainFrame.add(buttonDelete);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Add":
                editMainFrame.dispose();
                new AddSchedule();
                break;
            case "Delete":
                editMainFrame.dispose();
                new DeleteSchedule();
                break;
        }
    }

//    public static void main(String[] args){
//        new EditScheduleMainScreen();
//    }
}

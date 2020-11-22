/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu3;

import model.*;
import view.*;
import controller.DateLabelFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class SearchPesawat implements ActionListener{
    //frame
    JFrame bookingFrame = new JFrame();
    
    Member member = UserManager.getInstance().getMember();
    
    //datepicker
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    UtilDateModel model;
    //label
    JLabel labTitle = new JLabel("Form Pencarian Pesawat");
    JLabel labKotaAsal = new JLabel("Kota Asal");
    JLabel labKotaTujuan = new JLabel("Kota Tujuan");
    JLabel labTanggalKeberangkatan = new JLabel("Tanggal Keberangkatan");
    JLabel labTipePenerbangan = new JLabel("Kelas Penerbangan");
    JLabel labJmlPenumpang = new JLabel("Jumlah Penumpang");
    //text field
    JTextField tfKotaAsal = new JTextField();
    JTextField tfKotaTujuan = new JTextField();
    //combo box
    JComboBox cbTipePenerbangan = new JComboBox();
    //button
    JButton buttonSearch = new JButton("Search");
    JButton buttonBack = new JButton("Back");
    
    public SearchPesawat(){
        //set frame
        bookingFrame.setTitle("Search Pesawat");
        bookingFrame.setSize(600, 400);
        bookingFrame.setLocationRelativeTo(null);
        bookingFrame.setLayout(null);
        bookingFrame.setVisible(true);
        bookingFrame.setResizable(false);
        
        labTitle.setBounds(220, 10, 200, 30);
        //label kota asal dan text field
        labKotaAsal.setBounds(50, 50, 150, 30);
        tfKotaAsal.setBounds(50, 90, 200, 30);
        
        //label kota tujuan dan text field
        labKotaTujuan.setBounds(300, 50, 150, 30);
        tfKotaTujuan.setBounds(300, 90, 200, 30);
        
        //date picker tanggal
        labTanggalKeberangkatan.setBounds(50, 140, 200, 30);
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(300, 140, 200, 30);
        
        //label tipe penerbangan dan combo box
        labTipePenerbangan.setBounds(50, 180, 200, 30);
        String sTipePenerbangan[]={"Economy","Business","First Class"};
        cbTipePenerbangan = new JComboBox(sTipePenerbangan);
        cbTipePenerbangan.setBounds(300, 180, 100, 30);
        
        //button cari jadwal
        buttonSearch.setBounds(400, 300, 100, 30);
        buttonSearch.setActionCommand("Search");
        buttonSearch.addActionListener(this);
        
        buttonBack.setBounds(200, 300, 100, 30);
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        
        //add to frame
        bookingFrame.add(labTitle);
        bookingFrame.add(labKotaAsal);
        bookingFrame.add(tfKotaAsal);
        bookingFrame.add(labKotaTujuan);
        bookingFrame.add(tfKotaTujuan);
        bookingFrame.add(labTanggalKeberangkatan);
        bookingFrame.add(labTipePenerbangan);
        bookingFrame.add(cbTipePenerbangan);
        bookingFrame.add(datePicker);
        bookingFrame.add(buttonSearch);
        bookingFrame.add(buttonBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Search":
                String valKotaAsal = tfKotaAsal.getText();
                String valKotaTujuan = tfKotaTujuan.getText();
                String valTglBerangkat = model.getYear() + "-" + (model.getMonth()+1) + "-" + model.getDay();
                String valTipe = cbTipePenerbangan.getSelectedItem().toString();;
                
                Global.setPassengerAmount(Integer.parseInt(JOptionPane.showInputDialog(bookingFrame,"Berapa banyak penumpang? (MAX 5)")));     
                
                int passAmount = Global.getPassengerAmount();
                
                if(passAmount<=5 && passAmount>0){
                    bookingFrame.dispose();
                    new ChooseSchedule(valKotaAsal, valKotaTujuan, valTglBerangkat, valTipe);
                }else{
                   JOptionPane.showMessageDialog(bookingFrame, "Harap kurangi penumpang Anda.", "Pass Error",JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "Back":
                bookingFrame.dispose();
                new GuestMemberMenu();
        }
    }
}

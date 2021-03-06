/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import view.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {
    conn.connect();
    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }
    conn.disconnect();

    return new DefaultTableModel(data, columnNames);

    }
    
    //mengambil id jadwal ke dalam arraylist
    public static ArrayList<String> getListIDSchedules(){
        ArrayList<String> IDs = new ArrayList();
        conn.connect();
        String query = "SELECT idJadwal FROM jadwal";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                IDs.add(rs.getString("idJadwal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (IDs);
    }
    
    //mengambil id booking terakhir dari database
    public static int getLastIDBookingInteger(){
        String ID = "";
        conn.connect();
        String query = "SELECT idBooking FROM booking WHERE idBooking=(SELECT max(idBooking) FROM booking)";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                ID = rs.getString("idBooking");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        int endIdx = ID.length();
        int countID = Integer.parseInt(ID.substring(2,endIdx));
        
        conn.disconnect();
        return (countID);
    }
    
    //mengambil id penumpang terakhir yang ada di database
    public static int getLastIDPenumpangInteger(){
        String ID = "";
        conn.connect();
        String query = "SELECT idPenumpang FROM penumpang WHERE idPenumpang=(SELECT max(idPenumpang) FROM Penumpang)";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                ID = rs.getString("idPenumpang");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        int endIdx = ID.length();
        int countID = Integer.parseInt(ID.substring(2,endIdx));
        conn.disconnect();
        return (countID);
    }
    
    //mengambil kursi yang tersedia di database
    public static ArrayList<String> getAllAvailableSeats(String idPesawat) {
        ArrayList<String> kursi = new ArrayList<>();
        conn.connect();
        String query = "SELECT noKursi FROM kursi WHERE idPesawat='" + idPesawat + "' AND statusKursi='KOSONG'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                kursi.add(rs.getString("noKursi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (kursi);
    }
    
    //konversi dari array list ke array string
    public static String[] getStringArray(ArrayList<String> arr) 
    { 
  
        // declaration and initialise String Array 
        String str[] = new String[arr.size()]; 
  
        // Convert ArrayList to object array 
        Object[] objArr = arr.toArray(); 
  
        // Iterating and converting to String 
        int i = 0; 
        for (Object obj : objArr) { 
            str[i++] = (String)obj; 
        } 
  
        return str; 
    } 
}

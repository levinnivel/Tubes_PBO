/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MemberMenu3;

import controller.Controller;
import javax.swing.*;
import model.*;
import static controller.Controller.buildTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import static model.PaidEnum.*;
import static model.ActiveEnum.*;

public class ChooseSchedule implements ActionListener{
    static DatabaseHandler conn = new DatabaseHandler();
    
    Member member = UserManager.getInstance().getMember();
    
    JFrame schFrame = new JFrame();
    
    //jlabel
    JLabel labTitle = new JLabel("Pesawat yang Tersedia");
    JLabel labNote = new JLabel("Harap klik idJadwal untuk memilih.");
    
    JTable tabPesawat;
    
    String selectedData = null;
    
    JButton buttonSubmit = new JButton("Submit");
    
    public ChooseSchedule(String kotaAsal, String kotaTujuan, String tglBerangkat,String tipe){
        schFrame.setSize(1300, 500);
        schFrame.setLocationRelativeTo(null);
        schFrame.setLayout(null);
        schFrame.setVisible(true);
        
//      mengambil data sesuai masukan member berdasarkan kota asal, kota tujuan, tipe pesawat, dan tanggal berangkat
        conn.connect();
        String query = "SELECT a.idJadwal, b.idPesawat, b.kodeMaskapai, c.destinasiAsal, c.destinasiAkhir, "
                + "a.jamKeberangkatan, a.jamKedatangan, a.dateKeberangkatan, a.dateKedatangan, "
                + "b.tipePesawat, b.hargaKursi, b.kapasitasBagasi, b.hargaBagasiPerKg, "
                + "d.noTelpMaskapai, d.namaMaskapai "
                + "FROM jadwal a JOIN pesawat b ON a.idPesawat=b.idPesawat JOIN rute c ON a.idRute=c.idRute JOIN maskapai d ON b.kodeMaskapai=d.kodeMaskapai "
                + "WHERE c.destinasiAsal='" + kotaAsal + "' AND c.destinasiAkhir='" + kotaTujuan +"' AND b.tipePesawat='" + tipe + "' AND a.dateKedatangan='" + tglBerangkat + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            tabPesawat = new JTable(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
//      memilih tabel
        tabPesawat.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = tabPesawat.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //lambda expression?
        cellSelectionModel.addListSelectionListener((ListSelectionEvent eTab) -> {
            int[] selectedRow = tabPesawat.getSelectedRows();
            int[] selectedColumns = tabPesawat.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
                for (int j = 0; j < selectedColumns.length; j++) {
                    selectedData = (String) tabPesawat.getValueAt(selectedRow[i], selectedColumns[j]);
                }
            }
        });
        
        labTitle.setBounds(600, 30, 200, 20);
        labNote.setBounds(30, 60, 200, 20);
        JScrollPane sp = new JScrollPane(tabPesawat);
        sp.setBounds(30, 90, 1230, 300);
        
        buttonSubmit.setBounds(1100, 400, 100, 30);
        buttonSubmit.setActionCommand("Submit");
        buttonSubmit.addActionListener(this);
        
        schFrame.add(labTitle);
        schFrame.add(labNote);
        schFrame.add(sp);
        schFrame.add(buttonSubmit);
        
        conn.disconnect();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Submit":
                if(selectedData!=null){
                    conn.connect();

                    Date date = Calendar.getInstance().getTime();  
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
                    String strDate = dateFormat.format(date);  

                    Booking booking = new Booking();
                    JadwalPesawat jadwal = new JadwalPesawat();
                    Rute rute = new Rute();
                    Pesawat pesawat = new Pesawat();
                    
                    jadwal.setIdJadwal(selectedData);
                    booking.setIdBooking("B-" + (Controller.getLastIDBookingInteger()+1));
                    booking.setDateBooking(strDate);
                    booking.setIsPaid(BELUM_LUNAS);
                    booking.setIsActive(INACTIVE);

                    boolean status1 = false;
                    String query1 = "SELECT * FROM jadwal WHERE idJadwal='" + selectedData + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        ResultSet rs = stmt.executeQuery(query1);
                        while (rs.next()) {
                            jadwal.setIdJadwal(rs.getString("idJadwal"));
                            rute.setIdRute(rs.getString("idRute"));
                            pesawat.setIdPesawat(rs.getString("idPesawat"));
                            jadwal.setDateKeberangkatan(rs.getString("dateKeberangkatan"));
                            jadwal.setDateKedatangan(rs.getString("dateKedatangan"));
                            jadwal.setJamKeberangkatan(rs.getString("jamKeberangkatan"));
                            jadwal.setJamKedatangan(rs.getString("jamKedatangan"));
                        }
                        status1 = true;
                    } catch (SQLException excGet) {
                        excGet.printStackTrace();
                    }

                    boolean status2 = false;
                    String query2 = "SELECT * FROM rute WHERE idRute='" + jadwal.getIdJadwal() + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        ResultSet rs = stmt.executeQuery(query2);
                        while (rs.next()) {
                            rute.setDestinasiAsal(rs.getString("destinasiAsal"));
                            rute.setDestinasiTujuan(rs.getString("destinasiTujuan"));
                        }
                        status2 = true;
                    } catch (SQLException excGet) {
                        excGet.printStackTrace();
                    }

                    boolean status3 = false;
                    String query3 = "SELECT * FROM pesawat WHERE idPesawat='" + pesawat.getIdPesawat() + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        ResultSet rs = stmt.executeQuery(query3);
                        while (rs.next()) {
                            pesawat.setIdPesawat(rs.getString("idPesawat"));
                            pesawat.setKodeMaskapai(rs.getString("kodeMaskapai"));
                            pesawat.setTipePesawat(rs.getString("tipePesawat"));
                            pesawat.setHargaKursi(rs.getInt("hargaKursi"));
                            pesawat.setKapasitasBagasi(rs.getInt("kapasitasBagasi"));
                            pesawat.setHargaBagasiPerKg(rs.getInt("hargaBagasiPerKg"));
                        }
                        status3 = true;
                    } catch (SQLException excGet) {
                        excGet.printStackTrace();
                    }
                    
                    boolean status4 = false;
                    String query4 = "SELECT * FROM maskapai WHERE kodeMaskapai='" + pesawat.getKodeMaskapai() + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        ResultSet rs = stmt.executeQuery(query4);
                        while (rs.next()) {
                            pesawat.setNotelpMaskapai(rs.getString("noTelpMaskapai"));
                            pesawat.setNamaMaskapai(rs.getString("namaMaskapai"));
                        }
                        status4 = true;
                    } catch (SQLException excGet) {
                        excGet.printStackTrace();
                    }

                    if(status1 && status2 && status3 && status4){
                        jadwal.setPesawat(pesawat);
                        booking.setSchedule(jadwal);
                        
                        JOptionPane.showMessageDialog(schFrame,"Pengambilan dari database berhasil!");
                        
                        BookingManager.getInstance().setBooking(booking);
                        
                        String query5 = "INSERT INTO booking VALUES(?,?,?,?,?,?,?)";
                        try {
                            PreparedStatement stmt = conn.con.prepareStatement(query5);
                            stmt.setString(1, booking.getIdBooking());
                            stmt.setString(2, member.getEmail());
                            stmt.setString(3, booking.getSchedule().getIdJadwal());
                            stmt.setString(4, booking.getDateBooking());
                            stmt.setInt(5, 0);
                            stmt.setString(6, "BELUM_LUNAS");
                            stmt.setString(7, "INACTIVE");
                            stmt.executeUpdate();
                        } catch (SQLException excIns) {
                            excIns.printStackTrace();
                            JOptionPane.showMessageDialog(schFrame, "Detail gagal!", "Detail Error",JOptionPane.WARNING_MESSAGE);
                        }
                        
                        schFrame.dispose();
                        new PassengerFormScreen();
                        
                    }else{
                        JOptionPane.showMessageDialog(schFrame, "Pengambilan gagal!", "Get Error",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(schFrame, "Silahkan klik ID Jadwal yang ingin dipesan.", "Select Error",JOptionPane.WARNING_MESSAGE);
                }
                break;
        }
    }
}

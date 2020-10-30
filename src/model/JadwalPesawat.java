/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class JadwalPesawat {
    private String dateKeberangkatan;
    private String dateKedatangan;
    private String jamKedatangan;
    private String jamKeberangkatan;
    private Pesawat pesawat;
    private Rute rute;
    
    public JadwalPesawat(){
        
    }

    public JadwalPesawat(String dateKeberangkatan, String dateKedatangan, String jamKedatangan, String jamKeberangkatan, Pesawat pesawat, Rute rute) {
        this.dateKeberangkatan = dateKeberangkatan;
        this.dateKedatangan = dateKedatangan;
        this.jamKedatangan = jamKedatangan;
        this.jamKeberangkatan = jamKeberangkatan;
        this.pesawat = pesawat;
        this.rute = rute;
    }

    public String getDateKeberangkatan() {
        return dateKeberangkatan;
    }

    public void setDateKeberangkatan(String dateKeberangkatan) {
        this.dateKeberangkatan = dateKeberangkatan;
    }

    public String getDateKedatangan() {
        return dateKedatangan;
    }

    public void setDateKedatangan(String dateKedatangan) {
        this.dateKedatangan = dateKedatangan;
    }

    public String getJamKedatangan() {
        return jamKedatangan;
    }

    public void setJamKedatangan(String jamKedatangan) {
        this.jamKedatangan = jamKedatangan;
    }

    public String getJamKeberangkatan() {
        return jamKeberangkatan;
    }

    public void setJamKeberangkatan(String jamKeberangkatan) {
        this.jamKeberangkatan = jamKeberangkatan;
    }

    public Pesawat getPesawat() {
        return pesawat;
    }

    public void setPesawat(Pesawat pesawat) {
        this.pesawat = pesawat;
    }

    public Rute getRute() {
        return rute;
    }

    public void setRute(Rute rute) {
        this.rute = rute;
    }

    
    
}

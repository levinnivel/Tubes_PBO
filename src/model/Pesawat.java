/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Andreas
 */
public class Pesawat {
    String idPesawat;
    String tipePesawat;
    int hargaKursi;
    int hargaBagasiPerKG;
    int kapasitasBagasi;
    
    public Pesawat(){
        
    }

    public Pesawat(String idPesawat, String tipePesawat, int hargaKursi, int hargaBagasiPerKG, int kapasitasBagasi) {
        this.idPesawat = idPesawat;
        this.tipePesawat = tipePesawat;
        this.hargaKursi = hargaKursi;
        this.hargaBagasiPerKG = hargaBagasiPerKG;
        this.kapasitasBagasi = kapasitasBagasi;
    }

    public String getIdPesawat() {
        return idPesawat;
    }

    public void setIdPesawat(String idPesawat) {
        this.idPesawat = idPesawat;
    }

    public String getTipePesawat() {
        return tipePesawat;
    }

    public void setTipePesawat(String tipePesawat) {
        this.tipePesawat = tipePesawat;
    }

    public int getHargaKursi() {
        return hargaKursi;
    }

    public void setHargaKursi(int hargaKursi) {
        this.hargaKursi = hargaKursi;
    }

    public int getHargaBagasiPerKG() {
        return hargaBagasiPerKG;
    }

    public void setHargaBagasiPerKG(int hargaBagasiPerKG) {
        this.hargaBagasiPerKG = hargaBagasiPerKG;
    }

    public int getKapasitasBagasi() {
        return kapasitasBagasi;
    }

    public void setKapasitasBagasi(int kapasitasBagasi) {
        this.kapasitasBagasi = kapasitasBagasi;
    }
}

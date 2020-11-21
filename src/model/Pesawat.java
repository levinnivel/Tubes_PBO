/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Pesawat extends Maskapai{
    String idPesawat;
    String tipePesawat;
    int hargaKursi;
    int kapasitasBagasi;
    int hargaBagasiPerKg;
    
    public Pesawat(){
        
    }

    public Pesawat(String idPesawat, String tipePesawat, int hargaKursi, int kapasitasBagasi, int hargaBagasiPerKg, String kodeMaskapai, String notelpMaskapai, String namaMaskapai) {
        super(kodeMaskapai, notelpMaskapai, namaMaskapai);
        this.idPesawat = idPesawat;
        this.tipePesawat = tipePesawat;
        this.hargaKursi = hargaKursi;
        this.kapasitasBagasi = kapasitasBagasi;
        this.hargaBagasiPerKg = hargaBagasiPerKg;
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

    public int getKapasitasBagasi() {
        return kapasitasBagasi;
    }

    public void setKapasitasBagasi(int kapasitasBagasi) {
        this.kapasitasBagasi = kapasitasBagasi;
    }

    public int getHargaBagasiPerKg() {
        return hargaBagasiPerKg;
    }

    public void setHargaBagasiPerKg(int hargaBagasiPerKg) {
        this.hargaBagasiPerKg = hargaBagasiPerKg;
    }
    
}

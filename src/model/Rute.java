/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Rute {
    private String idRute;
    private String destinasiAsal;
    private String destinasiTujuan;
    
    public Rute(){
        
    }

    public Rute(String idRute, String destinasiAsal, String destinasiTujuan) {
        this.idRute = idRute;
        this.destinasiAsal = destinasiAsal;
        this.destinasiTujuan = destinasiTujuan;
    }

    public String getIdRute() {
        return idRute;
    }

    public void setIdRute(String idRute) {
        this.idRute = idRute;
    }
    
    public String getDestinasiAsal() {
        return destinasiAsal;
    }

    public void setDestinasiAsal(String destinasiAsal) {
        this.destinasiAsal = destinasiAsal;
    }

    public String getDestinasiTujuan() {
        return destinasiTujuan;
    }

    public void setDestinasiTujuan(String destinasiTujuan) {
        this.destinasiTujuan = destinasiTujuan;
    }
    
    
}

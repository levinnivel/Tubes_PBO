/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public abstract class Maskapai {
    private String kodeMaskapai;
    private String notelpMaskapai;
    private String namaMaskapai;
    
    public Maskapai(){
        
    }

    public Maskapai(String kodeMaskapai, String notelpMaskapai, String namaMaskapai) {
        this.kodeMaskapai = kodeMaskapai;
        this.notelpMaskapai = notelpMaskapai;
        this.namaMaskapai = namaMaskapai;
    }

    public String getKodeMaskapai() {
        return kodeMaskapai;
    }

    public String getNotelpMaskapai() {
        return notelpMaskapai;
    }

    public String getNamaMaskapai() {
        return namaMaskapai;
    }

    public void setKodeMaskapai(String kodeMaskapai) {
        this.kodeMaskapai = kodeMaskapai;
    }

    public void setNotelpMaskapai(String notelpMaskapai) {
        this.notelpMaskapai = notelpMaskapai;
    }

    public void setNamaMaskapai(String namaMaskapai) {
        this.namaMaskapai = namaMaskapai;
    }
    
    
}

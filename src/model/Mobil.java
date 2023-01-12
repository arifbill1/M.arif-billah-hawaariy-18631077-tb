/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author arif billah
 */
public class Mobil {

    private String nopol;
    private String merk;
    private String warna;
    private int tahun;
    private int hargaSewa;
    private boolean status;

    public Mobil() {
    }

    public Mobil(String nopol, String merk, String warna, int tahun, int hargaSewa, boolean status) {
        this.nopol = nopol;
        this.merk = merk;
        this.warna = warna;
        this.tahun = tahun;
        this.hargaSewa = hargaSewa;
        this.status = status;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
        return merk + " " + nopol;
    }
}

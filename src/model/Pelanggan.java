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
public class Pelanggan {

    private String ktp;
    private String nama;
    private String alamat;
    private String hp;

    public Pelanggan() {
    }

    public Pelanggan(String ktp, String nama, String alamat, String hp) {
        this.ktp = ktp;
        this.nama = nama;
        this.alamat = alamat;
        this.hp = hp;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }
    
    @Override
    public String toString(){
        return nama;
    }
}

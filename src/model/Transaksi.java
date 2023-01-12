/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.DateHelper;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import db.Koneksi;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author arif billah
 */
public class Transaksi {

    

    private int id;
    private String ktp;
    private String nama;
    private String nopol;
    private String merk;
    private int hargaSewa;
    private String waktuMulai;
    private String waktuSelesai;

    public Transaksi() {
    }

    public Transaksi(int id, String ktp, String nama, String nopol, String merk, int hargaSewa, String waktuMulai, String waktuSelesai) {
        this.id = id;
        this.ktp = ktp;
        this.nama = nama;
        this.nopol = nopol;
        this.merk = merk;
        this.hargaSewa = hargaSewa;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
    }

    public int getHariSewa() throws ParseException {
        Date mulai = DateHelper.stringToDate(this.waktuMulai);
        LocalDate mulaiDate = mulai.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date selesai = DateHelper.stringToDate(this.waktuSelesai);
        LocalDate selesaiDate = selesai.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysBetween = Duration.between(mulaiDate.atStartOfDay(), selesaiDate.atStartOfDay()).toDays();
        return (int) daysBetween;
    }

    public int getTotalHarga() throws ParseException {
        int totalHari = getHariSewa();
        return totalHari * this.hargaSewa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    
}

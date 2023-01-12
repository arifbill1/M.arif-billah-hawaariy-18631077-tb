/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import db.Koneksi;
import static java.awt.PageAttributes.MediaType.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Transaksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;



/**
 *
 * @author arif billah
 */
public class TransaksiFrame extends javax.swing.JFrame {

    int gTransaksiCount = 0;
    int gTotalSewa = 0;
    private Object report1;
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    Map<String,Object> param =  new HashMap<String,Object>();
    JasperPrint jasperPrint;

    /**
     * Creates new form TransasksiFrame
     */
    public TransaksiFrame() {
        initComponents();
        setLocationRelativeTo(null);
        resetTable();
    }

    public void setHeader() {
        tvJumlahSewa.setText("Jumlah sewa : " + gTransaksiCount);
        tvPendapatan.setText("Total pendapatan kotor : " + gTotalSewa);
    }

    public ArrayList<Transaksi> getTransaksiList() throws ParseException {

        gTransaksiCount = 0;
        gTotalSewa = 0;

        ArrayList<Transaksi> transaksiList = new ArrayList<>();
        Koneksi koneksi = new Koneksi();
        Connection connection = koneksi.getConnection();

        String query = "SELECT s.id AS id, s.ktp as KTP, p.nama as nama, s.nopol as nopol, m.merk as merk, s.harga_sewa as harga_sewa, s.waktu_mulai as waktu_mulai, s.waktu_selesai as waktu_selesai "
                + "FROM sewa s "
                + "LEFT JOIN mobil m USING(nopol) "
                + "LEFT JOIN pelanggan p USING(ktp)";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(
                        rs.getInt("id"),
                        rs.getString("ktp"),
                        rs.getString("nama"),
                        rs.getString("nopol"),
                        rs.getString("merk"),
                        rs.getInt("harga_sewa"),
                        rs.getString("waktu_mulai"),
                        rs.getString("waktu_selesai"));
                transaksiList.add(transaksi);

                gTransaksiCount += 1;
                gTotalSewa = transaksi.getTotalHarga();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Gagal melakukan query : " + ex.getMessage());
        }

        setHeader();

        return transaksiList;
    }

    public void selectTransaksi() throws ParseException {
        ArrayList<Transaksi> list = getTransaksiList();
        DefaultTableModel model = (DefaultTableModel) tbTransaksi.getModel();
        Object[] row = new Object[9];

        for (int i = 0; i < list.size(); i++) {

            String hariSewa = list.get(i).getHariSewa() + " hari";

            row[0] = list.get(i).getId();
            row[1] = list.get(i).getNama();
            row[2] = list.get(i).getMerk();
            row[3] = list.get(i).getNopol();
            row[4] = hariSewa;
            row[5] = list.get(i).getTotalHarga();
            row[6] = list.get(i).getWaktuMulai();
            row[7] = list.get(i).getWaktuSelesai();
            row[8] = list.get(i).getKtp();

            model.addRow(row);
        }
    }

    public final void resetTable() {
        DefaultTableModel model = (DefaultTableModel) tbTransaksi.getModel();
        model.setRowCount(0);
        try {
            selectTransaksi();
        } catch (ParseException ex) {

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btMobil = new javax.swing.JButton();
        btPelanggan = new javax.swing.JButton();
        btTambah = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btLaporan = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        tvJumlahSewa = new javax.swing.JLabel();
        tvPendapatan = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTransaksi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btMobil.setText("Mobil");
        btMobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMobilActionPerformed(evt);
            }
        });

        btPelanggan.setText("Pelanggan");
        btPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPelangganActionPerformed(evt);
            }
        });

        btTambah.setText("Tambah Transaksi");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Rental Mobil Berkat Doa Kuitan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        btLaporan.setText("Laporan");
        btLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLaporanMouseClicked(evt);
            }
        });
        btLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLaporanActionPerformed(evt);
            }
        });

        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });

        tvJumlahSewa.setBackground(new java.awt.Color(255, 255, 255));
        tvJumlahSewa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tvJumlahSewa.setText("Mobil yang disewa : 0");

        tvPendapatan.setBackground(new java.awt.Color(255, 255, 255));
        tvPendapatan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tvPendapatan.setText("Pendapatan : 0");

        jButton1.setText("jButton1");

        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Mobil", "Nopol", "Lama", "Total", "Tgl Sewa", "Est Kembali", "Ktp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTransaksi.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbTransaksi);
        if (tbTransaksi.getColumnModel().getColumnCount() > 0) {
            tbTransaksi.getColumnModel().getColumn(0).setMaxWidth(50);
            tbTransaksi.getColumnModel().getColumn(8).setMinWidth(0);
            tbTransaksi.getColumnModel().getColumn(8).setPreferredWidth(0);
            tbTransaksi.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btMobil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPelanggan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8)
                        .addComponent(tvJumlahSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tvPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btLaporan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tvJumlahSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tvPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String string;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public String getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "TransaksiFrame{" + "gTransaksiCount=" + gTransaksiCount + ", gTotalSewa=" + gTotalSewa + ", btHapus=" + btHapus + ", btLaporan=" + btLaporan + ", btMobil=" + btMobil + ", btPelanggan=" + btPelanggan + ", btTambah=" + btTambah + ", jButton1=" + jButton1 + ", jLabel1=" + jLabel1 + ", jPanel1=" + jPanel1 + ", jScrollPane1=" + jScrollPane1 + ", jScrollPane2=" + jScrollPane2 + ", tbTransaksi=" + tbTransaksi + ", tvJumlahSewa=" + tvJumlahSewa + ", tvPendapatan=" + tvPendapatan + '}';
    }

    private void btMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMobilActionPerformed
        MobilFrame lamanMobil = new MobilFrame();
        lamanMobil.setVisible(true);
    }//GEN-LAST:event_btMobilActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        TambahTransaksiFrame lamanTambahTransaksi = new TambahTransaksiFrame();
        lamanTambahTransaksi.setVisible(true);
    }//GEN-LAST:event_btTambahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        resetTable();
    }//GEN-LAST:event_formWindowActivated

    private void btPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPelangganActionPerformed
        PelangganFrame lamanPelanggan = new PelangganFrame();
        lamanPelanggan.setVisible(true);
    }//GEN-LAST:event_btPelangganActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
       int i = tbTransaksi.getSelectedRow();
        int pilihan = JOptionPane.showConfirmDialog(
                null,
                "Apakah Anda Ingin Menghapus ?",
                "Konfirmasi hapus",
                JOptionPane.YES_NO_OPTION
        );
        if (pilihan == 0) {
            if (i >= 0) {
                try {
                    TableModel model = tbTransaksi.getModel();
                    Koneksi koneksi = new Koneksi();
                    Connection conn = koneksi.getConnection();

                    String executeQuery = "DELETE FROM sewa WHERE id = ?";
                    PreparedStatement ps = conn.prepareStatement(executeQuery);
                    ps.setString(1, model.getValueAt(i, 0).toString());
                    ps.executeUpdate();
                } catch (SQLException err) {
                    System.err.println(err);
                }
            } else {
                JOptionPane.showMessageDialog(
                        null, "Silahkan Pilih Data Yang ingin dihapus"
                );
            }
        }
        resetTable();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLaporanActionPerformed
      
    }//GEN-LAST:event_btLaporanActionPerformed

    private void btLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLaporanMouseClicked
    try {
            File file = new File("src/report/report1.jrxml");
            Koneksi koneksi = new Koneksi();
            Connection conn = koneksi.getConnection();
            jasperDesign = JRXmlLoader.load(file);
            param.clear();
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, param,    koneksi.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
         {
           
          
        } 
    }//GEN-LAST:event_btLaporanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        new TransaksiFrame().setVisible(true);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btLaporan;
    private javax.swing.JButton btMobil;
    private javax.swing.JButton btPelanggan;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbTransaksi;
    private javax.swing.JLabel tvJumlahSewa;
    private javax.swing.JLabel tvPendapatan;
    // End of variables declaration//GEN-END:variables
}

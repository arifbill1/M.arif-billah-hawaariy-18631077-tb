/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author arif billah
 */
public class Koneksi {
    
        // just for simple app univ, no need to write credential in env
        private final String URL = "jdbc:mysql://localhost:3306/rental_mobil_18631077";
        private final String USER = "root";
        private final String PASS = ""; 
        
        
         private Connection conn;
         private Statement stmt;
         private ResultSet rs;

        
        public Connection getConnection(){
            Connection con;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection( URL, USER, PASS);
            } catch(ClassNotFoundException | SQLException ex) {
                System.out.println("Koneksi Ke Database Gagal " + ex.getMessage());
                con=null;
            }
            return con;
        }
        
        public static void main(String[] args){
            Koneksi koneksi = new Koneksi();
            koneksi.getConnection();
        }
}

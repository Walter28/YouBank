/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */

import java.sql.*;


public class BaseSQL {
    
    Connection con;
    
    public Connection getConnection() {
        try {
            //Ouverture de la connexion
            //Deprecated
            //Class.forName("com.mysql.jdbc.Driver");
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost/youbankDB",    "root","");
            return con;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
    
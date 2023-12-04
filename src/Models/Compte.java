/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.BaseSQL;
import Models.LoginModel;
import java.sql.*;

/**
 *
 * @author HP
 */
public class Compte {
    
    int numero, solde;
    String proprio, type;

    public Compte(int numero, int solde, String proprio, String type) {
        this.numero = numero;
        this.solde = solde;
        this.proprio = proprio;
        this.type = type;
    }
    
    public void enregistrement(){
        BaseSQL baseSQL = new BaseSQL();
    
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement st;
        
        String proprio = this.proprio;
        int numero = this.numero;
        int solde = 0;
        String type = this.type;
        
        String query = "INSERT INTO compte(numero, proprio, solde, type)" + "VALUES('"+numero+"','"+proprio+"','"+solde+"','"+type+"' ) ";
        
        
        try{
//            
            con = baseSQL.getConnection();
            st = con.createStatement();
            
            st.execute(query);
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void depot(int montant, int numero){
        
        BaseSQL baseSQL = new BaseSQL();
    
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement st;
        int solde;
        int newSolde;
        
        String query = "SELECT solde FROM compte WHERE numero='"+numero+"' ";
        
        try{
//            
            con = baseSQL.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }
    
    public void retirer(int montant, int numero){
        
        BaseSQL baseSQL = new BaseSQL();
    
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement st;
        
        String query = "SELECT solde FROM compte WHERE numero='"+numero+"' ";
        
    }
    
    public void verifier(int numero){
        
        BaseSQL baseSQL = new BaseSQL();
    
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement st;
        
        String query = "SELECT solde FROM compte WHERE numero='"+numero+"' ";
        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getProprio() {
        return proprio;
    }

    public void setProprio(String proprio) {
        this.proprio = proprio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}

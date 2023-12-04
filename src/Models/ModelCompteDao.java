/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.BaseSQL;
import Models.LoginModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ModelCompteDao {

    BaseSQL baseSQL = new BaseSQL();

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;

    int solde = 0;

    public boolean Enregistrer(ModelCompte mc) {
        String query = "INSERT INTO compte (numero, proprio, solde, type) VALUE "
                + "(?,?,?,?) ";
        
        

        try {            
            con = baseSQL.getConnection();
            pst = con.prepareStatement(query);
            
            pst.setInt(1, mc.getNumero());
            pst.setString(2, mc.getProprio());
            pst.setInt(3, solde);
            pst.setString(4, mc.getType());
            pst.executeUpdate();
            
            System.out.print(pst);
            
            JOptionPane.showMessageDialog(null, "Compte cree avec succee");
            
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            
            JOptionPane.showMessageDialog(new JFrame(), "Le compte n'as pas ete cree\n Code erreur : "+e.getMessage(), "Error", 
                JOptionPane.ERROR_MESSAGE);
            
            return false;
        } finally {
            try{
                con.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public List Liste(){
        
        List<ModelCompte> list = new ArrayList<>();
        
        String query = "SELECT * FROM compte";
        
        try{
            
            con = baseSQL.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                ModelCompte mc = new ModelCompte();
                
                mc.setNumero(rs.getInt("numero"));
                mc.setProprio(rs.getString("proprio"));
                mc.setSolde(rs.getInt("solde"));
                mc.setType(rs.getString("type"));
                
                list.add(mc);
            }
            
        } catch(Exception e){
            
        }
        
        return list;
        
    }
    
    public static void VerifierSolde(int numero){
        
        BaseSQL baseSQL = new BaseSQL();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement st;

        
        String query = "SELECT * FROM compte WHERE numero ='"+numero+"' ";
        
        int solde_actuel = 0;
        String proprio=null;
        boolean done = false;
        
        try{
            
            con = baseSQL.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            System.out.print(rs);
            
            while(rs.next()){
                solde_actuel = rs.getInt("solde");
                proprio = rs.getString("proprio");
                done = true;
            }
            
            if (done){
                JOptionPane.showMessageDialog(new JFrame(), "Client : "+proprio+"\n SOLDE ACTUEL: "+solde_actuel+" Fc", "VERFICATION", 
                JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Client n'existe pas", "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            }
            
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", 
                JOptionPane.ERROR_MESSAGE);
        }      
            
        
    }
    
    public static void depot(int montant, int numero){
        
        BaseSQL baseSQL = new BaseSQL();
    
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement st;
        boolean done = false;
        int solde_actuel=0;
        int newSolde;
        String proprio=null;
        
        String query = "SELECT * FROM compte WHERE numero='"+numero+"' ";
        
        try{
//            
            con = baseSQL.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                solde_actuel = rs.getInt("solde");
                proprio =  rs.getString("proprio");
                done = true;
            }
            
            if(done){
                if(montant <= 100){
                    JOptionPane.showMessageDialog(new JFrame(), "Impossible de faire le depot de cette somme\nDepot minimum doit etre superieur"
                            + "a 100Fc", "ERROR", 
                    JOptionPane.ERROR_MESSAGE);
                } else{
                    newSolde = solde_actuel + montant;
                    
                    String queryUpdate = " UPDATE compte SET solde='"+newSolde+"' WHERE numero='"+numero+"' ";
                    
                    
                    
                    try{
                        st.executeUpdate(queryUpdate);
                        
                        JOptionPane.showMessageDialog(new JFrame(), "Depot Effectuer avec succees"
                                + "\n Client : "+proprio+"\n Nouveau solde : "+newSolde, "SUCCESS", 
                        JOptionPane.INFORMATION_MESSAGE);
                        
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(new JFrame(), "Impossible de faire le depot", "ERROR", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                }
            }else {
                JOptionPane.showMessageDialog(new JFrame(), "Client n'existe pas", "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            }
            
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", 
                JOptionPane.ERROR_MESSAGE);
        } 
        
        
        
    }
    
    
    public static void retrait(int montant, int numero){
        
        BaseSQL baseSQL = new BaseSQL();
    
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement st;
        boolean done = false;
        int solde_actuel=0;
        int newSolde;
        String proprio=null;
        
        String query = "SELECT * FROM compte WHERE numero='"+numero+"' ";
        
        try{
//            
            con = baseSQL.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                solde_actuel = rs.getInt("solde");
                proprio =  rs.getString("proprio");
                done = true;
            }
            
            if(done){
                if(solde_actuel - montant < 0){
                    JOptionPane.showMessageDialog(new JFrame(), "Impossible de faire le retrait, Veiller crediter votre compte", "ERROR", 
                    JOptionPane.ERROR_MESSAGE);
                } else{
                    newSolde = solde_actuel - montant;
                    
                    String queryUpdate = " UPDATE compte SET solde='"+newSolde+"' WHERE numero='"+numero+"' ";
                    
                    
                    
                    try{
                        st.executeUpdate(queryUpdate);
                        
                        JOptionPane.showMessageDialog(new JFrame(), "Retrait Effectuer avec succees"
                                + "\n Client : "+proprio+"\n Nouveau solde : "+newSolde, "SUCCESS", 
                        JOptionPane.INFORMATION_MESSAGE);
                        
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(new JFrame(), "Impossible de faire le retrait", "ERROR", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                }
            }else {
                JOptionPane.showMessageDialog(new JFrame(), "Client n'existe pas", "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            }
            
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", 
                JOptionPane.ERROR_MESSAGE);
        } 
        
        
        
    }

}

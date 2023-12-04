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
public class LoginModelDao {
    
    BaseSQL baseSQL = new BaseSQL();
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void getAdmin(){
        
        String sql = "SELECT * FROM admin";
        
        try{
            
            con = baseSQL.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
//            while(rs.next()){
                LoginModel lm = new LoginModel();
                lm.setId(rs.getString("id"));
                lm.setUsername(rs.getString("username"));
                lm.setPassword(rs.getString("password"));
//            }
            
        } catch(Exception e){
            
        }
    }
    
}

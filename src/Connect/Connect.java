/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThaiNguyen
 */
public class Connect {
    private Connection conDB=null;
    private Statement stmDB=null;
    private ResultSet rsDB=null;
    

//    private final String servername= "closer2.heliohost.org:3306";//
//    private final String databasename="closer2_quanlicuahangson";//
//    private final String username="closer2";//
//    private final String password="Bo4quyennang";//

    private final String servername= "localhost";//
    private final String databasename="closer_quanlicuahangson";//
    private final String username="root";//
    private final String password="";//
    
    private String url="";

    public Connection getConDB() {
        return conDB;
    }
    
    
    public boolean Connected(){
        try{
            if((conDB!=null)&&(!conDB.isClosed())){
                CloseDB();
            }
            url="jdbc:mysql://"+servername+"/"+databasename+"?characterEncoding=utf8";
            conDB=DriverManager.getConnection(url,username,password);
        }
        catch(SQLException ex){
            System.out.println("Ngoại lệ tại Connect.Connected :"+ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean CloseDB(){
        if (conDB != null) {
            try {
                conDB.close();
                return true;
            } catch (SQLException ex) {
                System.out.println("Ngoại lệ tại Connect.Closed :" + ex.getMessage());
                return false;
            }
        }
        return false;
    }
    
    public ResultSet GetData(String SQL){
        ResultSet rs = null;
        try{
            if(Connected()){
                stmDB =(Statement) conDB.createStatement();
                rs=stmDB.executeQuery(SQL);
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại Connect.GetData: "+ex.getMessage());
        }
        return rs;
    }
    
//     public static void main(String args[]) { 
//         Connect cn = new Connect();
//         if(cn.Connected())
//             System.out.println("Connected");
//     }

}

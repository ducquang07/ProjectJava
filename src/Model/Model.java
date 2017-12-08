/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;
import Connect.Connect;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ThaiNguyen
 */
public abstract class Model {
    protected Connect DB;
    protected String SQL;
    protected String Table;
    protected String ID;
    protected Statement  stmDB;
    
    
    //Constructor

    public Model() {
    }

    public Model(Connect DB, String Table, String ID) {
        this.DB = DB;
        this.Table = Table;
        this.ID = ID;
    }
    
    //Get List 
    public ResultSet GetALL(){
        SQL="Select * from "+Table;
        ResultSet rs=DB.GetData(SQL);
        return rs;
    }
    
    //Get by Object's ID
    public ResultSet GetByID(String id){
        SQL="Select * from "+Table+" where "+ID+"='"+id+"'";
        ResultSet rs = DB.GetData(SQL);
        return rs;
    }
    
    
    //Search by Object's ID
    public ResultSet SearchByID(String id){
        SQL="Select * from "+Table+" where "+ID+" like '%"+id+"%'";
        ResultSet rs = DB.GetData(SQL);
        return rs;
    }
    
    public abstract boolean Insert();
    
    public abstract boolean Update();
    
    public boolean Delete(String id){
        SQL="Delete from "+Table+" where "+ID+" ='"+id+"'";
        if(DB.Connected()){
            try {
                Statement  stmDB =(Statement)  DB.getConDB().createStatement();
                stmDB.executeUpdate(SQL);
            } catch (SQLException ex) {
                System.out.println("Ngoại lệ tại Model.Delete: "+ex.getMessage());
                return false;
            }
            finally{
                DB.CloseDB();
            }
        }
        return true;
    }
}

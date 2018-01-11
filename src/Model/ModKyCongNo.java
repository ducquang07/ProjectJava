/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjKyCongNo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ThaiNguyen
 */
public class ModKyCongNo extends Model{
    private PreparedStatement pstmt;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
     public ModKyCongNo() {
        DB=new Connect();
        Table="KYCONGNO";
        ID="MaKCN";
    }
     
    public ResultSet GetALL(){
        return super.GetALL();
    }
     
    public boolean Insert(ObjKyCongNo TbKyCongNo) {
        String SQL="INSERT INTO KyCongNo (MaKCN,TuNgay,DenNgay) VALUES (?, ?, ?);";   
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbKyCongNo.getMaKCN());
                pstmt.setString(2,dt.format(TbKyCongNo.getTuNgay()));
                pstmt.setString(3,dt.format(TbKyCongNo.getDenNgay()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModKyCongNo.Insert:"+ex.getMessage());
            return false;
        }       
        return true;
    }
    
    public boolean Delete(String MaKCN){
        return super.Delete(MaKCN);
    }
}

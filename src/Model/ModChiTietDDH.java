/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjChiTietDDH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Coldblood
 */
public class ModChiTietDDH extends Model {
    private PreparedStatement pstmt;
    
    public ModChiTietDDH()
    {
        DB = new Connect();
        Table = "CTDDH";
        ID = "MaDDH";
    }
    public ResultSet GetALL(){
        return super.GetALL();
    }
    
    public ResultSet GetByID(String ID){
        return super.GetByID(ID);
    }
    
    public ResultSet SearchByID(String ID){
        return super.SearchByID(ID);
    }
    
     public boolean Insert(ObjChiTietDDH TbCTDDH) {
        String SQL="INSERT INTO CTDDH (MaDDh,MaSP,SoLuong) VALUES (?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbCTDDH.getMaDDH());
                pstmt.setString(2,TbCTDDH.getMaSP());
                pstmt.setString(3,Integer.toString(TbCTDDH.getSoLuong()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietDDH.Insert:"+ex.getMessage());
            return false;
        }
        return true;
    }
     public boolean Update(ObjChiTietDDH TbCTDDH) {
        String SQL="Update CTHDL set MaSP='"+TbCTDDH.getMaSP()+
                                              "',SoLuong='"+TbCTDDH.getSoLuong()+
                                              "' where MaDDH='"+TbCTDDH.getMaDDH()+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietDDH.Update:"+ex.getMessage());
            return false;
        }
        return true;
    }
     public boolean Delete(String MaDDH){
        return super.Delete(MaDDH);
    }
    
}

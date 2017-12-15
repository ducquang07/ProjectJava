/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjChiTietHDS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class ModChiTietHDS extends Model{
    
    private PreparedStatement pstmt;
    
    public ModChiTietHDS() {
        DB=new Connect();
        Table="CTHDS";
        ID="SoHDS";
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

    public boolean Insert(ObjChiTietHDS TbCTHDS) {
        String SQL="INSERT INTO CTHDS (SoHDS,MaSP,SoLuong,DonGia,ThanhTien) VALUES (?, ?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbCTHDS.getSoHDS());
                pstmt.setString(2,TbCTHDS.getMaSP());
                pstmt.setString(3,Integer.toString(TbCTHDS.getSoLuong()));
                pstmt.setString(4,Integer.toString(TbCTHDS.getDonGia()));
                pstmt.setString(5,Integer.toString(TbCTHDS.getThanhTien()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietHDS.Insert:"+ex.getMessage());
            return false;
        }
        return true;
    }


    public boolean Update(ObjChiTietHDS TbCTHDS) {
        String SQL="Update CTHDS set SoLuong='"+TbCTHDS.getSoLuong()+
                                              "',DonGia='"+TbCTHDS.getDonGia()+
                                              "',ThanhTien='"+TbCTHDS.getThanhTien()+
                                              "' where SoHDS='"+TbCTHDS.getSoHDS()+"' and MaSP='"+TbCTHDS.getMaSP()+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietHDS.Update:"+ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean Delete(String SoHDS){
        return super.Delete(SoHDS);
    }
}

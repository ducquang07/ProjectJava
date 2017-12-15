/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjChiTietHDL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class ModChiTietHDL extends Model{
    private PreparedStatement pstmt;
    
    public ModChiTietHDL() {
        DB=new Connect();
        Table="CTHDL";
        ID="SoHDL";
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

    public boolean Insert(ObjChiTietHDL TbCTHDL) {
        String SQL="INSERT INTO CTHDL (SoHDL,MaSP,SoLuong,DonGia,ThanhTien) VALUES (?, ?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbCTHDL.getSoHDL());
                pstmt.setString(2,TbCTHDL.getMaSP());
                pstmt.setString(3,Integer.toString(TbCTHDL.getSoLuong()));
                pstmt.setString(4,Integer.toString(TbCTHDL.getDonGia()));
                pstmt.setString(5,Integer.toString(TbCTHDL.getThanhTien()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietHDL.Insert:"+ex.getMessage());
            return false;
        }
        return true;
    }


    public boolean Update(ObjChiTietHDL TbCTHDL) {
        String SQL="Update CTHDL set SoLuong='"+TbCTHDL.getSoLuong()+
                                              "',DonGia='"+TbCTHDL.getDonGia()+
                                              "',ThanhTien='"+TbCTHDL.getThanhTien()+
                                              "' where SoHDL='"+TbCTHDL.getSoHDL()+"' and MaSP='"+TbCTHDL.getMaSP()+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietHDL.Update:"+ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean Delete(String SoHDL){
        return super.Delete(SoHDL);
    }
}

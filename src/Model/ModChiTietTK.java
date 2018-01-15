/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjCTKTK;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class ModChiTietTK extends Model{
    private PreparedStatement pstmt;
    
     public ModChiTietTK() {
        DB=new Connect();
        Table="CTKTK";
        ID="MaKTK";
    }
     
    public boolean Insert(ObjCTKTK TbCTTK) {
        String SQL="INSERT INTO CTKTK (MaKTK,MaSP,SoLuongTonDauKi,SoLuongNhap,SoLuongXuat,SoLuongTonCuoiKi) VALUES (?, ?, ?, ?, ?, ?);";   
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbCTTK.getMaKTK());
                pstmt.setString(2,TbCTTK.getMaSP());
                pstmt.setInt(3,TbCTTK.getSoLuongTonDauKi());
                pstmt.setInt(4,TbCTTK.getSoLuongNhap());
                pstmt.setInt(5,TbCTTK.getSoLuongXuat());
                pstmt.setInt(6,TbCTTK.getSoLuongTonCuoiKi());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietTK.Insert:"+ex.getMessage());
            return false;
        }       
        return true;
    }
    
    public boolean Delete(String MaKTK){
        return super.Delete(MaKTK);
    }
}

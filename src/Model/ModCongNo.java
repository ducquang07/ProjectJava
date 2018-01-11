/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjCongNo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class ModCongNo extends Model{
     private PreparedStatement pstmt;
    
     public ModCongNo() {
        DB=new Connect();
        Table="CONGNO";
        ID="MaKCN";
    }
     
    public boolean Insert(ObjCongNo TbCongNo) {
        String SQL="INSERT INTO CongNo (MaKCN,MaKH,SoDuNoDauKi,SoNoPhatSinhTrongKi,SoTienThuTrongKi,SoDuNoCuoiKi) VALUES (?, ?, ?, ?, ?, ?);";   
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbCongNo.getMaKCN());
                pstmt.setString(2,TbCongNo.getMaKH());
                pstmt.setInt(3,TbCongNo.getSoDuNoCuoiKi());
                pstmt.setInt(4,TbCongNo.getSoNoPhatSinhTrongKi());
                pstmt.setInt(5,TbCongNo.getSoTienThuTrongKi());
                pstmt.setInt(6,TbCongNo.getSoDuNoCuoiKi());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModCongNo.Insert:"+ex.getMessage());
            return false;
        }       
        return true;
    }
    
    public boolean Delete(String MaKCN){
        return super.Delete(MaKCN);
    }
}

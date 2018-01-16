/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjThamSo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class ModThietLapMucTonKho extends Model{
    private PreparedStatement pstmt;
    public ModThietLapMucTonKho(){
        DB=new Connect();
        Table="KHACHHANG";
        ID="MaKH";
    }
    
     public boolean Update(int SoLuongMoi,int SoLuongCu) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String SQL="Update ThamSo set SoLuongToiThieu='"+ SoLuongMoi+"' where SoLuongToiThieu='"+SoLuongCu+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate(SQL);
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModThietLapMucTonKho.Update: "+ex.getMessage());
            return false;
        }
        finally{
            DB.CloseDB();
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjChiTietPNH;
import Object.ObjPhieuNhapHang;
import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author DucQuang
 */
public class ModPhieuNhapHang extends Model{
    
    private PreparedStatement pstmt;
    
    public ModPhieuNhapHang() {
        DB=new Connect();
        Table="PHIEUNHAP";
        ID="MaPN";
        Name="";
    }
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    public boolean Insert(ObjPhieuNhapHang TbPhieuNhapHang) {
         String mySQL="INSERT INTO PHIEUNHAP (MaPN,NgayNhap,MaDDH) VALUES (?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(mySQL);
                pstmt.setString(1,TbPhieuNhapHang.getMaPN());
                pstmt.setString(2, df.format(TbPhieuNhapHang.getNgayNhap()));
                pstmt.setString(3,TbPhieuNhapHang.getMaDDH());
                pstmt.executeUpdate();
                DB.CloseDB();
            }
            
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModPhieuNhapHang.Insert: "+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }
        return true;        
    }

    public boolean Update(ObjPhieuNhapHang TbPhieuNhapHang) {
         String mySQL="Update PHIEUNHAP set NgayNhap='" + TbPhieuNhapHang.getNgayNhap()+ 
       "', MaDDH='" + TbPhieuNhapHang.getMaDDH() + "' where MaPN='"+ TbPhieuNhapHang.getMaPN()+"';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(mySQL);
               DB.CloseDB();
           }
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModPhieuNhapHang.Update: "+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }
        return true;
       
    }
    public boolean Delete(ObjPhieuNhapHang TbPhieuNhapHang) {
         SQL="Delete from CTPN where MaPN ='"+TbPhieuNhapHang.getMaPN()+"'";
            try {
                if(DB.Connected()){
                Statement  stmDB =(Statement)  DB.getConDB().createStatement();
                stmDB.executeUpdate(SQL);
                }
            } catch (SQLException ex) {
                System.out.println("Ngoại lệ tại ModCTPN.Delete: "+ex.getMessage());
                DB.CloseDB();
                return false;
            }finally{
                DB.CloseDB();
            }
         return super.Delete(TbPhieuNhapHang.getMaPN());
    }
    
    public boolean Delete(ObjChiTietPNH TbCTPN) {
         return super.Delete(TbCTPN.getMaPN());
    }
    
}

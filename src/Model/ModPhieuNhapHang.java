/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjPhieuNhapHang;
import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author DucQuang
 */
public class ModPhieuNhapHang extends Model{
    
    private PreparedStatement pstmt;
    
    public ModPhieuNhapHang() {
        DB=new Connect();
        Table="PHIEUNHAPHANG";
        ID="MaPN";
        Name="";
    }
    public boolean Insert(ObjPhieuNhapHang TbPhieuNhapHang) {
         String mySQL="INSERT INTO PHIEUNHAPHANG (MaPN,NgayNhap,MaDDH) VALUES (?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(mySQL);
                pstmt.setString(1,TbPhieuNhapHang.getMaPN());
                pstmt.setDate(2, (Date) TbPhieuNhapHang.getNgayNhap());
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
         String mySQL="Update PHIEUNHAPHANG set NgayNhap='" + TbPhieuNhapHang.getNgayNhap()+ 
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
         return super.Delete(TbPhieuNhapHang.getMaPN());
    }
    
}

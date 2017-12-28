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

/**
 *
 * @author DucQuang
 */
public class ModChiTietPNH extends Model{
    private PreparedStatement pstmt;
    
    public ModChiTietPNH() {
        DB=new Connect();
        Table="CTPN";
        ID="MaPN";
        Name="";
    }
    public boolean Insert(ObjChiTietPNH TbChiTietPNH) {
         String mySQL="INSERT INTO CTPN (MaPN,MaSP,SoLuong, DonGia) VALUES (?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(mySQL);
                pstmt.setString(1,TbChiTietPNH.getMaPN());
                pstmt.setString(2,TbChiTietPNH.getMaSP());
                pstmt.setInt(3, TbChiTietPNH.getSoLuong());
                pstmt.setInt(4,TbChiTietPNH.getDonGia());
                pstmt.executeUpdate();
                DB.CloseDB();
            }
            
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChitietPNH.Insert: "+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }
        return true;        
    }

    public boolean Update(ObjChiTietPNH TbChiTietPNH) {
         String mySQL="Update CTPN set SoLuong='" + TbChiTietPNH.getSoLuong()+ "', DonGia='" + TbChiTietPNH.getDonGia() + "' where MaPN='"+ TbChiTietPNH.getMaPN()+"' and MaSP='" + TbChiTietPNH.getMaSP()+ "';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(mySQL);
               DB.CloseDB();
           }
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModChiTietPNH.Update: "+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }
        return true;
       
    }
    public boolean Delete(String MaPN) {
         SQL="Delete from CTPN where MaPN ='"+MaPN+"'";
            try {
                if(DB.Connected()){
                Statement  stmDB =(Statement)  DB.getConDB().createStatement();
                stmDB.executeUpdate(SQL);
                }
            } catch (SQLException ex) {
                System.out.println("Ngoại lệ tại Model.Delete: "+ex.getMessage());
                DB.CloseDB();
                return false;
            }finally{
                DB.CloseDB();
            }
            return true;
    }    
}

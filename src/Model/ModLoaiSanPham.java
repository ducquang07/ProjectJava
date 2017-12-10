/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjLoaiSanPham;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class ModLoaiSanPham extends Model{
    private ObjLoaiSanPham TbLoaiSanPham;
    private PreparedStatement pstmt;
    
    public ModLoaiSanPham() {
        DB=new Connect();
        Table="LOAISANPHAM";
        ID="MaLoaiSP";
        Name="TenLoaiSP";
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
    
    public ResultSet GetNameByID(String ID){
        return super.GetNameByID(ID);
    }

    @Override
    public boolean Insert() {
         String SQL="INSERT INTO LOAISANPHAM (MaLoaiSP,TenLoaiSP) VALUES (?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbLoaiSanPham.getMaLoaiSP());
                pstmt.setString(2,TbLoaiSanPham.getTenLoaiSP());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModLoaiSanPham.Insert: "+ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean Update() {
         String SQL="Update LOAISANPHAM set TenLoaiSP=N'" + TbLoaiSanPham.getTenLoaiSP()+ 
       "' where MaLoaiSP='"+ TbLoaiSanPham.getMaLoaiSP()+"';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(SQL);
           }
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModLoaiSamPham.Update: "+ex.getMessage());
        }
        return false;
    }
}

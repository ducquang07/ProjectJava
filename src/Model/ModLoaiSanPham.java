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
    //private ObjLoaiSanPham TbLoaiSanPham;
    private PreparedStatement pstmt;
    
    public ModLoaiSanPham() {
        DB=new Connect();
        Table="LOAISANPHAM";
        ID="MaLoaiSP";
        Name="TenLoaiSP";
    }

    @Override
    public ResultSet GetALL(){
        return super.GetALL();
    }
    
    @Override
    public ResultSet GetByID(String ID){
        return super.GetByID(ID);
    }
    
    @Override
    public ResultSet SearchByID(String ID){
        return super.SearchByID(ID);
    }
    
    @Override
    public ResultSet GetNameByID(String ID){
        return super.GetNameByID(ID);
    }

    public boolean Insert(ObjLoaiSanPham TbLoaiSanPham) {
         String mySQL="INSERT INTO LOAISANPHAM (MaLoaiSP,TenLoaiSP) VALUES (?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(mySQL);
                pstmt.setString(1,TbLoaiSanPham.getMaLoaiSP());
                pstmt.setString(2,TbLoaiSanPham.getTenLoaiSP());
                pstmt.executeUpdate();
                DB.CloseDB();
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModLoaiSanPham.Insert: "+ex.getMessage());
            DB.CloseDB();
            return false;
        }        
    }

    public boolean Update(ObjLoaiSanPham TbLoaiSanPham) {
         String mySQL="Update LOAISANPHAM set TenLoaiSP=N'" + TbLoaiSanPham.getTenLoaiSP()+ 
       "' where MaLoaiSP='"+ TbLoaiSanPham.getMaLoaiSP()+"';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(mySQL);
               DB.CloseDB();
           }
           return true;
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModLoaiSamPham.Update: "+ex.getMessage());
            DB.CloseDB();
            return false;
        }
       
    }
    public boolean Delete(ObjLoaiSanPham TbLoaiSanPham) {
         return super.Delete(TbLoaiSanPham.getMaLoaiSP());
    }
}

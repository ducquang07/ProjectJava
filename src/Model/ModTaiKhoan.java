/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjTaiKhoan;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author ThaiNguyen
 */
public class ModTaiKhoan extends Model{
     private PreparedStatement pstmt;
    
    public ModTaiKhoan() {
        DB=new Connect();
        Table="TAIKHOAN";
        ID="TenDangNhap";
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
    
    
    
    public boolean Insert(ObjTaiKhoan TbTaiKhoan) {
        String SQL="INSERT INTO TAIKHOAN (TenDangNhap,MatKhau,Ten,PhanLoai) VALUES (?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbTaiKhoan.getTenDangNhap());
                pstmt.setString(2,TbTaiKhoan.getMatKhau());
                pstmt.setString(3,TbTaiKhoan.getTen());
                pstmt.setString(4,TbTaiKhoan.getPhanLoai());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModTaiKhoan.Insert: "+ex.getMessage());
            return false;
        }
        finally{
            DB.CloseDB();
        }
        return true;
    }

    
    public boolean Update(ObjTaiKhoan TbTaiKhoan) {
        String SQL="Update TAIKHOAN set " + 
                                    "MatKhau='" + TbTaiKhoan.getMatKhau() +
       "' where TenDangNhap='"+ TbTaiKhoan.getTenDangNhap()+"';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(SQL);
           }
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModTaiKhoan.Update: "+ex.getMessage());
            return false;
       }
       finally{
           DB.CloseDB();
       }
        return true;
    }
    
    public boolean UpdatePhanQuyen(ObjTaiKhoan TbTaiKhoan) {
       String SQL="Update TAIKHOAN set PhanLoai='" + TbTaiKhoan.getPhanLoai() + "' where TenDangNhap='"+ TbTaiKhoan.getTenDangNhap()+"';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(SQL);
           }
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModTaiKhoan.Update: "+ex.getMessage());
            return false;
       }
       finally{
           DB.CloseDB();
       }
       return true;
    }
    
    
}

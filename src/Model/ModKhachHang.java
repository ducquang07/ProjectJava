/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjKhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Thanh
=======

/**
 *
 * @author ThaiNguyen

 */
public class ModKhachHang extends Model{
    private ObjKhachHang TbKhachHang;
    private PreparedStatement pstmt;

    public ModKhachHang(){
        DB=new Connect();
        Table="KHACHHANG";
        ID="MaKH";
        
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
    
    public boolean Insert(ObjKhachHang TbKhachHang) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String SQL="INSERT INTO KHACHHANG (MaKH, TenKH, SDT,DiaChi,Email,LoaiKH) VALUES (?, ?, ?, ?, ?, ?);";
        try{
            if(DB.Connected())
            {
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1, TbKhachHang.getMaKH());
                pstmt.setString(2, TbKhachHang.getTenKH());
                pstmt.setString(3, TbKhachHang.getSDT());
                pstmt.setString(4, TbKhachHang.getDiaChi());
                pstmt.setString(5, TbKhachHang.getEmail());
                pstmt.setString(6, TbKhachHang.getLoaiKH());
                pstmt.executeUpdate();
                DB.CloseDB();
                
            }
        } catch(SQLException ex){
            System.out.println("Ngoại lệ tại ModKhachHang.Insert: "+ex.getMessage());
            return false;
        }
        finally{
            DB.CloseDB();
        }
        return true;
    }

    public boolean Update(ObjKhachHang TbKhachHang) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String SQL="Update KHACHHANG set MaKH='"+ TbKhachHang.getMaKH()+
                                        "',TenKH=N'" + TbKhachHang.getTenKH()+
                                        "',SDT='" + TbKhachHang.getSDT()+
                                        "',DiaChi=N'" + TbKhachHang.getDiaChi()+
                                        "',Email='"+ TbKhachHang.getEmail()+
                                        "',LoaiKH=N'" + TbKhachHang.getLoaiKH()+
                                        "' where MaKH='" +TbKhachHang.getMaKH()+"';";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate(SQL);
                
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModKhachHang.Update: "+ex.getMessage());
            return false;
        }
        finally{
            DB.CloseDB();
        }
        return false;
    }

    public boolean Delete(String ObjKH) {
                 return super.Delete(ObjKH);

    }

    
}

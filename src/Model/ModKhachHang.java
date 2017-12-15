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
    
    @Override
    public boolean Insert() {
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

    @Override
    public boolean Update() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String SQL="Update KHACHHANG set TenKH=N'" + TbKhachHang.getTenKH()+
                                        "',SDT='" + TbKhachHang.getSDT()+
                                        "',DiaChi=N'" + TbKhachHang.getDiaChi()+
                                        "',Email='"+ TbKhachHang.getEmail()+
                                        "',LoaiKH=N'" + TbKhachHang.getLoaiKH()+
                                        "' where MaKH='" +TbKhachHang.getMaKH()+"';";
        try{
            if(DB.Connected()){
                stmDB=(com.mysql.jdbc.Statement) (Statement) DB.getConDB().createStatement();
                stmDB.executeUpdate(SQL);
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModKhachHang.Update: "+ex.getMessage());
        }
        finally{
            DB.CloseDB();
        }
        return false;
    }
    
    
}

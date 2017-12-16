/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjNhaCungCap;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class ModNhaCungCap extends Model{
    private ObjNhaCungCap TbNhaCungCap;
    private PreparedStatement pstmt;
    
    public ModNhaCungCap() {
        DB=new Connect();
        Table="NHACUNGCAP";
        ID="MaNCC";
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

    public boolean Insert(ObjNhaCungCap TbNhaCungCap) {
         String mySQL="INSERT INTO NHACUNGCAP (MaNCC, TenNCC, SDT, DiaChi, Email, NoCuaDaiLy) VALUES (?, ?, ?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(mySQL);
                pstmt.setString(1,TbNhaCungCap.getMaNCC());
                pstmt.setString(2,TbNhaCungCap.getTenNCC());
                pstmt.setString(3,TbNhaCungCap.getSDT());
                pstmt.setString(4,TbNhaCungCap.getDiaChi());
                pstmt.setString(5,TbNhaCungCap.getEmail());
                pstmt.setString(6, Integer.toString(TbNhaCungCap.getNoCuaDaiLy()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModNhaCungCap.Insert: "+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }
        return true;
    }

    public boolean Update(ObjNhaCungCap TbNhaCungCap) {
           String mySQL="Update NHACUNGCAP set TenNCC=N'" + TbNhaCungCap.getTenNCC()+ 
                                    "',SDT='" + TbNhaCungCap.getSDT()+
                                    "',DiaChi='" + TbNhaCungCap.getDiaChi() + 
                                    "',Email='" + TbNhaCungCap.getEmail() + 
                                    "',NoCuaDaiLy='" +TbNhaCungCap.getNoCuaDaiLy() + 
       "' where MaNCC='"+ TbNhaCungCap.getMaNCC()+"';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(mySQL);
           }
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModNhaCungCap.Update: "+ex.getMessage());
            return false;
        }finally{
           DB.CloseDB();
       }
        return true;
    }
    
    public boolean Delete(ObjNhaCungCap TbNhaCungCap){
        return super.Delete(TbNhaCungCap.getMaNCC());
    }
}

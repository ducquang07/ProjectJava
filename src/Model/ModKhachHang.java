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

/**
 *
 * @author ThaiNguyen
 */
public class ModKhachHang extends Model{
    private ObjKhachHang TbKhachHang;
    private PreparedStatement pstmt;
    
    public ModKhachHang() {
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
    
    
}

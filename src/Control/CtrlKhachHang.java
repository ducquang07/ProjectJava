/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thanh
 */
public class CtrlKhachHang {
    Connect DB=new Connect();
    public ResultSet LayDanhSachKhachHang(){
        String SQL = "Select * "+"from KHACHHANG";
        return DB.GetData(SQL);
    }
    
    public ResultSet SearchKhachHangByID(String MaKH){
        String SQL="Select * "+ "from KHACHHANG"+" and MaKH like '%"+MaKH+"%'";
        return DB.GetData(SQL);
    }
    
    public ResultSet SearchKhachHangByName(String TenKH){
        String SQL="Select *"+" from KHACHHANG"+" and TenKH like '%"+TenKH+"%'";
        return DB.GetData(SQL);
    }
    
    public ResultSet SearchKhachHangBySDT(String SDT){
        String SQL="Select *"+" from KHACHHANG"+" and SDT like '%"+SDT+"%'";
        return DB.GetData(SQL);
    }
    public ResultSet SearchKhachHangByDiaChi(String DiaChi){
        String SQL="Select *"+" from KHACHHANG"+" and DiaChi like '%"+DiaChi+"%'";
        return DB.GetData(SQL);
    }
    
    public ResultSet SearchKhachHangByEmail(String Email){
        String SQL="Select *"+" from KHACHHANG"+" and Email like '%"+Email+"%'";
        return DB.GetData(SQL);
    }
    
    public String TaoMaKH(){
        String ID="KH001";
        ResultSet rs=null;
        String SQL="Select * from KHACHHANG order by MaKH DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("MaKH");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="KH00"+STT;
                else if(STT<100) ID="KH0"+STT;
                else ID="KH"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlKhachHang.TaoMaKH: "+ex.getMessage());
        }
        return ID;
    }
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    
}

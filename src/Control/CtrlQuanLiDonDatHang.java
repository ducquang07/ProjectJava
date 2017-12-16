/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.ModNhaCungCap;
import java.util.Date;

/**
 *
 * @author Coldblood
 */
public class CtrlQuanLiDonDatHang {
    Connect DB=new Connect();
    
    public ResultSet LayDSDonDatHang(){
        String SQL="Select MaDDH, MaNCC, NgayDatHang,TrangThai from DONDATHANG";
        return DB.GetData(SQL);
    }
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    public String LayMaDDH(){
        String ID="DH001";
        ResultSet rs=null;
        String mySQL="Select * from DONDATHANG order by MaDDH DESC limit 1";
        try{
            rs=DB.GetData(mySQL);
            if(rs.next()){
                ID=rs.getString("MaDDH");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="DH00"+STT;
                else if(STT<100) ID="DH0"+STT;
                else ID="DH"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlQuanLiDonDatHang.LayMaDDH:"+ex.getMessage());
        }
        return ID;
    }
    
   
    
    public ResultSet SearchDonDHByTrangThai(String TrangT){
        String mySQL="Select * from DONDATHANG where TrangThai like '%"+TrangT+"%'";
        return DB.GetData(mySQL);
    }
     public ResultSet SearchDonDHByMaDDH(String MaDDH)
    {
        String mySQL="Select * from DONDATHANG where MaDDH like '%"+MaDDH+"%'";
        return DB.GetData(mySQL);
    }  
}


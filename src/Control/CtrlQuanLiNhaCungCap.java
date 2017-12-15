/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DucQuang
 */
public class CtrlQuanLiNhaCungCap {
    Connect DB=new Connect();
    public ResultSet LayDSNhaCungCap(){
        String SQL="Select MaNCC, TenNCC, SDT, DiaChi, Email from NHACUNGCAP";
        return DB.GetData(SQL);
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    
    public String LayMaNCC(){
        String ID="NCC001";
        ResultSet rs=null;
        String mySQL="Select * from NHACUNGCAP order by MaNCC DESC limit 1";
        try{
            rs=DB.GetData(mySQL);
            if(rs.next()){
                ID=rs.getString("MaNCC");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="NCC00"+STT;
                else if(STT<100) ID="NCC0"+STT;
                else ID="NCC"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlQuanLiNhaCungCap.LayMaNCC:"+ex.getMessage());
        }
        return ID;
    }
    
    public ResultSet SearchNCCByID(String MaNCC){
        String mySQL="Select MaNCC, TenNCC, SDT, DiaChi, Email from NHACUNGCAP where MaNCC like '%"+MaNCC+"%'";
        return DB.GetData(mySQL);
    }
    
    public ResultSet SearchNCCByName(String TenNCC){
        String mySQL="Select MaNCC, TenNCC, SDT, DiaChi, Email from NHACUNGCAP where TenNCC like '%"+TenNCC+"%'";
        return DB.GetData(mySQL);
    }
    
    public ResultSet SearchNCCByPhone(String SDT){
        String mySQL="Select MaNCC, TenNCC, SDT, DiaChi, Email from NHACUNGCAP where SDT like '%"+SDT+"%'";
        return DB.GetData(mySQL);
    }
}

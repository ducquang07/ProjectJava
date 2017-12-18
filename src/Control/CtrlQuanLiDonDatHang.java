/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModNhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Coldblood
 */
public class CtrlQuanLiDonDatHang {
    Connect DB=new Connect();
    SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
    public ResultSet LayDSDonDatHang(){
        String SQL="Select MaDDH, MaNCC, NgayDatHang,TrangThai from DONDATHANG";
        return DB.GetData(SQL);
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
    public boolean CloseConnection(){
        return DB.CloseDB();
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
    public ResultSet SearchDonDHByNgayDH(Date tungay, Date denngay)
    {
        String mySQL="Select * from DONDATHANG where NgayDH between ('"+dt1.format(tungay)+"') and ('"+dt1.format(denngay)+" 23:59:59')";
        return DB.GetData(mySQL);
    }
    public ResultSet SearchDonDHByNhaCC(String TenNCC)
    {
        String mySQL="Select NCC.TenNCC FROM NHACUNGCAP NCC, DONDATHANG DDH WHERE NCC.MaNCC = DDH.MaNCC AND NCC.TenNCC like '%"+TenNCC+"%'";
        return DB.GetData(mySQL);
    }
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC=new ModNhaCungCap();
        return modNCC.GetALL();
    }
}


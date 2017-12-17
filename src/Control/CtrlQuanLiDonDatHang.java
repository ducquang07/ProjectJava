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
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC=new ModNhaCungCap();
        return modNCC.GetALL();
    }
}


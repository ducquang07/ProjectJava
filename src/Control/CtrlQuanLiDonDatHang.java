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
        String SQL="Select DDH.MaDDH, DDH.MaNCC, DDH.NgayDatHang,DDH.TrangThai,CTDH.MaSP, CTDH.SoLuong from DONDATHANG DDH, CTDDH CTDH where DDH.MaDDH=CTDH.MaDDH";
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
    public ResultSet Search(Date tungay, Date denngay,String TenNCC, String TrangThai)
    {
        String mySQL="Select DDH.MaDDH, DDH.NgayDatHang, DDH.TrangThai, NCC.MaNCC, NCC.TenNCC from DONDATHANG DDH, NHACUNGCAP NCC where DDH.MaNCC = NCC.MaNCC and NCC.TenNCC = '"+TenNCC+"' and DDH.TrangThai = '"+TrangThai+"' and DDH.NgayDatHang between ('"+dt1.format(tungay)+"00:00:00') and ('"+dt1.format(denngay)+" 23:59:59')";
        return DB.GetData(mySQL);
    }
    
    public ResultSet Search1(Date tungay, Date denngay, String TenNCC)
    {
        String mySQL="Select DDH.MaDDH, DDH.NgayDatHang, DDH.TrangThai, NCC.MaNCC, NCC.TenNCC from NHACUNGCAP NCC, DONDATHANG DDH where NCC.MaNCC = DDH.MaNCC and NCC.TenNCC = '"+TenNCC+"' and DDH.NgayDatHang between ('"+dt1.format(tungay)+"00:00:00') and ('"+dt1.format(denngay)+" 23:59:59')";
        return DB.GetData(mySQL);
    }
     public ResultSet Search2(Date tungay, Date denngay)
    {
        String mySQL="Select DDH.MaDDH, DDH.NgayDatHang, DDH.TrangThai, NCC.MaNCC, NCC.TenNCC from NHACUNGCAP NCC, DONDATHANG DDH where NCC.MaNCC = DDH.MaNCC and DDH.NgayDatHang between ('"+dt1.format(tungay)+"00:00:00') and ('"+dt1.format(denngay)+" 23:59:59')";
        return DB.GetData(mySQL);
    }
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC=new ModNhaCungCap();
        return modNCC.GetALL();
    }
}


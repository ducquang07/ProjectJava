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
        String SQL="Select DDH.MaDDH, DDH.MaNCC, DDH.NgayDatHang, DDH.TrangThai, NCC.TenNCC from DONDATHANG DDH, NHACUNGCAP NCC where DDH.MaNCC=NCC.MaNCC";
        return DB.GetData(SQL);
    }

    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    
    public ResultSet LayThongTinChiTietDDH(String MaDDH){
        String SQL="Select CT.*,SP.TenSP from CTDDH CT,SANPHAM SP where SP.MaSP=CT.MaSP and CT.MaDDH='"+MaDDH+"'";
        return DB.GetData(SQL);
    }
    
     
    public ResultSet TimKiemDDHByMaDDH(String MaDDH,String MaNCC,String TinhTrang,Date tungay,Date denngay){
        try{
           String SQL="Select DDH.MaDDH, DDH.MaNCC, DDH.NgayDatHang, DDH.TrangThai, NCC.TenNCC from DONDATHANG DDH, NHACUNGCAP NCC where DDH.MaNCC=NCC.MaNCC and DDH.MaDDH like '%"+MaDDH+"%' and DDH.MaNCC like '%"+MaNCC+"%' and DDH.TrangThai like '%"+TinhTrang+"%' and DDH.NgayDatHang between ('"+dt1.format(tungay)+"') and ('"+dt1.format(denngay)+"  23:59:59')";
           return DB.GetData(SQL);
        }
        catch(Exception ex){
           System.out.println("Ngoại lệ tại CtrlDanhSachHoaDon.TimKiemHDL:"+ex.getMessage());
        }
        return null;
    } 
     
     
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC=new ModNhaCungCap();
        return modNCC.GetALL();
    }
}


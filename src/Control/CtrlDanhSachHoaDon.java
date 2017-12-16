/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModChiTietHDL;
import Model.ModHoaDonLe;
import Model.ModHoaDonSi;
import Model.ModKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlDanhSachHoaDon {
    Connect DB = new Connect();
    SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
    public ResultSet LayDanhSachHoaDonLe(){
        try{
        ModHoaDonLe modHDL = new ModHoaDonLe();
        return modHDL.GetALL();
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại CtrlDanhSachHoaDon.LayDanhSachHoaDonLe: "+ex.getMessage());
        }
        return null;
    }
    
    public ResultSet LayDanhSachHoaDonSi(){
        try{
        String SQL="Select HDS.*,KH.TenKH,KH.DiaChi,KH.SDT from KHACHHANG KH,HOADONSI HDS where KH.MaKH=HDS.MaKH";
        return DB.GetData(SQL);
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại CtrlDanhSachHoaDon.LayDanhSachHoaDonSi: "+ex.getMessage());
        }
        return null;
    }
    
    public ResultSet LayThongTinChiTietHoaDonLe(String SoHDL){
        try{
        String SQL = "Select CT.*,SP.TenSP,SP.DVT from SANPHAM SP, CTHDL CT where SP.MaSP=CT.MaSP and CT.SoHDL='"+SoHDL+"'";
        return DB.GetData(SQL);
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại CtrlDanhSachHoaDon.LayThongTinChiTietHoaDonLe: "+ex.getMessage());
        }
        return null;
    }
    
    public ResultSet LayThongTinChiTietHoaDonSi(String SoHDS){
        try{
        String SQL = "Select CT.*,SP.TenSP,SP.DVT from SANPHAM SP, CTHDS CT where SP.MaSP=CT.MaSP and CT.SoHDS='"+SoHDS+"'";
        return DB.GetData(SQL);
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại CtrlDanhSachHoaDon.LayThongTinChiTietHoaDonSi: "+ex.getMessage());
        }
        return null;
    }
    
    public ResultSet LayDanhSachKhachHang(){
        try{
            String SQL="Select MaKH,TenKH from KHACHHANG where LoaiKH='KHS'";
            return DB.GetData(SQL);
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại CtrlDanhSachHoaDon.LayDanhSachKhachHang:"+ex.getMessage());
        }
        return null;
    }
    
    public ResultSet TimKiemHDL(String TenKH,String SoHDL,Date tungay,Date denngay){
        try{
           String SQL="Select * from HOADONLE where TenKH like '%"+TenKH+"%' and SoHDL like '%"+SoHDL+"%' and NgayLap between ('"+dt1.format(tungay)+"') and ('"+dt1.format(denngay)+"  23:59:59')";
           return DB.GetData(SQL);
        }
        catch(Exception ex){
           System.out.println("Ngoại lệ tại CtrlDanhSachHoaDon.LayDanhSachKhachHang:"+ex.getMessage());
        }
        return null;
    }
    
    //public ResultSet TimKiemHDL(String MaKH,)
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

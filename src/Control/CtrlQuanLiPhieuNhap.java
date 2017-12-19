/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DucQuang
 */
public class CtrlQuanLiPhieuNhap {
    Connect DB=new Connect();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    public ResultSet LayDSPhieuNhap(){
        String SQL="Select PN.MaPN, PN.NgayNhap, PN.MaDDH, NCC.MaNCC, NCC.TenNCC from PHIEUNHAP PN, DONDATHANG DDH, NHACUNGCAP NCC where PN.MaDDH=DDH.MaDDH and DDH.MaNCC=NCC.MaNCC";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayCTPN(String MaPN){
        String SQL="Select CT.MaPN, CT.MaSP, SP.TenSP, CT.SoLuong, CT.DonGia from CTPN CT, SANPHAM SP where CT.MaSP=SP.MaSP and CT.MaPN='"+MaPN+"'";
        return DB.GetData(SQL);
    }
    
    public ResultSet Search(Date TuNgay, Date DenNgay, String TenNCC, String MaPN){
        String SQL="Select PN.MaPN, PN.NgayNhap, PN.MaDDH, NCC.MaNCC, NCC.TenNCC from PHIEUNHAP PN, DONDATHANG DDH, NHACUNGCAP NCC where PN.MaDDH=DDH.MaDDH and DDH.MaNCC=NCC.MaNCC and PN.MaPN= '"+MaPN+"' and NCC.TenNCC=N'"+TenNCC+"' and PN.NgayNhap between '"+df.format(TuNgay)+" 00:00:00'and'"+df.format(DenNgay)+" 23:59:59'";
        System.out.println(SQL);
        return DB.GetData(SQL);
    }
    
    public ResultSet SearchByTenNCC(Date TuNgay, Date DenNgay, String TenNCC){
        String SQL="Select PN.MaPN, PN.NgayNhap, PN.MaDDH, NCC.MaNCC, NCC.TenNCC from PHIEUNHAP PN, DONDATHANG DDH, NHACUNGCAP NCC where PN.MaDDH=DDH.MaDDH and DDH.MaNCC=NCC.MaNCC and NCC.TenNCC=N'"+TenNCC+"' and PN.NgayNhap between '"+df.format(TuNgay)+" 00:00:00'and'"+df.format(DenNgay)+" 23:59:59'";
        System.out.println(SQL);
        return DB.GetData(SQL);
    }
    
    public ResultSet LayTenNCC(){
        String SQL="Select TenNCC from NHACUNGCAP";
        return DB.GetData(SQL);
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

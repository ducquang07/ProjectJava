/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;

/**
 *
 * @author DucQuang
 */
public class CtrlLapPhieuNhapHang {
    Connect DB=new Connect();
    public ResultSet LayDSPhieuNhap(){
        String SQL="Select PN.MaPN, PN.NgayNhap, PN.MaDDH, NCC.MaNCC, NCC.TenNCC from PHIEUNHAP PN, DONDATHANG DDH, NHACUNGCAP NCC where PN.MaDDH=DDH.MaDDH and DDH.MaNCC=NCC.MaNCC";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayCTPN(String MaPN){
        String SQL="Select CT.MaPN, CT.MaSP, SP.TenSP, CT.SoLuong, CT.DonGia from CTPN CT, SANPHAM SP where CT.MaSP=SP.MaSP and CT.MaPN='"+MaPN+"'";
        return DB.GetData(SQL);
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

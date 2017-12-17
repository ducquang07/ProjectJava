/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModKhachHang;
import Model.ModLoaiSanPham;
import Model.ModNhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlLapHoaDonSi {
    Connect DB=new Connect();
    public ResultSet LayDanhSachSanPham(){
        String SQL="Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                + "from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC";
        return DB.GetData(SQL);
    }
     
    public ResultSet LayDanhSachKhachHang(){
        String SQL="Select * from KHACHHANG where LoaiKH='KHS'";
        return DB.GetData(SQL);
    } 
    
    public ResultSet LayDanhSachLoaiSanPham(){
        ModLoaiSanPham modLSP = new ModLoaiSanPham();
        return modLSP.GetALL();
    } 
    
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC = new ModNhaCungCap();
        return modNCC.GetALL();
    }
    
    public ResultSet SearchSanPhamByID(String MaSP,String MaLSP,String MaNCC){
        String SQL="Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                +" from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC"
                +" and SP.MaSP like '%"+MaSP+"%'";
        if(!MaLSP.equals("")) SQL+=" and SP.MaLoaiSP ='"+MaLSP+"'";
        if(!MaNCC.equals("")) SQL+=" and SP.MaNCC='"+MaNCC+"'";
        return DB.GetData(SQL);
    }
    
    public ResultSet SearchSanPhamByName(String TenSP,String MaLSP,String MaNCC){
        String SQL="Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                +" from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC"
                +" and SP.TenSP like '%"+TenSP+"%'";
        if(!MaLSP.equals("")) SQL+=" and SP.MaLoaiSP ='"+MaLSP+"'";
        if(!MaNCC.equals("")) SQL+=" and SP.MaNCC='"+MaNCC+"'";
        return DB.GetData(SQL);
    }
    
    public String LaySoHDS(){
        String ID="HDS0001";
        ResultSet rs=null;
        String SQL="Select * from HOADONSI order by SoHDS DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("SoHDS");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="HDS000"+STT;
                else if(STT<100) ID="HDS00"+STT;
                else ID="HDS0"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlLapHoaDonSi.LaySoHDS:"+ex.getMessage());
        }
        return ID;
    }
     
    public boolean  CloseConnection(){
        return DB.CloseDB();
    }
}

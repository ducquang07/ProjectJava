/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModLoaiSanPham;
import Model.ModNhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlLapDonDatHang {
    Connect DB=new Connect();
    
    public ResultSet LayDanhSachSanPhamNCC(String MaNCC){
        String SQL="Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                + "from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC and NCC.MaNCC='"+MaNCC+"'";
        return DB.GetData(SQL);
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
    
    public ResultSet LayDanhSachLoaiSanPham(){
        ModLoaiSanPham modLSP = new ModLoaiSanPham();
        return modLSP.GetALL();
    } 
    
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC = new ModNhaCungCap();
        return modNCC.GetALL();
    }
    
    public String LayMaDDH(){
        String ID="DDH0001";
        ResultSet rs=null;
        String SQL="Select * from DONDATHANG order by MaDDH DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("MaDDH");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="DDH000"+STT;
                else if(STT<100) ID="DDH00"+STT;
                else ID="DDH0"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlLapDonDatHang.LayMaDDH:"+ex.getMessage());
        }
        return ID;
    }
    
    public int LaySoLuongSanPham(String MaSP,String SoHDL){
        int SoLuong=0;
        String SQL = "Select SoLuong from CTHDL where SoHDL='"+SoHDL+"' and MaSP='"+MaSP+"'";
        ResultSet rs = DB.GetData(SQL);
        try {
            if(rs.next()){
                SoLuong=Integer.parseInt(rs.getString("SoLuong"));
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlLapHoaDonLe.LaySoLuongSanPham:"+ex.getMessage());
            return 0;
        }
        SQL="Select SoLuong from SanPham where MaSP='"+MaSP+"'";
        rs=DB.GetData(SQL);
        try {
            if(rs.next()) return SoLuong+Integer.parseInt(rs.getString("SoLuong"));
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlLapHoaDonLe.LaySoLuongSanPham:"+ex.getMessage());
        }
        return 0;
    }
    
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }

}
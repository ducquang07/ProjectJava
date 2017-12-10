/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Connect.Connect;
import java.sql.ResultSet;
import Model.ModLoaiSanPham;
import Model.ModNhaCungCap;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlLapHoaDonLe {
    Connect DB=new Connect();
    public ResultSet LayDanhSachSanPham(){
        String SQL="Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.SoLuong,SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                + "from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayDanhSachLoaiSanPham(){
        ModLoaiSanPham modLSP = new ModLoaiSanPham();
        return modLSP.GetALL();
    } 
    
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC=new ModNhaCungCap();
        return modNCC.GetALL();
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }

}

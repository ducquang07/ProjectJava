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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    
    public ResultSet SearchSanPhamByID(String MaSP,String MaLSP,String MaNCC){
        String SQL="Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.SoLuong,SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                +" from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC"
                +" and SP.MaSP like '%"+MaSP+"%'";
        if(!MaLSP.equals("")) SQL+=" and SP.MaLoaiSP ='"+MaLSP+"'";
        if(!MaNCC.equals("")) SQL+=" and SP.MaNCC='"+MaNCC+"'";
        return DB.GetData(SQL);
    }
    
    public ResultSet SearchSanPhamByName(String TenSP,String MaLSP,String MaNCC){
        String SQL="Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.SoLuong,SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
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
        ModNhaCungCap modNCC=new ModNhaCungCap();
        return modNCC.GetALL();
    }
    
    public String LaySoHDL(){
        String ID="HDL0001";
        ResultSet rs=null;
        String SQL="Select * from HOADONLE order by SoHDL DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("SoHDL");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="HDL000"+STT;
                else if(STT<100) ID="HDL00"+STT;
                else ID="HDL0"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlLapHoaDonLe.LaySoHDL:"+ex.getMessage());
        }
        return ID;
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }

}

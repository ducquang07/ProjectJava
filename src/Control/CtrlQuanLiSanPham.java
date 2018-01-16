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
 * @author Coldblood
 */
public class CtrlQuanLiSanPham {

    Connect DB = new Connect();

    public ResultSet LayDSSanPham() {
        String SQL = "Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MoTa,SP.LoiNhuanBien, SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                + "from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC";
        return DB.GetData(SQL);
    }

    public ResultSet LayDSSanPhamDuoiMucTon(){
        String SQL = "Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MoTa,SP.LoiNhuanBien, SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                + "from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC,ThamSo TS where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC and SP.SoLuong<TS.SoLuongToiThieu";
        return DB.GetData(SQL);
    }
    public boolean CloseConnection() {
        return DB.CloseDB();
    }

    public String LayMaSP() {
        String ID = "SP001";
        ResultSet rs = null;
        String mySQL = "Select * from SANPHAM order by MaSP DESC limit 1";
        try {
            rs = DB.GetData(mySQL);
            if (rs.next()) {
                ID = rs.getString("MaSP");
                int STT = Integer.parseInt(ID.substring(3));
                STT += 1;
                if (STT < 10) {
                    ID = "SP00" + STT;
                } else if (STT < 100) {
                    ID = "SP0" + STT;
                } else {
                    ID = "SP" + STT;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlQuanLiSanPham.LayMaSP:" + ex.getMessage());
        }
        return ID;
    }

    public ResultSet SearchByID(String MaSP, String MaNCC, String MaLSP) {
        String SQL = "Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MoTa,SP.LoiNhuanBien, SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                + " from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC"
                + " and SP.MaSP like '%" + MaSP + "%'";
        if (!MaLSP.equals("")) {
            SQL += " and SP.MaLoaiSP ='" + MaLSP + "'";
        }
        if (!MaNCC.equals("")) {
            SQL += " and SP.MaNCC='" + MaNCC + "'";
        }
        return DB.GetData(SQL);
    }

    public ResultSet SearchByName(String TenSP, String MaNCC, String MaLSP) {
        String SQL = "Select SP.MaSP,SP.TenSP,SP.DVT,SP.GiaLe,SP.GiaSi,SP.SoLuong,SP.MoTa,SP.LoiNhuanBien, SP.MaLoaiSP,SP.MaNCC,LSP.TenLoaiSP,NCC.TenNCC "
                + " from SANPHAM SP,LOAISANPHAM LSP,NHACUNGCAP NCC where SP.MaLoaiSP=LSP.MaLoaiSP and SP.MaNCC=NCC.MaNCC"
                + " and SP.TenSP like '%" + TenSP + "%'";
        if (!MaLSP.equals("")) {
            SQL += " and SP.MaLoaiSP ='" + MaLSP + "'";
        }
        if (!MaNCC.equals("")) {
            SQL += " and SP.MaNCC='" + MaNCC + "'";
        }
        return DB.GetData(SQL);
    }

    public ResultSet SearchLoaiSPByName(String TenSP) {
        String mySQL = "Select * from SANPHAM where TenLoaiSP like '%" + TenSP + "%'";
        return DB.GetData(mySQL);
    }

    public ResultSet LayDanhSachNhaCungCap() {
        ModNhaCungCap modNCC = new ModNhaCungCap();
        return modNCC.GetALL();
    }

    public ResultSet LayDanhSachLoaiSanPham() {
        ModLoaiSanPham modLSP = new ModLoaiSanPham();
        return modLSP.GetALL();
    }
}

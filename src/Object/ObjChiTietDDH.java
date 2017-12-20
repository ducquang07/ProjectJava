/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.util.Date;

/**
 *
 * @author Coldblood
 */
public class ObjChiTietDDH {
    private String MaDDH;
    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private String MaNCC;
    private String TenNCC;
    private Date NgayDatHang;
    private String TrangThai;
    private String DVT;
    
    public ObjChiTietDDH()
    {
    }
    public ObjChiTietDDH(String MaDDH, String MaSP, int SoLuong)
    {
        this.MaDDH = MaDDH;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
    }

    public ObjChiTietDDH(String MaDDH, String MaSP, String TenSP, int SoLuong) {
        this.MaDDH = MaDDH;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
    }

    public ObjChiTietDDH(String MaDDH,String MaSP, String TenSP, int SoLuong, String DVT) {
        this.MaDDH=MaDDH;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
        this.DVT = DVT;
    }
    
    
    public ObjChiTietDDH(String MaDDH, String MaSP, int SoLuong, String MaNCC, String TenNCC, Date NgayDatHang, String TrangThai)
    {
        this.MaDDH = MaDDH;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.NgayDatHang = NgayDatHang;
        this.TrangThai = TrangThai;
    }

    
    //Get
    public String getTenSP() {
        return TenSP;
    }
    
    public String getMaDDH()
    {
        return MaDDH;
    }
    public String getMaSP()
    {
        return MaSP;
    }
    public int getSoLuong()
    {
        return SoLuong;
    }
    public String getMaNCC()
    {
        return MaNCC;
    }
    public String getTenNCC()
    {
        return TenNCC;
    }
    public String getTrangThai()
    {
        return TrangThai;
    }
    public Date getNgayDatHang()
    {
        return NgayDatHang;
    }

    public String getDVT() {
        return DVT;
    }
    
    
    //Set
    public void setMaDDH(String MaDDH)
    {
        this.MaDDH = MaDDH;
    }
    public void setMaSP(String MaSP)
    {
        this.MaDDH = MaSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public void setNgayDatHang(Date NgayDatHang) {
        this.NgayDatHang = NgayDatHang;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public void setSoLuong(int SoLuong)
    {
        this.SoLuong = SoLuong;
    }
}

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
public class ObjDonDatHang {
    
    private String MaDDH;
    private String MaPN;
    private String MaNCC;
    private String TenNCC;
    private Date NgayDatHang;
    private Date NgayNhap;
    private String TrangThai; 

    //Constructor
    public ObjDonDatHang()
    {
    }

    public ObjDonDatHang(String MaDDH, String MaNCC, String TenNCC, Date NgayDatHang, String TrangThai) {
        this.MaDDH = MaDDH;
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.NgayDatHang = NgayDatHang;
        this.TrangThai = TrangThai;
    }

    public ObjDonDatHang(String MaDDH, String MaNCC, Date NgayDatHang, String TrangThai)
    {
        this.MaDDH = MaDDH;
        this.MaNCC = MaNCC;
        this.NgayDatHang = NgayDatHang;
        this.TrangThai = TrangThai;
    }

    public ObjDonDatHang(String MaDDH, String MaPN, String TenNCC, Date NgayDatHang, Date NgayNhap) {
        this.MaDDH = MaDDH;
        this.MaPN = MaPN;
        this.TenNCC = TenNCC;
        this.NgayDatHang = NgayDatHang;
        this.NgayNhap = NgayNhap;
    }

    public ObjDonDatHang(String MaDDH, String MaPN, String MaNCC, String TenNCC, Date NgayDatHang, String TrangThai) {
        this.MaDDH = MaDDH;
        this.MaPN = MaPN;
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.NgayDatHang = NgayDatHang;
        this.TrangThai = TrangThai;
    }
    
    
    //Get
    
    public Date getNgayNhap()
    {
        return NgayNhap;
    }

    public String getMaPN() {
        return MaPN;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public String getMaDDH() {
        return MaDDH;
    }
    public String getMaNCC()
    {
        return MaNCC;
    }
    public Date getNgayDatHang()
    {
        return NgayDatHang;
    }
    public String getTrangThai()
    {
        return TrangThai;
    }
    
    //Set
    
    public void setNgayNhap(Date NgayNhap)
    {
        this.NgayNhap = NgayNhap;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public void setMaDDH(String MaDDH) {
        this.MaDDH = MaDDH;
    }
    public void setMaNCC(String MaNCC)
    {
        this.MaNCC = MaNCC;
    }
    public void setNgayDatHang(Date NgayDatHang)
    {
        this.NgayDatHang = NgayDatHang;
    }
    public void setTrangThai(String TrangThai)
    {
        this.TrangThai = TrangThai;
    }
}

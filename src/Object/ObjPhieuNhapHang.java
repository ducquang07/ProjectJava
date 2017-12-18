/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.util.Date;

/**
 *
 * @author DucQuang
 */
public class ObjPhieuNhapHang {
    private String MaPN;
    private Date NgayNhap;
    private String MaDDH;
    private String MaNCC;
    private String TenNCC;
    

    public ObjPhieuNhapHang() {
    }

    public ObjPhieuNhapHang(String MaPN, Date NgayNhap, String MaDDH) {
        this.MaPN = MaPN;
        this.NgayNhap = NgayNhap;
        this.MaDDH = MaDDH;
    }

    public ObjPhieuNhapHang(String MaPN, Date NgayNhap, String MaDDH, String MaNCC, String TenNCC) {
        this.MaPN = MaPN;
        this.NgayNhap = NgayNhap;
        this.MaDDH = MaDDH;
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }
    
    public String getMaDDH() {
        return MaDDH;
    }

    public String getMaPN() {
        return MaPN;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public void setMaDDH(String MaDDH) {
        this.MaDDH = MaDDH;
    }
    
}

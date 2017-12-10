/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author ThaiNguyen
 */
public class ObjSanPham {
    private String MaSP;
    private String TenSP;
    private String MaLoaiSP;
    private String TenLoaiSP;
    private int GiaLe;
    private int GiaSi;
    private String DVT;
    private int SoLuong;
    private String MaNCC;
    private String TenNCC;
    private double LoiNhuanBien;
    private String MoTa;
    
    
////////Constructor
    public ObjSanPham() {
    }

    public ObjSanPham(String MaSP, String TenSP, String MaLoaiSP, int GiaLe, int GiaSi, String DVT, int SoLuong, String MaNCC, double LoiNhuanBien, String MoTa) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.MaLoaiSP = MaLoaiSP;
        this.GiaLe = GiaLe;
        this.GiaSi = GiaSi;
        this.DVT = DVT;
        this.SoLuong = SoLuong;
        this.MaNCC = MaNCC;
        this.LoiNhuanBien = LoiNhuanBien;
        this.MoTa = MoTa;
    }

    public ObjSanPham(String MaSP, String TenSP, String MaLoaiSP, String TenLoaiSP, int GiaLe, String DVT, int SoLuong, String MaNCC, String TenNCC) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.MaLoaiSP = MaLoaiSP;
        this.TenLoaiSP = TenLoaiSP;
        this.GiaLe = GiaLe;
        this.DVT = DVT;
        this.SoLuong = SoLuong;
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
    }

    

    
    //////Get
    public String getMaSP() {
        return MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public int getGiaLe() {
        return GiaLe;
    }

    public int getGiaSi() {
        return GiaSi;
    }

    public String getDVT() {
        return DVT;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public double getLoiNhuanBien() {
        return LoiNhuanBien;
    }

    public String getMoTa() {
        return MoTa;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    ////////Set
    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public void setGiaLe(int GiaLe) {
        this.GiaLe = GiaLe;
    }

    public void setGiaSi(int GiaSi) {
        this.GiaSi = GiaSi;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setLoiNhuanBien(double LoiNhuanBien) {
        this.LoiNhuanBien = LoiNhuanBien;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    
}

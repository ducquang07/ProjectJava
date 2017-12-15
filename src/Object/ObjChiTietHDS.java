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
public class ObjChiTietHDS {
    private String SoHDS;
    private String MaSP;
    private String TenSP;
    private String DVT;
    private int SoLuong;
    private int DonGia;
    private int ThanhTien;

    ////Constructor
    public ObjChiTietHDS() {
    }

    
    public ObjChiTietHDS(String SoHDS, String MaSP, int SoLuong, int DonGia, int ThanhTien) {
        this.SoHDS = SoHDS;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }

    public ObjChiTietHDS(String SoHDS,String MaSP, String TenSP, String DVT, int SoLuong, int DonGia, int ThanhTien) {
        this.SoHDS=SoHDS;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.DVT = DVT;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }
    
    //Get

    public String getSoHDS() {
        return SoHDS;
    }

    public String getMaSP() {
        return MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public String getDVT() {
        return DVT;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public int getThanhTien() {
        return ThanhTien;
    }
    
    //Set

    public void setSoHDS(String SoHDS) {
        this.SoHDS = SoHDS;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}

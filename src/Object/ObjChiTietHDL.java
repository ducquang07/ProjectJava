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
public class ObjChiTietHDL {
    private String SoHDL;
    private String MaSP;
    private int SoLuong;
    private int DonGia;
    private int ThanhTien;
    
    //Constructor

    public ObjChiTietHDL() {
    }

    public ObjChiTietHDL(String SoHDL, String MaSP, int SoLuong, int DonGia, int ThanhTien) {
        this.SoHDL = SoHDL;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }
    
    //Get

    public String getSoHDL() {
        return SoHDL;
    }

    public String getMaSP() {
        return MaSP;
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

    public void setSoHDL(String SoHDL) {
        this.SoHDL = SoHDL;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author DucQuang
 */
public class ObjChiTietPNH {
    private String MaPN;
    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private int DonGia;

    public ObjChiTietPNH() {
    }

    public ObjChiTietPNH(String MaPN, String MaSP, int SoLuong, int DonGia) {
        this.MaPN = MaPN;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public ObjChiTietPNH(String MaPN, String MaSP, String TenSP, int SoLuong, int DonGia) {
        this.MaPN = MaPN;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }
    

    public String getMaPN() {
        return MaPN;
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

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
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
    
}

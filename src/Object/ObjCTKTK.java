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
public class ObjCTKTK {
    private String MaKTK;
    private String MaSP;
    private String TenSP;
    private String TenNCC;
    private int SoLuongTonDauKi;
    private int SoLuongNhap;
    private int SoLuongXuat;
    private int SoLuongTonCuoiKi;

    public ObjCTKTK() {
    }

    public ObjCTKTK(String MaKTK, String MaSP, int SoLuongTonDauKi, int SoLuongNhap, int SoLuongXuat, int SoLuongTonCuoiKi) {
        this.MaKTK = MaKTK;
        this.MaSP = MaSP;
        this.SoLuongTonDauKi = SoLuongTonDauKi;
        this.SoLuongNhap = SoLuongNhap;
        this.SoLuongXuat = SoLuongXuat;
        this.SoLuongTonCuoiKi = SoLuongTonCuoiKi;
    }

    public ObjCTKTK(String MaKTK, String MaSP, String TenSP, String TenNCC, int SoLuongTonDauKi, int SoLuongNhap, int SoLuongXuat, int SoLuongTonCuoiKi) {
        this.MaKTK = MaKTK;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.TenNCC=TenNCC;
        this.SoLuongTonDauKi = SoLuongTonDauKi;
        this.SoLuongNhap = SoLuongNhap;
        this.SoLuongXuat = SoLuongXuat;
        this.SoLuongTonCuoiKi = SoLuongTonCuoiKi;
    }

    public String getMaKTK() {
        return MaKTK;
    }

    public String getMaSP() {
        return MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public int getSoLuongTonDauKi() {
        return SoLuongTonDauKi;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public int getSoLuongNhap() {
        return SoLuongNhap;
    }

    public int getSoLuongXuat() {
        return SoLuongXuat;
    }

    public int getSoLuongTonCuoiKi() {
        return SoLuongTonCuoiKi;
    }

    public void setMaKTK(String MaKTK) {
        this.MaKTK = MaKTK;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setSoLuongTonDauKi(int SoLuongTonDauKi) {
        this.SoLuongTonDauKi = SoLuongTonDauKi;
    }

    public void setSoLuongNhap(int SoLuongNhap) {
        this.SoLuongNhap = SoLuongNhap;
    }

    public void setSoLuongXuat(int SoLuongXuat) {
        this.SoLuongXuat = SoLuongXuat;
    }

    public void setSoLuongTonCuoiKi(int SoLuongTonCuoiKi) {
        this.SoLuongTonCuoiKi = SoLuongTonCuoiKi;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }
    
    
}

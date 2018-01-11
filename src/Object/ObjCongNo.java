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
public class ObjCongNo {
    private String MaKCN;
    private String MaKH;
    private int SoDuNoDauKi;
    private int SoNoPhatSinhTrongKi;
    private int SoTienThuTrongKi;
    private int SoDuNoCuoiKi;
    private String TenKH;
    public ObjCongNo(String MaKCN, String MaKH,String TenKH, int SoDuNoDauKi, int SoNoPhatSinhTrongKi, int SoTienThuTrongKi, int SoDuNoCuoiKi) {
        this.MaKCN = MaKCN;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SoDuNoDauKi = SoDuNoDauKi;
        this.SoNoPhatSinhTrongKi = SoNoPhatSinhTrongKi;
        this.SoTienThuTrongKi = SoTienThuTrongKi;
        this.SoDuNoCuoiKi = SoDuNoCuoiKi;
    }

    public String getTenKH() {
        return TenKH;
    }
    
    public String getMaKCN() {
        return MaKCN;
    }

    public String getMaKH() {
        return MaKH;
    }

    public int getSoDuNoDauKi() {
        return SoDuNoDauKi;
    }

    public int getSoNoPhatSinhTrongKi() {
        return SoNoPhatSinhTrongKi;
    }

    public int getSoTienThuTrongKi() {
        return SoTienThuTrongKi;
    }

    public int getSoDuNoCuoiKi() {
        return SoDuNoCuoiKi;
    }

    public void setMaKCN(String MaKCN) {
        this.MaKCN = MaKCN;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setSoDuNoDauKi(int SoDuNoDauKi) {
        this.SoDuNoDauKi = SoDuNoDauKi;
    }

    public void setSoNoPhatSinhTrongKi(int SoNoPhatSinhTrongKi) {
        this.SoNoPhatSinhTrongKi = SoNoPhatSinhTrongKi;
    }

    public void setSoTienThuTrongKi(int SoTienThuTrongKi) {
        this.SoTienThuTrongKi = SoTienThuTrongKi;
    }

    public void setSoDuNoCuoiKi(int SoDuNoCuoiKi) {
        this.SoDuNoCuoiKi = SoDuNoCuoiKi;
    }
    
    
}

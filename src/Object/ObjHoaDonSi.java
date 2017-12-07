/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.sql.Date;

/**
 *
 * @author ThaiNguyen
 */
public class ObjHoaDonSi {
    private String SoHDS;
    private String MaKH;
    private Date NgayDat;
    private Date NgayGiaoDuKien;
    private int TongTien;
    private String TinhTrangGiaoHang;
    
    //Constructor

    public ObjHoaDonSi() {
    }

    public ObjHoaDonSi(String SoHDS, String MaKH, Date NgayDat, Date NgayGiaoDuKien, int TongTien, String TinhTrangGiaoHang) {
        this.SoHDS = SoHDS;
        this.MaKH = MaKH;
        this.NgayDat = NgayDat;
        this.NgayGiaoDuKien = NgayGiaoDuKien;
        this.TongTien = TongTien;
        this.TinhTrangGiaoHang = TinhTrangGiaoHang;
    }
    
    //Get

    public String getSoHDS() {
        return SoHDS;
    }

    public String getMaKH() {
        return MaKH;
    }

    public Date getNgayDat() {
        return NgayDat;
    }

    public Date getNgayGiaoDuKien() {
        return NgayGiaoDuKien;
    }

    public int getTongTien() {
        return TongTien;
    }

    public String getTinhTrangGiaoHang() {
        return TinhTrangGiaoHang;
    }
    
    //Set

    public void setSoHDS(String SoHDS) {
        this.SoHDS = SoHDS;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setNgayDat(Date NgayDat) {
        this.NgayDat = NgayDat;
    }

    public void setNgayGiaoDuKien(Date NgayGiaoDuKien) {
        this.NgayGiaoDuKien = NgayGiaoDuKien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public void setTinhTrangGiaoHang(String TinhTrangGiaoHang) {
        this.TinhTrangGiaoHang = TinhTrangGiaoHang;
    }
    
}

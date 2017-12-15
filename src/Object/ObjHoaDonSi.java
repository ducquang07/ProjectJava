/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.util.Date;

/**
 *
 * @author ThaiNguyen
 */
public class ObjHoaDonSi{
    private String SoHDS;
    private String MaKH;
    private Date NgayLap;
    private Date NgayGiaoDuKien;
    private int TongTien;
    private String TinhTrangGiaoHang;
    private String TenKH;
    private String SDT;
    private String DiaChi;
    
    //Constructor

    public ObjHoaDonSi() {
    }

    public ObjHoaDonSi(String SoHDS, String MaKH, Date NgayLap, Date NgayGiaoDuKien, int TongTien, String TinhTrangGiaoHang) {
        this.SoHDS = SoHDS;
        this.MaKH = MaKH;
        this.NgayLap = NgayLap;
        this.NgayGiaoDuKien = NgayGiaoDuKien;
        this.TongTien = TongTien;
        this.TinhTrangGiaoHang = TinhTrangGiaoHang;
    }
    
     public ObjHoaDonSi(String SoHDS, String MaKH, String TenKH, String DiaChi, String SDT, Date NgayLap, Date NgayGiaoDuKien, int TongTien, String TinhTrangGiaoHang) {
        
        this.SoHDS = SoHDS;
        this.MaKH = MaKH;
        this.TenKH=TenKH;
        this.DiaChi=DiaChi;
        this.SDT=SDT;
        this.NgayLap = NgayLap;
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

    public Date getNgayLap() {
        return NgayLap;
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

    public String getTenKH() {
        return TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }
    
    //Set

    public void setSoHDS(String SoHDS) {
        this.SoHDS = SoHDS;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setNgayDat(Date NgayLap) {
        this.NgayLap = NgayLap;
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

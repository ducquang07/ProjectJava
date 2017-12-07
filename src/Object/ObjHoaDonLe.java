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
public class ObjHoaDonLe {
    private String SoHDL;
    private String TenKH;
    private Date NgayLap;
    private int TongTien;
    
    //Constructor

    public ObjHoaDonLe() {
    }

    public ObjHoaDonLe(String SoHDL, String TenKH, Date NgayLap, int TongTien) {
        this.SoHDL = SoHDL;
        this.TenKH = TenKH;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
    }
    
    //Get

    public String getSoHDL() {
        return SoHDL;
    }

    public String getTenKH() {
        return TenKH;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public int getTongTien() {
        return TongTien;
    }
    
    //Set

    public void setSoHDL(String SoHDL) {
        this.SoHDL = SoHDL;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }
    
}

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
public class ObjKyTonKho {
    private String MaKTK;
    private Date TuNgay;
    private Date DenNgay;

    public ObjKyTonKho() {
    }

    public ObjKyTonKho(String MaKTK) {
        this.MaKTK = MaKTK;
    }

    public ObjKyTonKho(String MaKTK, Date TuNgay, Date DenNgay) {
        this.MaKTK = MaKTK;
        this.TuNgay = TuNgay;
        this.DenNgay = DenNgay;
    }

    public String getMaKTK() {
        return MaKTK;
    }

    public Date getTuNgay() {
        return TuNgay;
    }

    public Date getDenNgay() {
        return DenNgay;
    }

    public void setMaKTK(String MaKTK) {
        this.MaKTK = MaKTK;
    }

    public void setTuNgay(Date TuNgay) {
        this.TuNgay = TuNgay;
    }

    public void setDenNgay(Date DenNgay) {
        this.DenNgay = DenNgay;
    }
    
    
}

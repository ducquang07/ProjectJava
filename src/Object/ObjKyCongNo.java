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
public class ObjKyCongNo {
    private String MaKCN;
    private Date TuNgay;
    private Date DenNgay;

    public ObjKyCongNo() {
    }

    public ObjKyCongNo(String MaKCN) {
        this.MaKCN = MaKCN;
    }

    public ObjKyCongNo(String MaKCN, Date TuNgay, Date DenNgay) {
        this.MaKCN = MaKCN;
        this.TuNgay = TuNgay;
        this.DenNgay = DenNgay;
    }

    public String getMaKCN() {
        return MaKCN;
    }

    public Date getTuNgay() {
        return TuNgay;
    }

    public Date getDenNgay() {
        return DenNgay;
    }

    public void setMaKCN(String MaKCN) {
        this.MaKCN = MaKCN;
    }

    public void setTuNgay(Date TuNgay) {
        this.TuNgay = TuNgay;
    }

    public void setDenNgay(Date DenNgay) {
        this.DenNgay = DenNgay;
    }
    
    
}

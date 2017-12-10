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
public class ObjLoaiSanPham {
    private String MaLoaiSP;
    private String TenLoaiSP;
    
    public ObjLoaiSanPham(){}
    
    public ObjLoaiSanPham(String MaLoaiSP,String TenLoaiSP){
        this.MaLoaiSP=MaLoaiSP;
        this.TenLoaiSP=TenLoaiSP;
    }

    public ObjLoaiSanPham(String TenLoaiSP) {
        this.TenLoaiSP = TenLoaiSP;
    }
    
    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public void setTenLoaiSP(String TenLoaiSP) {
        this.TenLoaiSP = TenLoaiSP;
    }
    
    
}

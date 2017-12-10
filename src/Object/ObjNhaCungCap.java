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
public class ObjNhaCungCap {
    private String MaNCC;
    private String TenNCC;
    private String SDT;
    private String DiaChi;
    private String Email;
    private int NoCuaDaiLy;

    //////Constructor
    public ObjNhaCungCap() {
    }

    public ObjNhaCungCap(String MaNCC, String TenNCC, String SDT, String DiaChi, String Email) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.Email = Email;
    }

    public ObjNhaCungCap(String TenNCC) {
        this.TenNCC = TenNCC;
    }
    
    //////////Get

    public String getMaNCC() {
        return MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public String getSDT() {
        return SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public int getNoCuaDaiLy() {
        return NoCuaDaiLy;
    }
    
    ////Set

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setNoCuaDaiLy(int NoCuaDaiLy) {
        this.NoCuaDaiLy = NoCuaDaiLy;
    }
    
}

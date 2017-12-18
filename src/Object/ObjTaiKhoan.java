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
public class ObjTaiKhoan {
    private String TenDangNhap;
    private String MatKhau;
    private String Ten;
    private String PhanLoai;
    
    //Constructor

    public ObjTaiKhoan() {
    }

    public ObjTaiKhoan(String TenDangNhap, String MatKhau, String Ten, String PhanLoai) {
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Ten = Ten;
        this.PhanLoai = PhanLoai;
    }

    public ObjTaiKhoan(String TenDangNhap, String MatKhau) {
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
    }
    
    //Get

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public String getTen() {
        return Ten;
    }

    public String getPhanLoai() {
        return PhanLoai;
    }
    
    //Set

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public void setPhanLoai(String PhanLoai) {
        this.PhanLoai = PhanLoai;
    }
    
}

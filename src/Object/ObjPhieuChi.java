/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.util.Date;

/**
 *
 * @author Thanh
 */
public class ObjPhieuChi {
    private String MaPC;
    private String TongTienChi;
    private Date NgayChi;
    private String LyDoChi;
  
    private String MaNCC;
    private String TenNCC;
    private String SDT;
    private String DiaChi;
    private String NoCuaDaiLy;
    
    
    public ObjPhieuChi(){
        
    }
    public ObjPhieuChi(String MaPC, Date NgayChi, String MaNCC){
        this.MaPC=MaPC;
        this.NgayChi=NgayChi;
        this.MaNCC=MaNCC;        
    }
    
    public String getMaPC(){
        return MaPC;
    }
    public String getTongTienChi(){
        return TongTienChi;
    }
    public Date getNgayChi(){
        return NgayChi;
    }
    public String getLyDoChi(){
        return LyDoChi;
    }
    public String getMaNCC(){
        return MaNCC;
    }
    public String getTenNCC(){
        return TenNCC;
    }
    public String getSDT(){
        return SDT;
    }
    public String getDiaChi(){
        return DiaChi;  
    }
    public String getNoCuaDaiLy(){
        return NoCuaDaiLy;
    }
    
}

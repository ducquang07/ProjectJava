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
    private int TongTienChi;
    private Date NgayChi;
    private String LyDoChi;
  
    private String MaNCC;
    private String TenNCC;
    private String SDT;
    private String DiaChi;
    private String NoCuaDaiLy;
    
    
    public ObjPhieuChi(){
        
    }
    
    public ObjPhieuChi(String MaPC){
        this.MaPC=MaPC;
    }
    
    public ObjPhieuChi(String MaPC, String MaNCC){
        this.MaPC=MaPC;
        this.MaNCC=MaNCC;
    }
    
    public ObjPhieuChi(Date NgayChi,String MaPC,  String MaNCC, int TongTienChi, String LyDoChi){
        this.MaPC=MaPC;
        this.NgayChi=NgayChi;
        this.MaNCC=MaNCC;     
        this.TongTienChi=TongTienChi;
        this.LyDoChi=LyDoChi;
    }
    
    public String getMaPC(){
        return MaPC;
    }
    public int getTongTienChi(){
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
    
    public void setMaPC(String MaPC){
        this.MaPC=MaPC;
    }
    public void setTongTienChi(int TongTienChi){
        this.TongTienChi=TongTienChi;
    }
    public void setNgayChi(Date NgayChi){
        this.NgayChi=NgayChi;
    }
    public void setLyDoChi(String LyDoChi){
        this.LyDoChi=LyDoChi;
    }
    public void setMaNCC(String MaNCC){
        this.MaNCC=MaNCC;
    }
    public void setTenNCC(String TenNCC){
        this.TenNCC=TenNCC;
    }
    public void setSDT(String SDT){
        this.SDT=SDT;
    }public void setDiaChi(String DiaChi){
        this.DiaChi=DiaChi;
    }
    public void setNoCuaDaiLy(String NoCuaDaiLy){
        this.NoCuaDaiLy=NoCuaDaiLy;
    }
    
    
}

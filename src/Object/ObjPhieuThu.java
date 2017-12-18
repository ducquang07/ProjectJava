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
public class ObjPhieuThu {
    private String MaPT;
    private String LyDoThu;
    private int TongTienThu;
    private Date NgayThu;
    private String TenKH;
    private String MaKH;
    private String DiaChi;
    private String SDT;
    
    public ObjPhieuThu(){
        
    }
    
    public ObjPhieuThu(String MaPT, int TongTienThu, Date NgayThu,String TenKH,String MaKH, String DiaChi, String SDT ){
        this.MaPT=MaPT;
        this.LyDoThu=LyDoThu;
        this.TongTienThu=TongTienThu;
        this.NgayThu=NgayThu;
        this.TenKH=TenKH;
        this.MaKH=MaKH;
        this.DiaChi=DiaChi;
        this.SDT=SDT;
    }
    public ObjPhieuThu(String MaPT, String LyDoThu , int TongTienThu, Date NgayThu,String MaKH ){
        this.MaPT=MaPT;
        this.LyDoThu=LyDoThu;
        this.TongTienThu=TongTienThu;
        this.NgayThu=NgayThu;
        this.MaKH=MaKH;
    }
    
    public String getMaPT(){
        return MaPT;
    }
    public String getLyDoThu(){
        return LyDoThu;
    }
    public int getTongTienThu(){
        return TongTienThu;
    }
    public Date getNgayThu(){
        return NgayThu;
    }
    public String getTenKH(){
        return TenKH;
    }
    public String getMaKH(){
        return MaKH;
    }
    public String getDiaChi(){
        return DiaChi;
    }
    public String getSDT(){
        return SDT;
    }
    
    public void setMaPT(String MaPT){
        this.MaPT=MaPT;
    }
    public void setLyDoThu(String LyDoThu){
        this.LyDoThu=LyDoThu;
    }
    public void setTongTienThu(int TongTienThu){
        this.TongTienThu=TongTienThu;
    }
    public void setNgayThu(Date NgayThu){
        this.NgayThu=NgayThu;
    }
    public void setTenKH(String TenKH){
        this.TenKH=TenKH;
    }
    public void setMaKH(String MaKH){
        this.MaKH=MaKH;
    }
    public void setDiaChi(String DiaChi){
        this.DiaChi=DiaChi;
    }
    public void setSDT(String SDT){
        this.SDT=SDT;
    }
}

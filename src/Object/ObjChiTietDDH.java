/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.util.Date;

/**
 *
 * @author Coldblood
 */
public class ObjChiTietDDH {
    private String MaDDH;
    private String MaSP;
    private int SoLuong;
    private String MaNCC;
    private String TenNCC;
    private Date NgayDatHang;
    private String TrangThai;
    
    public ObjChiTietDDH()
    {
    }
    public ObjChiTietDDH(String MaDDH, String MaSP, int SoLuong)
    {
        this.MaDDH = MaDDH;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
    }
    public ObjChiTietDDH(String MaDDH, String MaSP, int SoLuong, String MaNCC, String TenNCC, Date NgayDatHang, String TrangThai)
    {
        this.MaDDH = MaDDH;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.NgayDatHang = NgayDatHang;
        this.TrangThai = TrangThai;
    }
    public String getMaDDH()
    {
        return MaDDH;
    }
    public String getMaSP()
    {
        return MaSP;
    }
    public int getSoLuong()
    {
        return SoLuong;
    }
    public String getMaNCC()
    {
        return MaNCC;
    }
    public String getTenNCC()
    {
        return TenNCC;
    }
    public String getTrangThai()
    {
        return TrangThai;
    }
    public Date getNgayDatHang()
    {
        return NgayDatHang;
    }
            
    public void setMaDDH(String MaDDH)
    {
        this.MaDDH = MaDDH;
    }
    public void setMaSP(String MaSP)
    {
        this.MaDDH = MaSP;
    }
    public void setSoLuong(int SoLuong)
    {
        this.SoLuong = SoLuong;
    }
}

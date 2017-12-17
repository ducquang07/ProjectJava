/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author Coldblood
 */
public class ObjChiTietDDH {
    private String MaDDH;
    private String MaSP;
    private int SoLuong;
    
    public ObjChiTietDDH()
    {
    }
    public ObjChiTietDDH(String MaDDH, String MaSP, int SoLuong)
    {
        this.MaDDH = MaDDH;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
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

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
public class ObjDonDatHang {
    
    private String MaDDH;
    private String MaNCC;
    private Date NgayDatHang;
    private String TrangThai; 

    //Constructor
    public ObjDonDatHang()
    {
    }

    public ObjDonDatHang(String MaDDH, String MaNCC, Date NgayDatHang, String TrangThai)
    {
        this.MaDDH = MaDDH;
        this.MaNCC = MaNCC;
        this.NgayDatHang = NgayDatHang;
        this.TrangThai = TrangThai;
    }
    
    //Get
    
    public String getMaDDH()
    {
        return MaDDH;
    }
    public String getMaNCC()
    {
        return MaNCC;
    }
    public Date getNgayDatHang()
    {
        return NgayDatHang;
    }
    public String getTrangThai()
    {
        return TrangThai;
    }
    
    //Set
    
    public void setMaDDH(String MaDDH)
    {
        this.MaDDH = MaDDH;
    }
    public void setMaNCC(String MaNCC)
    {
        this.MaNCC = MaNCC;
    }
    public void setNgayDatHang(Date NgayDatHang)
    {
        this.NgayDatHang = NgayDatHang;
    }
    public void setTrangThai(String TrangThai)
    {
        this.TrangThai = TrangThai;
    }
}

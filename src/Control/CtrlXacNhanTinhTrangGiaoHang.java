/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;

/**
 *
 * @author DucQuang
 */
public class CtrlXacNhanTinhTrangGiaoHang {
    Connect DB=new Connect();
    public ResultSet LayDSHoaDonChuaGiao(){
        String SQL="Select HDS.SoHDS, HDS.MaKH, HDS.NgayLap, HDS.NgayGiaoDuKien, HDS.TongTien,HDS.TinhTrangGiaoHang, KH.TenKH, KH.DiaChi, KH.SDT from HOADONSI HDS, KHACHHANG KH where HDS.MaKH=KH.MaKH and HDS.TinhTrangGiaoHang=N'Chưa giao'";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayDSHoaDonDaGiao(){
        String SQL="Select HDS.SoHDS, HDS.MaKH, HDS.NgayLap, HDS.NgayGiaoDuKien, HDS.TongTien,HDS.TinhTrangGiaoHang, KH.TenKH, KH.DiaChi, KH.SDT from HOADONSI HDS, KHACHHANG KH where HDS.MaKH=KH.MaKH and HDS.TinhTrangGiaoHang=N'Đã giao'";
        return DB.GetData(SQL);
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

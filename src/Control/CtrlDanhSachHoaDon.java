/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModHoaDonLe;
import Model.ModHoaDonSi;
import java.sql.ResultSet;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlDanhSachHoaDon {
    Connect DB = new Connect();
    public ResultSet LayDanhSachHoaDonLe(){
        ModHoaDonLe modHDL = new ModHoaDonLe();
        return modHDL.GetALL();
    }
    
    public ResultSet LayDanhSachHoaDonSi(){
        String SQL="Select HDS.*,KH.TenKH,KH.DiaChi,KH.SDT from KHACHHANG KH,HOADONSI HDS where KH.MaKH=HDS.MaKH";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayThongTinChiTietHoaDonLe(){
        return null;    
    }
}

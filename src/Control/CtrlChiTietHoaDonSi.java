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
public class CtrlChiTietHoaDonSi {
    Connect DB=new Connect();
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    
    public ResultSet LayDSCTHD(String SoHD){
        String SQL="Select CT.SoHDS, CT.MaSP, SP.TenSP, SP.DVT, CT.SoLuong, CT.DonGia, CT.ThanhTien from CTHDS CT, SANPHAM SP where CT.MaSP=SP.MaSP and CT.SoHDS='"+SoHD+"';";
        return DB.GetData(SQL);
    }
}

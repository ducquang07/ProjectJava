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
public class CtrlLapPhieuNhapHang {
    Connect DB=new Connect();
    public ResultSet LayDSPhieuNhap(){
        String SQL="Select MaPN, NgayNhap from PHIEUNHAP";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayCTPN(){
        String SQL="Select MaPN, MaSP, SoLuong, DonGia from CTPN";
        return DB.GetData(SQL);
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

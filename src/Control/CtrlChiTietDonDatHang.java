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
 * @author Coldblood
 */
public class CtrlChiTietDonDatHang {
     Connect DB=new Connect();
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    public ResultSet LayDSCTDDH(){
        String SQL="Select CTDH.MaDDH, CTDH.MaSP,CTDH.SoLuong, DDH.NgayDatHang, DDH.TrangThai, DDH.MaNCC, NCC.TenNCC from CTDDH CTDH, DONDATHANG DDH, NHACUNGCAP NCC WHERE CTDH.MaDDH = DDH.MaDDH and NCC.MaNCC = DDH.MaNCC";
        return DB.GetData(SQL);
    }
}


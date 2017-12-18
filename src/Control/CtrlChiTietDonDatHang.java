/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Coldblood
 */
public class CtrlChiTietDonDatHang {
     Connect DB=new Connect();
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    public String LayMaDDH(){
        String ID="DH001";
        ResultSet rs=null;
        String mySQL="Select * from DONDATHANG order by MaDDH DESC limit 1";
        try{
            rs=DB.GetData(mySQL);
            if(rs.next()){
                ID=rs.getString("MaDDH");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="DH00"+STT;
                else if(STT<100) ID="DH0"+STT;
                else ID="DH"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlQuanLiDonDatHang.LayMaDDH:"+ex.getMessage());
        }
        return ID;
    }
    public ResultSet LayDSCTDDH(){
        String SQL="Select CTDH.MaDDH, CTDH.MaSP,CTDH.SoLuong, DDH.NgayDatHang, DDH.TrangThai, DDH.MaNCC, NCC.TenNCC from CTDDH CTDH, DONDATHANG DDH, NHACUNGCAP NCC WHERE CTDH.MaDDH = DDH.MaDDH and NCC.MaNCC = DDH.MaNCC";
        return DB.GetData(SQL);
    }
}


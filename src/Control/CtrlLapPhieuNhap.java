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
 * @author DucQuang
 */
public class CtrlLapPhieuNhap {
    Connect DB=new Connect();
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    public ResultSet LayDSDDH(){
        String SQL="Select DDH.MaDDH, DDH.MaNCC, NCC.TenNCC, DDH.NgayDatHang, DDH.TrangThai from DONDATHANG DDH, NHACUNGCAP NCC where DDH.MaNCC=NCC.MaNCC and TrangThai=N'Da dat'";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayDSDDH(String MaPN){
        String SQL="Select PN.MaPN, DDH.MaDDH, DDH.MaNCC, DDH.NgayDatHang, DDH.TrangThai from DONDATHANG DDH, PHIEUNHAP PN where DDH.MaDDH=PN.MaDDH and PN.MaPN='"+MaPN+"'";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayDSSP(String MaDDH){
        String SQL="Select CT.MaDDH, CT.MaSP, SP.TenSP, CT.SoLuong from CTDDH CT, SANPHAM SP where CT.MaSP=SP.MaSP and CT.MaDDH='"+MaDDH+"'";
        return DB.GetData(SQL);
    }
    
    public String LayMaPN(){
        String ID="PN0001";
        ResultSet rs=null;
        String mySQL="Select * from PHIEUNHAP order by MaPN DESC limit 1";
        try{
            rs=DB.GetData(mySQL);
            if(rs.next()){
                ID=rs.getString("MaPN");
                int STT = Integer.parseInt(ID.substring(4));
                STT+=1;
                if(STT<10) ID="PN000"+STT;
                else if(STT<100) ID="PN00"+STT;
                else if(STT<1000) ID="PN0"+STT;
                else ID="PN"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlLapPhieuNhap.LayMaPN:"+ex.getMessage());
        }
        return ID;
    }
}

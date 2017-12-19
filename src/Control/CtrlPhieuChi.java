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
 * @author Thanh
 */
public class CtrlPhieuChi {
    Connect DB=new Connect();
    public ResultSet LayDanhSachNCC(){
        String sql="Select * from NHACUNGCAP";
        return DB.GetData(sql);
    }
     public ResultSet LayDanhSachPC(){
         String sql="Select * from PHIEUCHI";
         return DB.GetData(sql);
     }
     
     public ResultSet SearchPhieuChiByID(String MaPC, String MaNCC){
         String sql="Select PC.MaPC, PC.LyDoChi, PC.TongTienChi, PC.NgayChi, PC.MaNCC, NCC.TenNCC"
                 +" from PHIEUCHI PC, NHACUNGCAP NCC WHERE PC.MaNCC=NCC.MaNCC and PC.MaPC like '%"+MaPC+"%'";
         return DB.GetData(sql);
     }
     
     public ResultSet SearchPhieuChiByNameNCC(String MaPC, String TenNCC){
         String sql="Select PC.MaPC, PC.LyDoChi, PC.TongTienChi, PC.NgayChi, PC.MaNCC, NCC.TenNCC"
                 +" from PHIEUCHI PC, NHACUNGCAP NCC WHERE PC.MaNCC=NCC.MaNCC and NCC.TenNCC like '%"+TenNCC+"%'";
         return DB.GetData(sql);
     }
     
     public String TaoMaPC(){
        String ID="PC0001";
        ResultSet rs=null;
        String SQL="Select * from PHIEUCHI order by MaPC DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("MaPC");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="PC000"+STT;
                else if(STT<100) ID="PC00"+STT;
                else ID="PC0"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlPhieuChi.TaoMaPC:"+ex.getMessage());
        }
        return ID;
    }
     
    public boolean  CloseConnection(){
        return DB.CloseDB();
    }
}

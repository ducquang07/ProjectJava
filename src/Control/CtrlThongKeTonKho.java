/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModKyTonKho;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlThongKeTonKho {
    Connect DB =new Connect();
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
    public ResultSet LayDSTonKho(Date tungay,Date denngay){
        String SQL="call closer_quanlicuahangson.Proc_TinhTonKho('"+dt.format(tungay)+"','"+dt.format(denngay)+"')";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayDSKyTonKho(){
        ModKyTonKho modKTK = new ModKyTonKho();
        return modKTK.GetALL();
    }
    
    public ResultSet LayDanhSachTonKho(String MaKTK){
        String SQL = "Select CT.*,SP.TenSP,NCC.TenNCC from CTKTK CT,SanPham SP, NhaCungCap NCC where CT.MasP=SP.MaSP and SP.MaNCC=NCC.MaNCC and CT.MaKTK='"+MaKTK+"'";
        return DB.GetData(SQL);
    }
    
    public String LayMaKTK(){
        String ID="KTK0001";
        ResultSet rs=null;
        String SQL="Select * from KyTonKho order by MaKTK DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("MaKTK");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="KTK000"+STT;
                else if(STT<100) ID="KTK00"+STT;
                else ID="KTK0"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlThongKeTonKho.LayMaKTK:"+ex.getMessage());
        }
        finally{
            DB.CloseDB();
        }
        return ID;
    }
    
//    public static void main(String args[]) {
//        CtrlThongKeTonKho ctrl = new CtrlThongKeTonKho();
//        ResultSet rs= ctrl.LayDSTonKho("2018/1/1","2018/1/14");
//        try {
//            while(rs.next())
//                System.out.println(rs.getString("MaSP"));
//        } catch (SQLException ex) {
//            Logger.getLogger(CtrlThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}

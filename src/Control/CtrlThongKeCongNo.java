/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModCongNo;
import Model.ModKyCongNo;
import com.mysql.jdbc.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlThongKeCongNo {
    Connect DB =new Connect();
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
    public ResultSet LayDSCongNo(Date tungay,Date denngay){
        String SQL="call closer_quanlicuahangson.Proc_TinhCongNo('"+dt.format(tungay)+"','"+dt.format(denngay)+"')";
        return DB.GetData(SQL);
    }
    
    public ResultSet LayDanhSachKCN(){
        ModKyCongNo modCN = new ModKyCongNo();
        return modCN.GetALL();
    }
    
    public ResultSet LayDSCongNo(String MaKCN){
        String SQL = "Select CN.*,KH.TenKH from CongNo CN,KhachHang KH where CN.MaKH=KH.MaKH and MaKCN='"+MaKCN+"'";
        return DB.GetData(SQL);
    }
    
    public String LayMaKCN(){
        String ID="KCN0001";
        ResultSet rs=null;
        String SQL="Select * from KyCongNo order by MaKCN DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("MaKCN");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="KCN000"+STT;
                else if(STT<100) ID="KCN00"+STT;
                else ID="KCN0"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlThongKeCongNo.LayMaKCN:"+ex.getMessage());
        }
        finally{
            DB.CloseDB();
        }
        return ID;
    }
    
    public static void main(String args[]) {
        CtrlThongKeCongNo ctrl = new CtrlThongKeCongNo();
        ResultSet rs= ctrl.LayDSCongNo("KCN0001");
        try {
            while(rs.next())
                System.out.println(rs.getString("TenKH"));
        } catch (SQLException ex) {
            Logger.getLogger(CtrlThongKeCongNo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

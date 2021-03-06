/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Thanh
 */
public class CtrlPhieuThu {
    Connect DB=new Connect();
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    public ResultSet LayDanhSachPhieuThu(){
        String SQL="Select PT.MaPT,PT.LyDoThu,PT.TongTienThu,PT.NgayThu,KH.TenKH, KH.DiaChi,KH.SDT, KH.MaKH from PHIEUTHU PT, KHACHHANG KH where PT.MaKH=KH.MaKH;";
        return DB.GetData(SQL);           
    }
    public ResultSet LayDanhSachKhachHang(){
        String SQL="Select * from KHACHHANG";
        return DB.GetData(SQL);
    }
    public ResultSet SearchPhieuThu(String TenKH,String LyDoThu, String MaPT){
        String SQL="Select PT.MaPT,PT.LyDoThu,PT.TongTienThu,PT.NgayThu,KH.TenKH from PHIEUTHU PT, KHACHHANG KH where PT.MaKH=KH.MaKH "
                  +"and KH.TenKH like '%"+TenKH+"%'";
        if(!LyDoThu.equals("")) SQL+= " and PT.LyDoThu like '%"+LyDoThu+"%'";
        if(!MaPT.equals("")) SQL+=" and PT.MaPT like '%"+MaPT+"%'";
        return DB.GetData(SQL);
    }
    public ResultSet Search(Date tungay, Date denngay, String TenKH){
        String sql="SELECT * FROM PHIEUTHU,KHACHHANG WHERE PHIEUTHU.MaKH=KHACHHANG.MaKH AND TenKH like '%"+TenKH+"%' AND PHIEUTHU.NgayThu BETWEEN '"+dt.format(tungay)+"' and '"+dt.format(denngay)+"'";
        return DB.GetData(sql);
    }
    
    public ResultSet SearchTheoNgay(Date tungay, Date denngay){
        String sql="SELECT * FROM PHIEUTHU,KHACHHANG WHERE PHIEUTHU.MaKH=KHACHHANG.MaKH AND PHIEUTHU.NgayThu BETWEEN '"+dt.format(tungay)+"' and '"+dt.format(denngay)+"'";
        return DB.GetData(sql);
    }
    public ResultSet SearchTheoText(String TenKH, String MaPT, String LyDoThu){
        String sql="SELECT * FROM PHIEUTHU,KHACHHANG WHERE PHIEUTHU.MaKH=KHACHHANG.MaKH AND TenKH like '%"+TenKH+"%' AND MaPT like '%"+MaPT+"%' AND LyDoThu like '%"+LyDoThu+"%'";
        return DB.GetData(sql);
    }
    
    
    public String TaoMaPT(){
        String ID="PT0001";
        ResultSet rs=null;
        String SQL="Select * from PHIEUTHU order by MaPT DESC limit 1";
        try{
            rs=DB.GetData(SQL);
            if(rs.next()){
                ID=rs.getString("MaPT");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="PT000"+STT;
                else if(STT<100) ID="PT00"+STT;
                else ID="PT0"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlPhieuThu.TaoMaPT:"+ex.getMessage());
        }
        return ID;
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

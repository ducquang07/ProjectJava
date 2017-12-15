/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjHoaDonSi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ThaiNguyen
 */
public class ModHoaDonSi extends Model{
    private PreparedStatement pstmt;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
    public ModHoaDonSi() {
        DB=new Connect();
        Table="HOADONSI";
        ID="SoHDS";
    }

    public ResultSet GetALL(){
        return super.GetALL();
    }
    
    public ResultSet GetByID(String ID){
        return super.GetByID(ID);
    }
    
    public ResultSet SearchByID(String ID){
        return super.SearchByID(ID);
    }

    public boolean Insert(ObjHoaDonSi TbHDS) {
        String SQL="INSERT INTO HOADONSI (SoHDS,MaKH,NgayLap,NgayGiaoDuKien,TongTien,TinhTrangGiaoHang) VALUES (?, ?, ?, ?, ?, ?);";
        System.out.println("INSERT INTO closer2_quanlicuahangson.HOADONSI (SoHDS,MaKH,NgayLap,NgayGiaoDuKien,TongTien,TinhTrangGiaoHang) VALUES ('"+TbHDS.getSoHDS()+"','"+TbHDS.getMaKH()+"','"+TbHDS.getNgayLap()+"','"+TbHDS.getNgayGiaoDuKien()+"','"+TbHDS.getTongTien()+"','"+TbHDS.getTinhTrangGiaoHang()+"')");
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbHDS.getSoHDS());
                pstmt.setString(2,TbHDS.getMaKH());
                pstmt.setString(3,dt.format(TbHDS.getNgayLap()));
                pstmt.setString(4,dt.format(TbHDS.getNgayGiaoDuKien()));
                pstmt.setString(5,Integer.toString(TbHDS.getTongTien()));
                pstmt.setString(6,TbHDS.getTinhTrangGiaoHang());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModHoaDonSi.Insert:"+ex.getMessage());
            return false;
        }       
        return true;
    }


    public boolean Update(ObjHoaDonSi TbHDS) {
        String SQL="Update HOADONSi set MaKH='"+TbHDS.getMaKH()+
                                              "',NgayLap='"+dt.format(TbHDS.getNgayLap())+
                                              "',NgayGiaoDuKien='"+dt.format(TbHDS.getNgayGiaoDuKien())+
                                              "',TongTien='"+TbHDS.getTongTien()+
                                              "',TinhTrangGiaoHang='"+TbHDS.getTinhTrangGiaoHang()+
                                              "' where SoHDL='"+TbHDS.getSoHDS()+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModHoaDonSi.Update:"+ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean Delete(String SoHDS){
        return super.Delete(SoHDS);
    }
}

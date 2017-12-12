/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjHoaDonLe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ThaiNguyen
 */
public class ModHoaDonLe extends Model{

    private PreparedStatement pstmt;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
    public ModHoaDonLe() {
        DB=new Connect();
        Table="HOADONLE";
        ID="SoHDL";
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

    public boolean Insert(ObjHoaDonLe TbHDL) {
        String SQL="INSERT INTO HOADONLE (SoHDL,TenKH,NgayLap,TongTien) VALUES (?, ?, ?, ?);";
        System.out.println("INSERT INTO HOADONLE (SoHDL,TenKH,NgayLap,TongTien) VALUES ('"+TbHDL.getSoHDL()+"','"+TbHDL.getTenKH()+"','"+dt.format(TbHDL.getNgayLap())+"','"+TbHDL.getTongTien()+"'");
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbHDL.getSoHDL());
                pstmt.setString(2,TbHDL.getTenKH());
                pstmt.setString(3,dt.format(TbHDL.getNgayLap()));
                pstmt.setString(4,Integer.toString(TbHDL.getTongTien()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModHoaDonLe.Insert:"+ex.getMessage());
            return false;
        }       
        return true;
    }


    public boolean Update(ObjHoaDonLe TbHDL) {
        String SQL="Update HOADONLE set TenKH='"+TbHDL.getTenKH()+
                                              "',NgayLap='"+dt.format(TbHDL.getNgayLap())+
                                              "',TongTien='"+TbHDL.getTongTien()+
                                              "' where SoHDL='"+TbHDL.getSoHDL()+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModHoaDonLe.Update:"+ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean Delete(String SoHDL){
        return super.Delete(SoHDL);
    }
}

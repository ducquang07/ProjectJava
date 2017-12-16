/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjDonDatHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Coldblood
 */
public class ModDonDatHang extends Model{
    
    private PreparedStatement pstmt;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
    public ModDonDatHang() {
        DB=new Connect();
        Table="DONDATHANG";
        ID="MaDDH";
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
    
    public boolean Insert(ObjDonDatHang TbDDH) {
        String SQL="INSERT INTO DONDATHANG (MaDDH,MaNCC,NgayDatHang,TrangThai) VALUES (?, ?, ?, ?);";
        System.out.println("INSERT INTO DONDATHANG (MaDDH,MaNCC,NgayDatHang,TrangThai) VALUES ('"+TbDDH.getMaDDH()+"','"+TbDDH.getMaNCC()+"','"+dt.format(TbDDH.getNgayDatHang())+"','"+TbDDH.getTrangThai()+"'");
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbDDH.getMaDDH());
                pstmt.setString(2,TbDDH.getMaNCC());
                pstmt.setString(3,dt.format(TbDDH.getNgayDatHang()));
                pstmt.setString(4,(TbDDH.getTrangThai()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModDonDatHang.Insert:"+ex.getMessage());
            return false;
        }       
        return true;
    }

    public boolean Update(ObjDonDatHang TbDDH) {
        String SQL="Update DONDATHANG set MaNNC='"+TbDDH.getMaNCC()+
                                              "',NgayDatHang='"+dt.format(TbDDH.getNgayDatHang())+
                                              "',TrangThai='"+TbDDH.getTrangThai()+
                                              "' where MaDDH='"+TbDDH.getMaDDH()+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModDonDatHang.Update:"+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean Delete(String MaDDH){
        return super.Delete(MaDDH);
    }
}


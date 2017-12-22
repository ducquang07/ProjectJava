/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjPhieuThu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Thanh
 */
public class ModPhieuThu extends Model{
    private PreparedStatement pstmt;
    SimpleDateFormat dt=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
    public ModPhieuThu(){
        DB=new Connect();
        Table="PHIEUTHU";
        ID="MaPT";
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
    
    public boolean Insert(ObjPhieuThu TbPT){
        String SQL="INSERT INTO PHIEUTHU (MaPT,LyDoThu,TongTienThu,NgayThu,MaKH) VALUES (?, ?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1, TbPT.getMaPT());
                pstmt.setString(2, TbPT.getLyDoThu());
                pstmt.setString(3, Integer.toString(TbPT.getTongTienThu()));
                pstmt.setString(4, dt.format(TbPT.getNgayThu()));
                pstmt.setString(5, TbPT.getMaKH());
                pstmt.executeUpdate();
            }
        }catch(SQLException ex){
            System.out.println("Ngoại lệ tại ModPhieuThu.Insert:"+ex.getMessage());
            return false;
        }
        finally{
            DB.CloseDB();
        }
        return true;
    }

    
    public boolean Update(ObjPhieuThu TbPT){
        String SQL="UPDATE PHIEUTHU set MaPT='"+TbPT.getMaPT()+
                                            "',LyDoThu='"+TbPT.getLyDoThu()+
                                            "',TongTienThu='"+Integer.toString(TbPT.getTongTienThu())+
                                            "',NgayThu='"+dt.format(TbPT.getNgayThu())+
                                            "',MaKH='"+TbPT.getMaKH()+
                                            "' where MaPT='"+TbPT.getMaPT()+"'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.executeUpdate();
            }
        }catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModHoaDonSi.Update:"+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }
        return true;
    }
}

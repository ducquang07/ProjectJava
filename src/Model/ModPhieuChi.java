/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjPhieuChi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Thanh
 */
public class ModPhieuChi extends Model{
    private PreparedStatement pstmt;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
    public ModPhieuChi(){
        DB=new Connect();
        Table="PHIEUCHI";
        ID="MaPC";
    }
    @Override
    public ResultSet GetALL(){
        return super.GetALL();
    }
    public ResultSet GetByID(String ID){
        return super.GetByID(ID);
    }
    @Override
    public ResultSet SearchByID(String ID){
        return super.SearchByID(ID);
    }
    
    public boolean Insert(ObjPhieuChi TbPC){
        String sql="INSERT INTO PHIEUCHI (MaPC, LyDoChi, TongTienChi, NgayChi, MaNCC) VALUES(?, ?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(sql);
                pstmt.setString(1,TbPC.getMaPC());
                pstmt.setString(2,TbPC.getLyDoChi());
                pstmt.setString(3,TbPC.getTongTienChi());
                pstmt.setString(4,dt.format(TbPC.getNgayChi()));
                
                pstmt.setString(6,TbPC.getMaNCC());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModPhieuChi.Insert:"+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }       
        return true;
    }
    
    public boolean Update(ObjPhieuChi TbPC){
        String sql="Update PHIEUCHI set MaPC='"+TbPC.getMaPC()+
                                            "',LyDoChi='"+TbPC.getLyDoChi()+
                                            "',TongTienChi='"+TbPC.getTongTienChi()+
                                            "',NgayChi='"+dt.format(TbPC.getNgayChi())+
                                            "',MaNCC='"+TbPC.getMaNCC()+
                                            "' where MaPC='"+TbPC.getMaPC()+ "'";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(sql);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModPhieuChi.Update:"+ex.getMessage());
            return false;
        }finally{
            DB.CloseDB();
        }
        return true;
    }
    @Override
    public boolean Delete(String SoHDS){
        return super.Delete(SoHDS);
    }
}

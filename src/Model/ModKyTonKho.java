/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connect.Connect;
import Object.ObjKyTonKho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ThaiNguyen
 */
public class ModKyTonKho extends Model{
    private PreparedStatement pstmt;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
     public ModKyTonKho() {
        DB=new Connect();
        Table="KYTONKHO";
        ID="MaKTK";
     }
     
    public ResultSet GetALL(){
        return super.GetALL();
    }
    
    public boolean Insert(ObjKyTonKho TbKyTonKho) {
        String SQL="INSERT INTO KyTonKho (MaKTK,TuNgay,DenNgay) VALUES (?, ?, ?);";   
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbKyTonKho.getMaKTK());
                pstmt.setString(2,dt.format(TbKyTonKho.getTuNgay()));
                pstmt.setString(3,dt.format(TbKyTonKho.getDenNgay()));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModKyTonKho.Insert:"+ex.getMessage());
            return false;
        }       
        return true;
    }
    
    public boolean Delete(String MaKTK){
        return super.Delete(MaKTK);
    }
}

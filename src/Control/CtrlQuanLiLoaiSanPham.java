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
 * @author DucQuang
 */
public class CtrlQuanLiLoaiSanPham {
    Connect DB=new Connect();
    public ResultSet LayDSLoaiSanPham(){
        String SQL="Select MaloaiSP, TenLoaiSP from LOAISANPHAM";
        return DB.GetData(SQL);
    }
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    public String LayMaLSP(){
        String ID="LSP001";
        ResultSet rs=null;
        String mySQL="Select * from LOAISANPHAM order by MaLoaiSP DESC limit 1";
        try{
            rs=DB.GetData(mySQL);
            if(rs.next()){
                ID=rs.getString("MaLoaiSP");
                int STT = Integer.parseInt(ID.substring(3));
                STT+=1;
                if(STT<10) ID="LSP00"+STT;
                else if(STT<100) ID="LSP0"+STT;
                else ID="LSP"+STT;
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlQuanLiLoaiSanPham.LayMaLoaiSP:"+ex.getMessage());
        }
        return ID;
    }
    
    public ResultSet SearchLoaiSPByID(String MaLSP){
        String mySQL="Select * from LOAISANPHAM where MaLoaiSP like '%"+MaLSP+"%'";
        return DB.GetData(mySQL);
    }
    
    public ResultSet SearchLoaiSPByName(String TenLSP){
        String mySQL="Select * from LOAISANPHAM where TenLoaiSP like '%"+TenLSP+"%'";
        return DB.GetData(mySQL);
    }
}

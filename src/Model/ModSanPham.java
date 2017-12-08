/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Connect.Connect;
import Object.ObjSanPham;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author ThaiNguyen
 */
public class ModSanPham extends Model{

    private ObjSanPham TbSanPham;
    private PreparedStatement pstmt;
    
    public ModSanPham() {
        DB=new Connect();
        Table="SanPham";
        ID="MaSP";
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
    
    
    @Override
    public boolean Insert() {
        String SQL="INSERT INTO SANPHAM (MaSP,TenSP,MaLoaiSP,Giale,GiaSi,DVT,SoLuong,MaNCC,LoiNhuanBien,MoTa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            if(DB.Connected()){
                pstmt=DB.getConDB().prepareStatement(SQL);
                pstmt.setString(1,TbSanPham.getMaSP());
                pstmt.setString(2,TbSanPham.getTenSP());
                pstmt.setString(3,TbSanPham.getMaLoaiSP());
                pstmt.setString(4,Integer.toString(TbSanPham.getGiaLe()));
                pstmt.setString(5, Integer.toString(TbSanPham.getGiaSi()));
                pstmt.setString(6, TbSanPham.getDVT());
                pstmt.setString(7, Integer.toString(TbSanPham.getSoLuong()));
                pstmt.setString(8, TbSanPham.getMaNCC());
                pstmt.setString(9, Double.toString(TbSanPham.getLoiNhuanBien()));
                pstmt.setString(10, TbSanPham.getMoTa());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModSanPham.Insert: "+ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean Update() {
        String SQL="Update SANPHAM set TenSP=N'" + TbSanPham.getTenSP()+ 
                                    "',Giale='" + TbSanPham.getGiaLe() +
                                    "',GiaSi='" + TbSanPham.getGiaSi() + 
                                    "',DVT=N'" + TbSanPham.getDVT() + 
                                    "',MoTa=N'" +TbSanPham.getMoTa() + 
                                    "',MaNCC='"+TbSanPham.getMaNCC()+
                                    "',MaLoaiSP='"+TbSanPham.getMaLoaiSP()+
                                    "',SoLuong='" + TbSanPham.getSoLuong()+
                                    "',LoiNhuanBien='" + TbSanPham.getLoiNhuanBien() + 
       "' where MaSP='"+ TbSanPham.getMaSP()+"';";
       try{
           if(DB.Connected()){
               stmDB=(Statement) DB.getConDB().createStatement();
               stmDB.executeUpdate(SQL);
           }
       } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại ModSamPham.Update: "+ex.getMessage());
        }
        return false;
    }
    
}

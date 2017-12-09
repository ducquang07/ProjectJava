/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Connect.Connect;
import java.sql.ResultSet;


/**
 *
 * @author ThaiNguyen
 */
public class CtrlLapHoaDonLe {
    Connect DB=new Connect();
    public ResultSet LayDanhSachSanPham(){
        String SQL="Select MaSP,TenSP,DVT,GiaLe,SoLuong,MaLoaiSP,MaNCC from SANPHAM";
        return DB.GetData(SQL);
    }
    
}

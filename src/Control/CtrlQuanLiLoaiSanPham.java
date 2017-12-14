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
 * @author DucQuang
 */
public class CtrlQuanLiLoaiSanPham {
    Connect DB=new Connect();
    public ResultSet LayDSLoaiSanPham(){
        String SQL="Select MaloaiSP, TenLoaiSP " + "from LOAISANPHAM";
        return DB.GetData(SQL);
    }
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

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
 * @author Coldblood
 */
public class CtrlChiTietDonDatHang {
     Connect DB=new Connect();
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
    public ResultSet LayDSCTDDH(){
        String SQL="Select MaDDH,MaSP,SoLuong from CTDDH";
        return DB.GetData(SQL);
    }
}


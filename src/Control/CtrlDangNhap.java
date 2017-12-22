/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlDangNhap {
    Connect DB = new Connect();
    
    public boolean KiemTraDangNhap(String User,String Pass){
        String SQL="Select * from TAIKHOAN where TenDangNhap='"+User+"' and MatKhau='"+Pass+"'";
        ResultSet rs= DB.GetData(SQL);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        finally{
            DB.CloseDB();
        }
        return false;
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import java.sql.ResultSet;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlMainForm {
    Connect DB = new Connect();
    
    public ResultSet LayThongTinUser(String User,String Pass){
        String SQL="Select * from TAIKHOAN where TenDangNhap='"+User+"' and MatKhau='"+Pass+"'";
        return DB.GetData(SQL);
    }
    
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

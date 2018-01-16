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
public class CtrlFormThietLapMucTonKho {
    Connect DB=new Connect();
    
    public String LayMucTonKho(){
        String SQL="Select * from ThamSo";
        ResultSet rs = DB.GetData(SQL);
        try {
            if(rs.next())
                return  rs.getString("SoLuongToiThieu");
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại CtrlFormThietLapMucTonKho :"+ex.getMessage());
            return "";
        }
        return "";
    }
}

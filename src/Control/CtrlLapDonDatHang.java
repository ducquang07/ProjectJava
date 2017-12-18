/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModNhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Coldblood
 */
public class CtrlLapDonDatHang {
    Connect DB = new Connect();
    
    public ResultSet LayDanhSachNhaCungCap(){
        ModNhaCungCap modNCC=new ModNhaCungCap();
        return modNCC.GetALL();
    }
}

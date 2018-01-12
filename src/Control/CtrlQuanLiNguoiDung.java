/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;
import Model.ModTaiKhoan;
import java.sql.ResultSet;

/**
 *
 * @author ThaiNguyen
 */
public class CtrlQuanLiNguoiDung {
    Connect DB=new Connect();
    
    public ResultSet LayDanhSachNguoiDung(){
        ModTaiKhoan modTK = new ModTaiKhoan();
        return modTK.GetALL();
    }
}

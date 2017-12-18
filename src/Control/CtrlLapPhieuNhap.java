/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Connect.Connect;

/**
 *
 * @author DucQuang
 */
public class CtrlLapPhieuNhap {
    Connect DB=new Connect();
    public boolean CloseConnection(){
        return DB.CloseDB();
    }
}

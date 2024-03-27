/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class SanPham {
    int masp;
    String tensp;
    String trangthai;

    public SanPham() {
    }

    public SanPham(int masp, String tensp, String trangthai) {
        this.masp = masp;
        this.tensp = tensp;
        this.trangthai = trangthai;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    
    public Object[] toDataRow(){
        return new Object[]{
            this.getMasp(),this.getTensp(),this.getTrangthai()
        };
    }
    
    
}

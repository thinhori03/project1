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
   

    public SanPham() {
    }

    public SanPham(int masp, String tensp) {
        this.masp = masp;
        this.tensp = tensp;
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
    public Object[] toDataRow(){
        return new Object[]{
            this.getMasp(),this.getTensp()
        };
    }
    
    
}

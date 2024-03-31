/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;

import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataTable;

@DataTable(name ="SAN_PHAM")
public class SanPhamModel {
    @DataField(name ="MASP")
    int masp;
    @DataField(name="TENSP")
    String tensp;
    @DataField(name="TRANGTHAI")
    String trangthai;

    public SanPhamModel() {
    }
    
    public SanPhamModel(int masp, String tensp, String trangthai) {
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

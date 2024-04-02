/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;

import java.util.Date;

/**
 *
 * @author acer
 */
public class KhachHangModel {
    private int maKH;    
    private String ten;
    private int  sdt;
    private String gioiTinh;
    private String email;
    private Date ngay;
    private String diaChi;
    
    public KhachHangModel() {
    }

    public KhachHangModel(String ten, int sdt, String gioiTinh, String email, Date ngay, String diaChi) {
        this.ten = ten;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.ngay = ngay;
        this.diaChi = diaChi;
    }

    public KhachHangModel(int maKH, String ten, int sdt, String gioiTinh, String email, Date ngay,String diaChi) {
        this.maKH = maKH;
        this.ten = ten;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.ngay = ngay;
        this.diaChi = diaChi;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", ten=" + ten + ", sdt=" + sdt + ", gioiTinh=" + gioiTinh + ", email=" + email + ", ngay=" + ngay + ", diaChi=" + diaChi + '}';
    }
    
}


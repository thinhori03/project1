/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;

/**
 *
 * @author acer
 */
public class ThongKeModel {
    private int ma;
    private String ten;
    private int sl;
    private String ngay;
    private Float gia;

    public ThongKeModel(int ma, String ten, int sl, String ngay, Float gia) {
        this.ma = ma;
        this.ten = ten;
        this.sl = sl;
        this.ngay = ngay;
        this.gia = gia;
    }

    public ThongKeModel(int ma, String ten, int sl,  Float gia) {
        this.ma = ma;
        this.ten = ten;
        this.sl = sl;
        this.gia = gia;
    }

    public ThongKeModel() {
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

   

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }
    
    public Float thanhTien(){
    return sl*gia;
    }
}

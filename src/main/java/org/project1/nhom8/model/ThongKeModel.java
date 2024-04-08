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
public class ThongKeModel {

     private int ma;
    private String ten;
    private int sl;
    private Date ngay;
    private Float gia;
    //
    private String tenSP;
    private String Mau;
    private String Size;
    private int slMua;

    public ThongKeModel(String tenSP, String Mau, String Size, int slMua) {
        this.tenSP = tenSP;
        this.Mau = Mau;
        this.Size = Size;
        this.slMua = slMua;
    }
    
    public ThongKeModel(int ma, String ten, int sl, Date ngay, Float gia) {
        this.ma = ma;
        this.ten = ten;
        this.sl = sl;
        this.ngay = ngay;
        this.gia = gia;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMau() {
        return Mau;
    }

    public void setMau(String Mau) {
        this.Mau = Mau;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public int getSlMua() {
        return slMua;
    }

    public void setSlMua(int slMua) {
        this.slMua = slMua;
    }

    public ThongKeModel(int ma, Date ngay) {
        this.ma = ma;
        this.ngay = ngay;
    }
    public ThongKeModel( Date ngay,int sl) {
        
        this.ngay = ngay;
        this.sl = sl;
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

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;


public class KhuyenMai {
    String makm;
    String ngaybd; 
    String ngaykt;
    int soluong;
    float gia;

    public KhuyenMai() {
    }

    public KhuyenMai(String makm, String ngaybd, String ngaykt, int soluong, float gia) {
        this.makm = makm;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.soluong = soluong;
        this.gia = gia;
    }

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
    public Object[] toDataRow(){
        return new Object[]{
          this.getMakm(),this.getNgaybd(),this.getNgaykt(),this.getSoluong(),this.getGia()
        };
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;


/**
 *
 * @author Admin
 */
public class KhuyenMai {
    String makm;
    String ngayBd;
    String ngayKt;
    int soluong;
    float gia;

    public KhuyenMai() {
    }

    public KhuyenMai(String makm, String ngayBd, String ngayKt, int soluong, float gia) {
        this.makm = makm;
        this.ngayBd = ngayBd;
        this.ngayKt = ngayKt;
        this.soluong = soluong;
        this.gia = gia;
    }

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public String getNgayBd() {
        return ngayBd;
    }

    public void setNgayBd(String ngayBd) {
        this.ngayBd = ngayBd;
    }

    public String getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(String ngayKt) {
        this.ngayKt = ngayKt;
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
          this.getMakm(),this.getNgayBd(),this.getNgayKt(),this.getSoluong(),this.getGia()
        };
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "makm=" + makm + ", ngayBd=" + ngayBd + ", ngayKt=" + ngayKt + ", soluong=" + soluong + ", gia=" + gia + '}';
    }
}

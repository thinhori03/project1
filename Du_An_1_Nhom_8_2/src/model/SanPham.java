/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class SanPham {
    int maspct;
    String ten;
    int soluong;
    int size;
    String masac;
    String makm;
    String trangthai;
    float gia;

    public SanPham() {
    }

    public SanPham(int maspct, String ten, int soluong, int size, String masac, String makm, String trangthai, float gia) {
        this.maspct = maspct;
        this.ten = ten;
        this.soluong = soluong;
        this.size = size;
        this.masac = masac;
        this.makm = makm;
        this.trangthai = trangthai;
        this.gia = gia;
    }

    public int getMaspct() {
        return maspct;
    }

    public void setMaspct(int maspct) {
        this.maspct = maspct;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMasac() {
        return masac;
    }

    public void setMasac(String masac) {
        this.masac = masac;
    }

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
    
    
    public Object[]toDataRow(){
        return new Object[]{
          this.getMaspct(),this.getTen(),this.getSoluong(),this.getSize(),this.getMasac(),this.getMakm(),this.getTrangthai(),this.getGia()
        };
    }

    @Override
    public String toString() {
        return "SanPham{" + "maspct=" + maspct + ", ten=" + ten + ", soluong=" + soluong + ", size=" + size + ", masac=" + masac + ", makm=" + makm + ", trangthai=" + trangthai + ", gia=" + gia + '}';
    }

   
    
}

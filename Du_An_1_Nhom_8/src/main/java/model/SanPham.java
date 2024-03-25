/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import service.GiaService;
import service.Mau_Service;
import service.SIZE_Service;

/**
 *
 * @author Admin
 */
public class SanPham {
    GiaService gs = new GiaService();
    SIZE_Service ss = new SIZE_Service();
    Mau_Service ms = new Mau_Service();
    int maspct;
    String ten;
    int soluong;
    int size;
    int masac;
    String makm;
    String trangthai;
    int id_gia;

    public SanPham() {
    }

    public SanPham(int maspct, String ten, int soluong, int size, int masac, String makm, String trangthai, int id_gia) {
        this.maspct = maspct;
        this.ten = ten;
        this.soluong = soluong;
        this.size = size;
        this.masac = masac;
        this.makm = makm;
        this.trangthai = trangthai;
        this.id_gia = id_gia;
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

    public int getMasac() {
        return masac;
    }

    public void setMasac(int masac) {
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

    public int getId_gia() {
        return id_gia;
    }

    public void setId_gia(int id_gia) {
        this.id_gia = id_gia;
    }

 
    public Object[]toDataRow(){
        return new Object[]{
          this.getMaspct(),this.getTen(),this.getSoluong(),this.ss.getBYID_Size(this.size).getTensize(),this.ms.getBYID_Mau(this.masac).getTenmau(),this.getMakm(),this.getTrangthai(),this.gs.getBYID_Gia(this.id_gia).getGia()
        };
    }

    @Override
    public String toString() {
        return "SanPham{" + "gs=" + gs + ", maspct=" + maspct + ", ten=" + ten + ", soluong=" + soluong + ", size=" + size + ", masac=" + masac + ", makm=" + makm + ", trangthai=" + trangthai + ", id_gia=" + id_gia + '}';
    }

    
    
 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import service.SanPham_Service;
import service.Mau_Service;
import service.SIZE_Service;

/**
 *
 * @author Admin
 */
public class SanPham_CT {

    SanPham_Service gs = new SanPham_Service();
    SIZE_Service ss = new SIZE_Service();
    Mau_Service ms = new Mau_Service();

    int maSP;
    String ten;
    int soluong;
    int size;
    int masac;
    String trangthai;
    float gia;

    public SanPham_CT() {
    }

    public SanPham_CT(int maSP, String ten, int soluong, int size, int masac, String trangthai, float gia) {
        this.maSP = maSP;
        this.ten = ten;
        this.soluong = soluong;
        this.size = size;
        this.masac = masac;
        this.trangthai = trangthai;
        this.gia = gia;
    }

    public SanPham_Service getGs() {
        return gs;
    }

    public void setGs(SanPham_Service gs) {
        this.gs = gs;
    }

    public SIZE_Service getSs() {
        return ss;
    }

    public void setSs(SIZE_Service ss) {
        this.ss = ss;
    }

    public Mau_Service getMs() {
        return ms;
    }

    public void setMs(Mau_Service ms) {
        this.ms = ms;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
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

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaSP(), this.getTen(), this.getSoluong(), ss.getBYID_Size(this.getSize()).getTensize(), ms.getBYID_Mau(this.getMasac()).getTenmau(), this.getTrangthai(), this.getGia()
        };
    }

    @Override
    public String toString() {
        return "SanPham{" + "gs=" + gs + ", ss=" + ss + ", ms=" + ms + ", maSP=" + maSP + ", ten=" + ten + ", soluong=" + soluong + ", size=" + size + ", masac=" + masac + ", trangthai=" + trangthai + ", gia=" + gia + '}';
    }

}

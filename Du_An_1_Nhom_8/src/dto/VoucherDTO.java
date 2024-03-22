/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author ngtnthori03
 */
public class VoucherDTO {
    
    private String maVoucher;

    private float giaTri;

    private int soLuong;

    private Date ngayBatDau;

    private Date ngyKetThuc;

    private float diauKien;


    /*
        contructors
     */

    public VoucherDTO() {
    }

    public VoucherDTO(String maVoucher, float giaTri, int soLuong, Date ngayBatDau, Date ngyKetThuc, float diauKien) {
        this.maVoucher = maVoucher;
        this.giaTri = giaTri;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngyKetThuc = ngyKetThuc;
        this.diauKien = diauKien;
    }

    /*
        getter/setter
     */
    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public float getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(float giaTri) {
        this.giaTri = giaTri;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgyKetThuc() {
        return ngyKetThuc;
    }

    public void setNgyKetThuc(Date ngyKetThuc) {
        this.ngyKetThuc = ngyKetThuc;
    }

    public float getDiauKien() {
        return diauKien;
    }

    public void setDiauKien(float diauKien) {
        this.diauKien = diauKien;
    }


}
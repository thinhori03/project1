/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*
CREATE TABLE VOUCHER(
    MAV VARCHAR(10) PRIMARY KEY,
    GIATRI FLOAT,
    SOLUONG INT ,
    NGAYBATDAU DATE,
    NGAYKETTHUC DATE,
    DIEUKIEN FLOAT
)
*/

import java.util.Date;
import util.data.DataField;
import util.data.DataTable;

/**
 *
 * @author ngtnthori03
 */

@DataTable(name = "VOUCHER")
public class VoucherModel {

    @DataField(name = "MAV")
    private String maVoucher;

    @DataField(name = "GIATRI")
    private double giaTri;

    @DataField(name = "SOLUONG")
    private int soLuong;

    @DataField(name = "NGAYBATDAU")
    private Date ngayBatDau;

    @DataField(name = "NGAYKETTHUC")
    private Date ngyKetThuc;

    @DataField(name = "DIEUKIEN")
    private double diauKien;

    /*
        contructors
     */
    public VoucherModel() {
    }

    public VoucherModel(String maVoucher, float giaTri, int soLuong, Date ngayBatDau, Date ngyKetThuc, float diauKien) {
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

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
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

    public double getDiauKien() {
        return diauKien;
    }

    public void setDiauKien(double diauKien) {
        this.diauKien = diauKien;
    }
}
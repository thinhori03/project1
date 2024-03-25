/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import util.data.DataField;
import util.data.DataId;
import util.data.DataTable;

/**
 *
 * @author ngtnthori03
 *
 * CREATE TABLE VOUCHER ( MAV VARCHAR(10) PRIMARY KEY, GIATRI FLOAT, NGAYBATDAU
 * DATE, NGAYKETTHUC DATE, DIEUKIEN FLOAT, ngay_tao DATETIME, TRANG_THAI
 * VARCHAR(20) -- đang hoạt động, đã hủy )
 */
@DataTable(name = "VOUCHER")
public class VoucherModel {

    @DataId
    @DataField(name = "MAV")
    private String maVoucher;

    @DataField(name = "GIATRI")
    private double giaTri;

    @DataField(name = "NGAYBATDAU")
    private Date ngayBatDau;

    @DataField(name = "NGAYKETTHUC")
    private Date ngayKetThuc;

    @DataField(name = "DIEUKIEN")
    private double diauKien;

    @DataField(name = "ngay_tao")
    private Date ngayTao;

    @DataField(name = "TRANG_THAI")
    private String trangThai;

    /*
        contructors
     */
    public VoucherModel() {
    }
    public VoucherModel(String maVoucher, double giaTri, Date ngayBatDau, Date ngyKetThuc, double diauKien, Date ngayTao, String trangThai) {
        this.maVoucher = maVoucher;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.diauKien = diauKien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

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

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getDiauKien() {
        return diauKien;
    }

    public void setDiauKien(double diauKien) {
        this.diauKien = diauKien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}

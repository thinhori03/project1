/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;

import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataId;
import org.project1.nhom8.util.data.DataTable;

import java.util.Date;

/**
 * @author ngtnthori03
 * CREATE TABLE VOUCHER
 * (
 * MAV                VARCHAR(10) PRIMARY KEY,
 * GIATRI             FLOAT,
 * NGAYBATDAU         DATE,
 * NGAYKETTHUC        DATE,
 * DIEUKIEN           FLOAT,
 * ngay_tao           DATETIME,
 * so_luong           INT,
 * ngay_cap_nhat      DATETIME,
 * TRANG_THAI         NVARCHAR(20) -- đang hoạt động, đã hủy
 * )
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

    @DataField(name = "Ngay_tao")
    private Date ngayTao;

    @DataField(name = "so_luong")
    private Integer soLuong;

    @DataField(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    @DataField(name = "TRANG_THAI")
    private String trangThai;

    public VoucherModel() {
    }

    public VoucherModel(String maVoucher, double giaTri, Date ngayBatDau, Date ngayKetThuc, double diauKien, Date ngayTao, Integer soLuong, Date ngayCapNhat, String trangThai) {
        this.maVoucher = maVoucher;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.diauKien = diauKien;
        this.ngayTao = ngayTao;
        this.soLuong = soLuong;
        this.ngayCapNhat = ngayCapNhat;
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

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}


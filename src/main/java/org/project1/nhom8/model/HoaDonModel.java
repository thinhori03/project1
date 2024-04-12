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
 * <p>
 * SQL table
 * CREATE TABLE HOA_DON
 * (
 * MAHD          varchar(20) PRIMARY KEY,
 * MAKH          INT references KHACH_HANG (MAKH),
 * MANV          INT references NHAN_VIEN (MANV),
 * NGAYTAO       DATETIME,
 * NGAYTHANHTOAN DATETIME,
 * TRANGTHAI     VARCHAR(100),
 * PHUONGTHUC    VARCHAR(100),
 * MAV           VARCHAR(10) references VOUCHER (MAV)
 * )
 */

@DataTable(name = "HOA_DON")
public class HoaDonModel {

    @DataId
    @DataField(name = "MAHD")
    private String maHoaDon;

    @DataField(name = "MAKH")
    private int maKH;

    @DataField(name = "MANV")
    private int maNV;

    @DataField(name = "MANV_THANH_TOAN")
    private Integer maNVXacNhan;

    @DataField(name = "TRANGTHAI")
    private String trangThai;

    @DataField(name = "PHUONGTHUC")
    private String phuongThuc;

    @DataField(name = "MAV")
    private String maVoucher;

    @DataField(name = "NGAYTAO")
    private Date ngayTao;

    @DataField(name = "NGAYTHANHTOAN")
    private Date ngayThanhToan;

    @DataField(name = "TIEN_THANH_TOAN")
    private Double tienThanhToan;

    public HoaDonModel() {
    }

    public HoaDonModel(String maHoaDon, int maKH, int maNV, Integer maNVXacNhan, String trangThai, String phuongThuc, String maVoucher, Date ngayTao, Date ngayThanhToan, Double tienThanhToan) {
        this.maHoaDon = maHoaDon;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maNVXacNhan = maNVXacNhan;
        this.trangThai = trangThai;
        this.phuongThuc = phuongThuc;
        this.maVoucher = maVoucher;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tienThanhToan = tienThanhToan;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Integer getMaNVXacNhan() {
        return maNVXacNhan;
    }

    public void setMaNVXacNhan(Integer maNVXacNhan) {
        this.maNVXacNhan = maNVXacNhan;
    }

    public Double getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(Double tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }
}
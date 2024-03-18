/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * CREATE TABLE HOA_DON
 * (
 *     MAHD          INT IDENTITY (1,1) PRIMARY KEY,
 *     MAKH          INT references KHACH_HANG (MAKH),
 *     MANV          INT references NHAN_VIEN (MANV),
 *     TRANGTHAI     VARCHAR(100),
 *     PHUONGTHUC    VARCHAR(100),
 *     MAV           VARCHAR(10) references VOUCHER (MAV),
 *     NGAYTAO       DATE,
 *     NGAYTHANHTOAN DATE
 * )
 */

import util.data.DataField;
import util.data.DataId;
import util.data.DataTable;

import java.util.Date;

/**
 *
 * @author ngtnthori03
 */
@DataTable(name = "HOA_DON")
public class HoaDonModel {

    @DataId
    @DataField(name = "MAHD")
    private int maHoaDon;

    @DataField(name = "MAKH")
    private int maKH;

    @DataField(name = "MANV")
    private int maNV;

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
    /*
        constructor
     */
    public HoaDonModel() {
    }

    public HoaDonModel(int maHoaDon, int maKH, int maNV, String trangThai, String phuongThuc, String maVoucher, Date ngayTao, Date ngayThanhToan) {
        this.maHoaDon = maHoaDon;
        this.maKH = maKH;
        this.maNV = maNV;
        this.trangThai = trangThai;
        this.phuongThuc = phuongThuc;
        this.maVoucher = maVoucher;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
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
}

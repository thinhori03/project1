package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.convert.DateFormat;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;


public class HoaDonViewModel {

    @DataHeader(name = "mã hóa đơn")
    private String maHoaDon;

    @DataHeader(name = "thời gian tạo")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayTao;

    @DataHeader(name = "thòi gian thanh toán")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayThanhToan;

    @DataHeader(name = "mã nhân viên")
    private Integer maNV;

    @DataHeader(name = "tên nhân viên")
    private String tenNV;

    @DataHeader(name = "tên khách hàng")
    private String tenKH;

    @DataHeader(name = "tổng tiền")
    private Double tongTien;

    @DataHeader(name = "giá voucher")
    private Double voucher;

    @DataHeader(name = "trạng thái")
    private String TrangThai;


    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String maHoaDon, Date ngayTao, Date ngayThanhToan, Integer maNV, String tenNV, String tenKH, Double tongTien, String trangThai, Double voucher) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.tongTien = tongTien;
        TrangThai = trangThai;
        this.voucher = voucher;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
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

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public Double getVoucher() {
        return voucher;
    }

    public void setVoucher(Double voucher) {
        this.voucher = voucher;
    }
}

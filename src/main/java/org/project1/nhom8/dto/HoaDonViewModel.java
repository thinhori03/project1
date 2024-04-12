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

    @DataHeader(name = "mã nhân viên tạo")
    private Integer maNV;

    @DataHeader(name = "mã nhân viên xác nhận")
    private Integer maNvXacNhan;

    @DataHeader(name = "tên khách hàng")
    private String tenKH;

    @DataHeader(name = "tổng tiền")
    private Double tongTien;

    @DataHeader(name = "giá voucher")
    private Double voucher;

    @DataHeader(name = "tiền khách đưa")
    private Double tienThanhToan;

    @DataHeader(name = "trạng thái")
    private String TrangThai;


    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String maHoaDon, Date ngayTao, Date ngayThanhToan, Integer maNV, Integer maNvXacNhan, String tenKH, Double tongTien, Double voucher, Double tienThanhToan, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.maNV = maNV;
        this.maNvXacNhan = maNvXacNhan;
        this.tenKH = tenKH;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.tienThanhToan = tienThanhToan;
        TrangThai = trangThai;
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

    public Integer getMaNvXacNhan() {
        return maNvXacNhan;
    }

    public void setMaNvXacNhan(Integer maNvXacNhan) {
        this.maNvXacNhan = maNvXacNhan;
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

    public Double getVoucher() {
        return voucher;
    }

    public void setVoucher(Double voucher) {
        this.voucher = voucher;
    }

    public Double getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(Double tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}


package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;


public class HoaDonViewModel {

    @DataHeader(name = "mã hóa đơn")
    private String maHoaDon;

    @DataHeader(name = "ngày tạo")
    private Date ngayTao;

    @DataHeader(name = "mã nhân viên")
    private Integer maNV;
    @DataHeader(name = "tên nhân viên")
    private String tenNV;

    @DataHeader(name = "tên khách hàng")
    private String tenKH;

    //    @DataHeader(name = "số điện thoại")
    private String sdt;

    @DataHeader(name = "tổng tiền")
    private Double tongTien;

    @DataHeader(name = "trạng thái")
    private String TrangThai;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String maHoaDon, Date ngayTao, Integer maNV, String tenNV, String tenKH, String sdt, Double tongTien, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.tongTien = tongTien;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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
}

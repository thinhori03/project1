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

    @DataHeader(name = "mã khách hàng")
    private Integer maKH;

    @DataHeader(name = "tổng tiền")
    private Double tongTien;

    @DataHeader(name = "trạng thái")
    private String TrangThai;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String maHoaDon, Date ngayTao, Integer maNV, Integer maKH, Double tongTien, String TrangThai) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.TrangThai = TrangThai;
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

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
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

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}

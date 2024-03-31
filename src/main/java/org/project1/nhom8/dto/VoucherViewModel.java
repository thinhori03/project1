package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.convert.DateFormat;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;

public class VoucherViewModel {

    @DataHeader(name = "mã voucher")
    private String maVoucher;

    @DataHeader(name = "giá")
    private Double gia;

    @DataHeader(name = "điều kiện")
    private Double dieuKien;

    @DataHeader(name = "ngày tạo")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayTao;

    @DataHeader(name = "ngày bắt đầu")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayBatDau;

    @DataHeader(name = "ngày kiết thúc")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayKetThuc;

    @DataHeader(name = "trạng thái")
    private String trangThai;

    public VoucherViewModel() {
    }

    public VoucherViewModel(String maVoucher, Double gia, Double dieuKien, Date ngayTao, Date ngayBatDau, Date ngayKetThuc, String trangThai) {
        this.maVoucher = maVoucher;
        this.gia = gia;
        this.dieuKien = dieuKien;
        this.ngayTao = ngayTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Double getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(Double dieuKien) {
        this.dieuKien = dieuKien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}

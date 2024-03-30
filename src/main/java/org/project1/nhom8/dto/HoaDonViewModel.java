package org.project1.nhom8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;


@Builder
public class HoaDonViewModel {

    @DataHeader(name = "Mã Hóa ??n")
    private String maHoaDon;

    @DataHeader(name = "Ngày t?o")
    private Date ngayTao;

    @DataHeader(name = "Mã nhân viên")
    private Integer maNV;

    @DataHeader(name = "Mã khách hàng")
    private Integer maKH;

    @DataHeader(name = "T?ng ti?n")
    private Double tongTien;

    @DataHeader(name = "Tr?ng thái")
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

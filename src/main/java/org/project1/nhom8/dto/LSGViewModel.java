package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.convert.ConcurrencyFormat;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;


public class LSGViewModel {

    @DataHeader(name = "mã sản phẩm")
    private Integer maSPCT;

    @DataHeader(name = "tên sản phẩm")
    private String tenSPCT;

    @DataHeader(name = "Giá")
    @ConcurrencyFormat
    private double gia;

    @DataHeader(name = "Ngày cập nhật")
    private Date ngayCapNhat;


    public LSGViewModel() {
    }

    public LSGViewModel(Integer maSPCT, String tenSPCT, double gia, Date ngayCapNhat) {
        this.maSPCT = maSPCT;
        this.tenSPCT = tenSPCT;
        this.gia = gia;
        this.ngayCapNhat = ngayCapNhat;
    }

    public Integer getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(Integer maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSPCT() {
        return tenSPCT;
    }

    public void setTenSPCT(String tenSPCT) {
        this.tenSPCT = tenSPCT;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
}

package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.convert.ConcurrencyFormat;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;


public class LSGViewModel {

    @DataHeader(name = "Giá")
    @ConcurrencyFormat
    private double gia;

    @DataHeader(name = "Ngày cập nhật")
    private Date ngayCapNhat;

    public LSGViewModel() {
    }

    public LSGViewModel(double gia, Date ngayCapNhat) {
        this.gia = gia;
        this.ngayCapNhat = ngayCapNhat;
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

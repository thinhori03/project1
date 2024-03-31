package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

public class SanPhamViewModel {

    @DataHeader(name = "mã sản phẩm")
    private  int maSanPham;

    @DataHeader(name = "tên sản phẩm")
    private String tenSanPham;
    
    @DataHeader(name = "trạng thái")
    private String trangThai;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(int maSanPham, String tenSanPham, String trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}

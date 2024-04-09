package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;


public class HDCTViewModel {
    @DataHeader(name = "mã sản phẩm")
    private Integer maSPCT;

    @DataHeader(name = "Tên sản phẩm")
    private String tenSP;

    @DataHeader(name = "số lượng")
    private Integer soLuong;

    @DataHeader(name = "Size")
    private String size;

    @DataHeader(name = "màu sắc")
    private String mauSac;

    @DataHeader(name = "giá")
    private double gia;

    @DataHeader(name = "giá khuyến mại")
    private Double giaKhuyenMai;

    public HDCTViewModel() {
    }

    public HDCTViewModel(Integer maSPCT, String tenSP, Integer soLuong, String size, String mauSac, double gia) {
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.size = size;
        this.mauSac = mauSac;
        this.gia = gia;
    }

    public Integer getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(Integer maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Double getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setGiaKhuyenMai(Double giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }
}

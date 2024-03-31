package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;


public class SPCTViewModel {

    private Integer maSPCT;

    @DataHeader(name = "mã sản phẩm")
    private Integer maSP;

    @DataHeader(name = "tên sản phẩm")
    private String tenSP;

    @DataHeader(name = "số lượng")
    private Integer soLuong;

    @DataHeader(name = "size")
    private String size;

    @DataHeader(name = "màu sắc")
    private String mauSac;

    @DataHeader(name = "trạng thái")
    private String trangThai;

    @DataHeader(name = "giá")
    private double gia;

    public SPCTViewModel() {
    }

    public SPCTViewModel(Integer maSPCT, Integer maSP, String tenSP, Integer soLuong, String size, String mauSac, String trangThai, double gia) {
        this.maSPCT = maSPCT;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.size = size;
        this.mauSac = mauSac;
        this.trangThai = trangThai;
        this.gia = gia;
    }

    public Integer getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(Integer maSPCT) {
        this.maSPCT = maSPCT;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
}

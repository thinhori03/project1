package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;


public class HDCTViewModel {
    @DataHeader(name = "M� s?n ph?m chi ti?t")
    private Integer maSPCT;

    @DataHeader(name = "T�n s?n ph?m")
    private String tenSP;

    @DataHeader(name = "S? l??ng")
    private Integer soLuong;

    @DataHeader(name = "Size")
    private String size;

    @DataHeader(name = "M�u s?c")
    private String mauSac;

    @DataHeader(name = "Gi�")
    private double gia;

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
    
}

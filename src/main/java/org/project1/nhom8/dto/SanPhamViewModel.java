package org.project1.nhom8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.visual.DataHeader;

@Builder
public class SanPhamViewModel {

    @DataHeader(name = "Mã s?n ph?m")
    private  int maSanPham;

    @DataHeader(name = "Tên s?n ph?m")
    private String tenSanPham;
    
    @DataHeader(name = "Tr?ng thái")
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

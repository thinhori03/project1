/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*
CREATE TABLE HOA_DON(
    MAHD INT PRIMARY KEY,
    MAKH INT references KHACH_HANG(MAKH),
    MANV INT references NHAN_VIEN(MANV),
    TRANGTHAI VARCHAR(100),
    PHUONGTHUC VARCHAR(100),
    MAV VARCHAR(10) references VOUCHER(MAV)
)
 */

/**
 *
 * @author ngtnthori03
 */
public class HoaDonModel {

    private int maHoaDon;

    private int maNhanVien;

    private int maKhachHang;

    private String trangThai;

    private String phuongThuc;

    private String maVoucher;

    /*
        constructor
     */

    public HoaDonModel() {
    }

    public HoaDonModel(int maHoaDon, int maNhanVien, int maKhachHang, String trangThai, java.lang.String phuongThuc, java.lang.String maVoucher) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.trangThai = trangThai;
        this.phuongThuc = phuongThuc;
        this.maVoucher = maVoucher;
    }

    /*
        getter/setter
     */

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public java.lang.String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(java.lang.String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public java.lang.String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(java.lang.String maVoucher) {
        this.maVoucher = maVoucher;
    }
}

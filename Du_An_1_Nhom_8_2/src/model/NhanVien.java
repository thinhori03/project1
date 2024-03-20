/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */

public class NhanVien {
    private int maNV;
    private String hoTen;
    private String sdt;
    private String email;
    private String gioiTinh;
    private String CCCD;
    private String matKhau;
    private String vaiTro;
    private String trangThai;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTen, String sdt, String email, String gioiTinh, String CCCD, String matKhau, String vaiTro, String trangThai) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.CCCD = CCCD;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    

   
    public Object[]toDataRow(){
        return new Object[]{
           this.getMaNV(),this.getHoTen(),this.getSdt(),this.getEmail(),this.getGioiTinh(),this.getMatKhau(),this.getCCCD(),this.getVaiTro(),this.getTrangThai()
        };
    }

    
}

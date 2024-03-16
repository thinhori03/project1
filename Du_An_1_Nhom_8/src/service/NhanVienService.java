/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

public class NhanVienService {

    private List<NhanVien> listNV;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    String SELECT_BY_ACCOUNT_SQL = "select * from NhanVien where TENNV = ?";

    public List<NhanVien> getAll() {
        listNV = new ArrayList<>();
        sql = "SELECT MANV,TENNV,SDT,EMAIL,GIOTINH,CCCD,MATKHAU,VAITRO,TRANGTHAI FROM NHAN_VIEN ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add(NhanVien nv) {
        sql = "INSERT INTO NHAN_VIEN(MANV,TENNV,SDT,EMAIL,GIOTINH,MATKHAU,CCCD,VAITRO,TRANGTHAI) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getEmail());
            ps.setString(5, nv.getGioiTinh());
            ps.setString(6, nv.getMatKhau());
            ps.setString(7, nv.getCCCD());
            ps.setString(8, nv.getVaiTro());
            ps.setString(9, nv.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int update(int ma, NhanVien nv) {
        sql = "UPDATE NHAN_VIEN SET TENNV = ?, SDT = ?,EMAIL = ?,GIOTINH = ?,MATKHAU = ?, CCCD = ? ,VAITRO = ?,TRANGTHAI = ? WHERE MANV = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getSdt());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getMatKhau());
            ps.setString(6, nv.getCCCD());
            ps.setString(7, nv.getVaiTro());
            ps.setString(8, nv.getTrangThai());
            ps.setInt(9, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<NhanVien> timKiem(int ma) {
        listNV = new ArrayList<>();
        sql = "SELECT MANV,TENNV,SDT,EMAIL,GIOTINH,CCCD,MATKHAU,VAITRO,TRANGTHAI FROM NHAN_VIEN WHERE MANV like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public NhanVien selectByAccount(String account) {
//        List<NhanVien> list = this.selectBySql(SELECT_BY_ACCOUNT_SQL, account);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }
}

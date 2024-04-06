/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

/**
 * @author PC
 */

import org.project1.nhom8.model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienVHHService {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    private List<NhanVien> disabledEmployees;

    public List<NhanVien> getDisabledEmployees() {
        disabledEmployees = new ArrayList<>();

        sql = "SELECT MANV,TENNV,SDT,EMAIL,GIOTINH,CCCD,MATKHAU,VAITRO,TRANGTHAI FROM NHAN_VIEN Where TRANGTHAI=N'Nghỉ việc'";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                disabledEmployees.add(nv);
            }
            return disabledEmployees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public int khoiPhucTrangThaiHoatDong(int ma) {
        sql = "UPDATE NHAN_VIEN SET trangThai=N'Đang làm việc' WHERE  MaNV=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

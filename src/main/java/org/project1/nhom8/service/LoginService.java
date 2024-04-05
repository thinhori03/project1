/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

/**
 *
 * @author PC
 */

import org.project1.nhom8.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public Login selectByMail(String email) {
        String sql = "SELECT MANV, TENNV, SDT, EMAIL, GIOTINH, MATKHAU, CCCD, VAITRO, TRANGTHAI FROM NHAN_VIEN WHERE EMAIL LIKE ?";
        try (
                Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Login login = new Login();
                    login.setMa(rs.getInt("MANV"));
                    login.setTen(rs.getString("TENNV"));
                    login.setSdt(rs.getString("SDT"));
                    login.setMatKhau(rs.getString("MATKHAU"));
                    login.setCCCD(rs.getString("CCCD"));
                    login.setGioiTinh(rs.getString("GIOTINH"));
                    login.setVaiTro(rs.getString("VAITRO"));
                    login.setTrangThai(rs.getString("TRANGTHAI"));
                    return login;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Login login(String email, String password) {
        String sql = "SELECT MANV, TENNV, SDT, EMAIL, GIOTINH, MATKHAU, CCCD, VAITRO, TRANGTHAI FROM NHAN_VIEN WHERE EMAIL LIKE ? AND MATKHAU LIKE ?";
        try (
                Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Login login = new Login();
                    login.setMa(rs.getInt("MANV"));
                    login.setTen(rs.getString("TENNV"));
                    login.setSdt(rs.getString("SDT"));
                    login.setMatKhau(rs.getString("MATKHAU"));
                    login.setCCCD(rs.getString("CCCD"));
                    login.setGioiTinh(rs.getString("GIOTINH"));
                    login.setVaiTro(rs.getString("VAITRO"));
                    login.setTrangThai(rs.getString("TRANGTHAI"));
                    return login;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Login lg = null;
}

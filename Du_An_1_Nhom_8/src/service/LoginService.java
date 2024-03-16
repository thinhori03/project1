/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author PC
 */
import java.sql.*;
import java.util.List;
import model.Login;

public class LoginService {
    public Login selectByMa(String ten) {
        String sql = "SELECT MANV, TENNV, SDT, EMAIL, GIOTINH, MATKHAU, CCCD, VAITRO, TRANGTHAI FROM NHAN_VIEN WHERE TENNV LIKE ?";
        try (
                Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
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

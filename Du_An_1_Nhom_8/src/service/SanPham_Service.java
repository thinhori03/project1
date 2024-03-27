/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class SanPham_Service {

    private List<SanPham> listsp;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public List<SanPham> getAll() {
        listsp = new ArrayList<>();
        sql = "select*from SAN_PHAM";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt(1),rs.getString(2),rs.getString(3)
                );
                listsp.add(sp);
            }
            return listsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int ADD_SP(SanPham sp) {
        sql = "INSERT INTO SAN_PHAM(TENSP,TRANGTHAI)VALUES(?,?)";
        int kq = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sp.getTensp());
            ps.setString(2, sp.getTrangthai());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public int Delete_SP(int ma){
        sql = "DELETE From SAN_PHAM where MASP like ?";
        int kq = 0;
        try {
            con = DBConnect.getConnection();
            ps= con.prepareStatement(sql);
            ps.setInt(1, ma);
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public List<SanPham> TimSanPham(String ten) {
        List<SanPham> listspT = new ArrayList<>();
        sql = "Select*from SAN_PHAM where TENSP like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt(1),rs.getString(2),rs.getString(3)
                );
                listspT.add(sp);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listspT;
    }

    public List<SanPham> TimSanPham(int ma) {
        List<SanPham> listspM = new ArrayList<>();
        sql = "Select*from SAN_PHAM where MASP = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt(1),rs.getString(2),rs.getString(3)
                );
                listspM.add(sp);
                
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listspM;
    }
}

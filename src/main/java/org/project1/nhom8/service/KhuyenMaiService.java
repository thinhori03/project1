/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.project1.nhom8.model.KhuyenMai;

/**
 *
 * @author Admin
 */
public class KhuyenMaiService {

    private List<KhuyenMai> listkm;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public List<KhuyenMai> getAll() {
        listkm = new ArrayList<>();
        sql = "Select*from KHUYEN_MAI_COUPON";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getFloat(5)
                );
                listkm.add(km);
            }
            return listkm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ADDKhuyenMai(KhuyenMai km) {
        listkm = new ArrayList<>();
        int kq = 0;
        sql = "insert into KHUYEN_MAI_COUPON(MAKM,NGAYBATDAU,NGAYKETTHUC,SOLUONG,GIA)values(?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, km.getMakm());
            ps.setString(2, km.getNgayBd());
            ps.setString(3, km.getNgayKt());
            ps.setInt(4, km.getSoluong());
            ps.setFloat(5, km.getGia());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int UpdateKhuyenMai(String ma, KhuyenMai km) {
        listkm = new ArrayList<>();
        sql = "Update KHUYEN_MAI_COUPON set NGAYBATDAU = ?,NGAYKETTHUC = ?,SOLUONG= ?,GIA= ? where MAKM like ?";
        int kq = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, km.getNgayBd());
            ps.setString(2, km.getNgayKt());
            ps.setInt(3, km.getSoluong());
            ps.setFloat(4, km.getGia());
            ps.setString(5, ma);
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int DeleteKhuyenMai(int ma) {
        listkm = new ArrayList<>();
        sql = "Delete  From SAN_PHAM_CHI_TIET where MAKM like ?";
        int kq = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            kq = ps.executeUpdate();
            kq += ps.executeUpdate();

            sql = "Delete from KHUYEN_MAI where MAKM like ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            kq += ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<KhuyenMai> TimSanPham(String ma) {
        List<KhuyenMai> listkm = new ArrayList<>();
        sql = "Select *from KHUYEN_MAI_COUPON where MAKM like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,"%" + ma + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)
                );
                listkm.add(km);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listkm;
    }
}

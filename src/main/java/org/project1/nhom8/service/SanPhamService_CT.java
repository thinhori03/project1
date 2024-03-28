/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.project1.nhom8.model.SPCTModel;

/**
 *
 * @author Admin
 */
public class SanPhamService_CT {

    private List<SPCTModel> listsp;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public List<SPCTModel> getAll() {
        listsp = new ArrayList<>();
        sql = "select sp.MASP,Sp.TENSP,SOLUONG,MASIZE,MAMAU,TRANGTHAI,lg.GIA from SAN_PHAM_CHI_TIET spct\n"
                + "		join SAN_PHAM sp on spct.MASP = sp.MASP\n"
                + "		join LICH_SU_GIA lg on spct.MASPCT = lg.MASPCT\n";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
//            while (rs.next()) {
//                SanPhamChiTietModel sp = new SanPhamChiTietModel(
//                        rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getFloat(7)
//               );
//                listsp.add(sp);
//            }
            return listsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public int ADDSanPham(SanPham_CT sp) {
//        listsp = new ArrayList<>();
//        sql = "insert into SAN_PHAM_CHI_TIET(MASP,SOLUONG,MASIZE,MAMAU,TRANGTHAI)values(?,?,?,?,?)";
//        int kq = 0;
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, sp.getMaSP());
////            ps.setString(2, sp.getTen());
//            ps.setInt(2, sp.getSoluong());
//            ps.setInt(3, sp.getSize());
//            ps.setInt(4, sp.getMasac());
////            ps.setString(6, sp.getMakm());
//            ps.setString(5, sp.getTrangthai());
////            ps.setFloat(8, sp.getGia());
//            kq = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }
//    public int UpdateSanPham(int ma, SanPham sp) {
//        listsp = new ArrayList<>();
//        sql = "Update SAN_PHAM_CHI_TIET set TENSP = ?,SOLUONG = ?,MASIZE =?,MAMAU =?,MAKM=?,TRANGTHAI = ?,MALSG =? where MASPCT like ?";
//        int kq = 0;
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, sp.getTen());
//            ps.setInt(2, sp.getSoluong());
//            ps.setInt(3, sp.getSize());
//            ps.setInt(4, sp.getMasac());
//            ps.setString(5, sp.getMakm());
//            ps.setString(6, sp.getTrangthai());
//            ps.setInt(7, sp.getId_gia());
//            ps.setInt(8, ma);
//            kq = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }
    
    public List<SPCTModel> TimSanPham(String ten) {
        List<SPCTModel> listsp = new ArrayList<>();
        sql = "select sp.MASP,Sp.TENSP,SOLUONG,MASIZE,MAMAU,TRANGTHAI,lg.GIA from SAN_PHAM_CHI_TIET spct\n"
                + "		join SAN_PHAM sp on spct.MASP = sp.MASP\n"
                + "		join LICH_SU_GIA lg on spct.MASPCT = lg.MASPCT where sp.TENSP like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            rs = ps.executeQuery();
//            while (rs.next()) {
//                SanPhamChiTietModel sp = new SanPhamChiTietModel(
//                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getString(6), rs.getFloat(7)
//                );
//                listsp.add(sp);
//            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsp;
    }

    public List<SPCTModel> TimSanPham(int ma) {
        List<SPCTModel> listkm = new ArrayList<>();
        sql = "select sp.MASP,Sp.TENSP,SOLUONG,MASIZE,MAMAU,TRANGTHAI,lg.GIA from SAN_PHAM_CHI_TIET spct\n"
                + "		join SAN_PHAM sp on spct.MASP = sp.MASP\n"
                + "		join LICH_SU_GIA lg on spct.MASPCT = lg.MASPCT where spct.MASP = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            rs = ps.executeQuery();
//            while (rs.next()) {
//                SanPhamChiTietModel sp = new SanPhamChiTietModel(
//                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getString(6), rs.getFloat(7)
//                );
//                listkm.add(sp);
//            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listkm;
    }
}

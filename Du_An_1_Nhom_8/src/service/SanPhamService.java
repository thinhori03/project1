/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;

/**
 *
 * @author Admin
 */
public class SanPhamService {

    private List<SanPham> listsp;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public List<SanPham> getAll() {
        listsp = new ArrayList<>();
        sql = "Select  spct.MASPCT,TENSP,SOLUONG,s.TENSIZE,ms.TENMAU,MAKM,TRANGTHAI,lsg.GIA from SAN_PHAM_CHI_TIET spct\n"
                + "join SIZE s on spct.MASIZE = s.MASIZE\n"
                + "join MAU_SAC ms on spct.MAMAU = ms.MAMAU\n"
                + "join LICH_SU_GIA lsg on spct.MALSG = lsg.MALSG";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(
                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8)
                );
                listsp.add(sp);
            }
            return listsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ADDSanPham(SanPham sp) {
        listsp = new ArrayList<>();
        sql = "insert into SAN_PHAM_CHI_TIET(MASPCT,TENSP,SOLUONG,MASIZE,MAMAU,MAKM,TRANGTHAI,MALSG)values(?,?,?,?,?,?,?,?)";
        int kq = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sp.getMaspct());
            ps.setString(2, sp.getTen());
            ps.setInt(3, sp.getSoluong());
            ps.setInt(4, sp.getSize());
            ps.setString(5, sp.getMasac());
            ps.setString(6, sp.getMakm());
            ps.setString(7, sp.getTrangthai());
            ps.setFloat(8, sp.getGia());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public int UpdateSanPham(int ma,SanPham sp){
        listsp = new ArrayList<>();
        sql = "Update SAN_PHAM_CHI_TIET set TENSP = ?,SOLUONG = ?,MASIZE =?,MAMAU =?,MAKM=?,TRANGTHAI = ?,MALSG =? where MASPCT like ?";
        int kq = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sp.getTen());
            ps.setInt(2, sp.getSoluong());
            ps.setInt(3, sp.getSize());
            ps.setString(4, sp.getMasac());
            ps.setString(5, sp.getMakm());
            ps.setString(6, sp.getTrangthai());
            ps.setFloat(7, sp.getGia());
            ps.setInt(8, ma);
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public int DeleteSanPham(int ma){
        listsp = new ArrayList<>();
        sql = "DELETE FROM SAN_PHAM_CHI_TIET WHERE MASPCT LIKE ?";
        int kq =  0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public List<SanPham> TimSanPham(String ten){
        List<SanPham> listsp = new ArrayList<>();
        sql = "Select  spct.MASPCT,TENSP,SOLUONG,s.TENSIZE,ms.TENMAU,MAKM,TRANGTHAI,lsg.GIA from SAN_PHAM_CHI_TIET spct\n"
                + "join SIZE s on spct.MASIZE = s.MASIZE\n"
                + "join MAU_SAC ms on spct.MAMAU = ms.MAMAU\n"
                + "join LICH_SU_GIA lsg on spct.MALSG = lsg.MALSG where TENSP like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,"%" +  ten + "%" );
            rs = ps.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham(
                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8)
                );
                listsp.add(sp);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsp;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Gia;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class GiaService {

    private List<Gia> listg;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public List<Gia> getAll() {
        listg = new ArrayList<>();
        sql = "select*from LICH_SU_GIA";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Gia g = new Gia(
                        rs.getInt(1), rs.getFloat(2), rs.getDate(3), rs.getDate(4)
                );
                listg.add(g);
            }
            return listg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Gia getBYID_Gia(int magia) {
        listg = new ArrayList<>();
        sql = "select*from LICH_SU_GIA where MALSG = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, magia);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            rs.next();
            Gia g = new Gia(
                    rs.getInt(1), rs.getFloat(2), rs.getDate(3), rs.getDate(4)
            );
            return g;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int TaoMA() {
        sql = "select COUNT(*) from LICH_SU_GIA";
        PreparedStatement p = null;
        try {
            con = DBConnect.getConnection();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            int ma = rs.getInt(1);
            return ma + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int ADDGia(Gia gia) {
        sql = "INSERT INTO LICH_SU_GIA(MALSG,GIA,NGAYBATDAU,NGAYKETTHUC)VALUES(?,?,?,?)";
//        int ma = this.TaoMA();
        PreparedStatement pss = null;
        try {
            con = DBConnect.getConnection();
            pss = con.prepareStatement(sql);
            pss.setInt(1, gia.getId_gia());
            pss.setFloat(2, gia.getGia());
            pss.setDate(3, new java.sql.Date(gia.getNgaybatdau().getTime()));
            pss.setDate(4,new java.sql.Date(gia.getNgayketthuc().getTime()));
            pss.executeUpdate();
            
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

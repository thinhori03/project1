/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import org.project1.nhom8.model.SizeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public class SIZE_Service {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    List<SizeModel> listSize;

    public List<SizeModel> getAll() {
        listSize = new ArrayList<>();
        sql = "Select*\n"
                + "from SIZE";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SizeModel sz = new SizeModel(rs.getInt(1), rs.getString(2));
                listSize.add(sz);

            }
            return listSize;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int upadate(int ma , SizeModel s ){
        sql = "UPDATE SIZE SET TENSIZE = ? WHERE MASIZE = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getTensize());
            ps.setInt(2, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public SizeModel getBYID_Size(int masize) {

        sql = "Select*from SIZE where MASIZE = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, masize);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            rs.next();
            SizeModel zs = new SizeModel(rs.getInt(1), rs.getString(2));
            return zs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSize(String tensize) {
        sql = "Select MASIZE from SIZE where TENSIZE = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setNString(1, tensize);
            rs = ps.executeQuery();
            rs.next();
            int ma = rs.getInt(1);
            if (Optional.of(ma).isEmpty()) {
                System.out.println("Tên không tồn tại");
            }
            return ma;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int ADD_SIZE(SizeModel s) {
        sql = "insert into SIZE(TENSIZE)values(?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getTensize());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}

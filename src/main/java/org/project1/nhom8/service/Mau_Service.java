/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.project1.nhom8.model.MauSacModel;

/**
 *
 * @author Admin
 */
public class Mau_Service {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    List<MauSacModel> listMS;

    public List<MauSacModel> getAll() {
        listMS = new ArrayList<>();
        sql = "select*from MAU_SAC";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MauSacModel ms = new MauSacModel(rs.getInt(1), rs.getString(2));
                listMS.add(ms);

            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int update(int ma , MauSacModel ms){
        sql = "UPDATE MAU_SAC SET TENMAU = ? WHERE MAMAU = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ms.getTenmau());
            ps.setInt(2, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public MauSacModel getBYID_Mau(int mamau) {
        
        sql = "Select*from MAU_SAC where MAMAU = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mamau);
            rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                return null;
            }
            rs.next();
            MauSacModel ms = null; // new MauSacModel(rs.getInt(1),rs.getString(2));
            return ms;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public  int getMaMau(String tenmau) {
        sql = "Select MAMAU from MAU_SAC where TENMAU = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setNString(1, tenmau);
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
     public int ADD_MAU(MauSacModel m) {
        sql = "insert into MAU_SAC(TENMAU)values(?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getTenmau());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
             return 0;
        }
       
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import java.sql.*;
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
            MauSacModel ms = new MauSacModel(rs.getInt(1),rs.getString(2));
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
}

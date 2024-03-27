/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.SanPham;
import model.SIZE;

/**
 *
 * @author Admin
 */
public class SIZE_Service {
    
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
    public SIZE getBYID_Size(int masize) {
        
        sql = "Select*from SIZE where MASIZE = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, masize);
            rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                return null;
            }
            rs.next();
            SIZE zs = new SIZE(rs.getInt(1),rs.getString(2));
            return zs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  int getSize(String tensize) {
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
}

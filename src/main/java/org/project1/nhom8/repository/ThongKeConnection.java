/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.repository;

import java.util.ArrayList;
import org.project1.nhom8.model.ThongKeModel;
import org.project1.nhom8.service.DBConnect;
import java.sql.*;
/**
 *
 * @author acer
 */
public class ThongKeConnection {
    DBConnect dBConnect;
    public ArrayList<ThongKeModel> getAll(){
    String sql = " select HOA_DON_CHI_TIET.MASPCT, TENSP ,HOA_DON_CHI_TIET.SOLUONG,GIA,NGAYTHANHTOAN\n" +
"	 from HOA_DON_CHI_TIET\n" +
"	 join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT= HOA_DON_CHI_TIET.MASPCT\n" +
"	 join LICH_SU_GIA on LICH_SU_GIA.MALSG= HOA_DON_CHI_TIET.MALSG\n" +
"	 join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD\n" +
"	 join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP\n" ;
        ArrayList<ThongKeModel> list  = new ArrayList<>();
        try (Connection conn = dBConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)){
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer ma = rs.getInt("MASPCT");
                String ten = rs.getString("TENSP");
                Integer sl = rs.getInt("SOLUONG");
                String ngay = rs.getString("NGAYTHANHTOAN");
                Float gia = rs.getFloat("GIA");
                ThongKeModel tk = new ThongKeModel(ma, ten, sl, ngay, gia);
                list.add(tk);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<ThongKeModel> timkiem (String ngay1){
            String sql = " select HOA_DON_CHI_TIET.MASPCT, TENSP ,HOA_DON_CHI_TIET.SOLUONG,GIA,NGAYTHANHTOAN\n" +
"		 from HOA_DON_CHI_TIET\n" +
"		 join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT= HOA_DON_CHI_TIET.MASPCT\n" +
"		 join LICH_SU_GIA on LICH_SU_GIA.MALSG= HOA_DON_CHI_TIET.MALSG\n" +
"		 join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD\n" +
"		 join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP\n" +
"		 where NGAYTHANHTOAN like '%" + ngay1 + "%'";
            ArrayList<ThongKeModel> list = new ArrayList<>();
            try (Connection conn = dBConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)){
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer ma = rs.getInt("MASPCT");
                String ten = rs.getString("TENSP");
                Integer sl = rs.getInt("SOLUONG");
                String ngay = rs.getString("NGAYTHANHTOAN");
                Float gia = rs.getFloat("GIA");
                ThongKeModel tk = new ThongKeModel(ma, ten, sl, ngay, gia);
                list.add(tk);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return list;
            }
}

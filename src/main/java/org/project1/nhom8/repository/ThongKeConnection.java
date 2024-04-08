/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.repository;

import java.util.ArrayList;
import org.project1.nhom8.model.ThongKeModel;
import org.project1.nhom8.service.DBConnect;
import java.sql.*;
import java.util.Date;
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
                Date ngay = rs.getDate("NGAYTHANHTOAN");
                Float gia = rs.getFloat("GIA");
                ThongKeModel tk = new ThongKeModel(ma, ten, sl, ngay, gia);
                list.add(tk);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<ThongKeModel> timkiem (Date ngaybd ,Date ngayKt){
            String sql = " select HOA_DON_CHI_TIET.MASPCT, TENSP ,HOA_DON_CHI_TIET.SOLUONG,GIA,NGAYTHANHTOAN\n" +
"		 from HOA_DON_CHI_TIET\n" +
"		 join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT= HOA_DON_CHI_TIET.MASPCT\n" +
"		 join LICH_SU_GIA on LICH_SU_GIA.MALSG= HOA_DON_CHI_TIET.MALSG\n" +
"		 join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD\n" +
"		 join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP\n" +
"		 where NGAYTHANHTOAN  between ? and ? "
                    + "order by NGAYTHANHTOAN desc";
            ArrayList<ThongKeModel> list = new ArrayList<>();
            try (Connection conn = dBConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)){
                pst.setObject(1, ngaybd);
                pst.setObject(2, ngayKt);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Integer ma = rs.getInt("MASPCT");
                String ten = rs.getString("TENSP");
                Integer sl = rs.getInt("SOLUONG");
                Date ngay = rs.getDate("NGAYTHANHTOAN");
                Float gia = rs.getFloat("GIA");
                ThongKeModel tk = new ThongKeModel(ma, ten, sl, ngay, gia);
                list.add(tk);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return list;
            }
    
     public ArrayList<ThongKeModel> listSp() {
        String sql = "select  SAN_PHAM.TENSP,TENMAU,TENSIZE,HOA_DON_CHI_TIET.SOLUONG\n"
                + "		from HOA_DON_CHI_TIET\n"
                + "		join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT = HOA_DON_CHI_TIET.MASPCT \n"
                + "		join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP\n"
                + "		join MAU_SAC on MAU_SAC.MAMAU = SAN_PHAM_CHI_TIET.MAMAU\n"
                + "		join SIZE on SIZE.MASIZE = SAN_PHAM_CHI_TIET.MASIZE\n"
                + "		order by HOA_DON_CHI_TIET.SOLUONG desc ";
        ArrayList<ThongKeModel> list = new ArrayList<>();
        try (Connection con = dBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String ten = rs.getString("TENSP");
                String mau = rs.getString("TENMAU");
                String size = rs.getString("TENSIZE");
                Integer sl = rs.getInt("SOLUONG");

                ThongKeModel tk = new ThongKeModel(ten, mau, size, sl);
                list.add(tk);
            }
            }catch(Exception e){
                e.printStackTrace();
                }
        
        return list;
    }
}

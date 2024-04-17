/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.repository;

import org.project1.nhom8.dto.ProductRevenue;
import org.project1.nhom8.dto.RevenueByMonth;
import org.project1.nhom8.model.ThongKeModel;
import org.project1.nhom8.service.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author acer
 */
public class ThongKeConnection {
    DBConnect dBConnect;

    public ArrayList<ThongKeModel> getAll() {
        String sql = " select HOA_DON_CHI_TIET.MASPCT, TENSP ,HOA_DON_CHI_TIET.SOLUONG,GIA,NGAYTHANHTOAN\n" +
                "	 from HOA_DON_CHI_TIET\n" +
                "	 join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT= HOA_DON_CHI_TIET.MASPCT\n" +
                "	 join LICH_SU_GIA on LICH_SU_GIA.MALSG= HOA_DON_CHI_TIET.MALSG\n" +
                "	 join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD\n" +
                "	 join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP\n";
        ArrayList<ThongKeModel> list = new ArrayList<>();
        try (Connection conn = dBConnect.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
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

    public ArrayList<ThongKeModel> timkiem(Date ngaybd, Date ngayKt) {
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
             PreparedStatement pst = conn.prepareStatement(sql)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<RevenueByMonth> getRevenueByMonth() {

        List<RevenueByMonth> result = new ArrayList<>();

        String query = """
                SELECT
                    SUM(REVENUE) AS REVENUE
                    , MON
                from (SELECT
                    SUM(LSG.GIA) * hdct.SOLUONG AS REVENUE
                    , MONTH(HD.NGAYTHANHTOAN) AS MON
                FROM HOA_DON_CHI_TIET AS HDCT
                    JOIN SAN_PHAM_CHI_TIET AS SPCT ON SPCT.MASPCT = HDCT.MASPCT
                    JOIN SAN_PHAM AS SP ON SPCT.MASP = SP.MASP
                    JOIN LICH_SU_GIA AS LSG ON SPCT.MASPCT = LSG.MASPCT
                    JOIN HOA_DON AS HD ON HDCT.MAHD = HD.MAHD
                group by hdct.SOLUONG, MONTH(HD.NGAYTHANHTOAN)) as GM
                GROUP BY MON
                """;

        try {
            PreparedStatement prestat = DBConnect.getConnection().prepareStatement(query);

            ResultSet resultSet = prestat.executeQuery();

            while (resultSet.next()) {
                result.add(new RevenueByMonth(
                        resultSet.getDouble("REVENUE")
                        , resultSet.getByte("MON")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public List<RevenueByMonth> getRevenueByMonth(Date startDate, Date endDate) {

        List<RevenueByMonth> result = new ArrayList<>();

        String query = """
                SELECT
                    SUM(REVENUE) AS REVENUE
                    , MON
                from (SELECT
                    SUM(LSG.GIA) * hdct.SOLUONG AS REVENUE
                    , MONTH(HD.NGAYTHANHTOAN) AS MON
                FROM HOA_DON_CHI_TIET AS HDCT
                    JOIN SAN_PHAM_CHI_TIET AS SPCT ON SPCT.MASPCT = HDCT.MASPCT
                    JOIN SAN_PHAM AS SP ON SPCT.MASP = SP.MASP
                    JOIN LICH_SU_GIA AS LSG ON SPCT.MASPCT = LSG.MASPCT
                    JOIN HOA_DON AS HD ON HDCT.MAHD = HD.MAHD
                    WHERE  GETDATE() BETWEEN ? AND ?
                group by hdct.SOLUONG, MONTH(HD.NGAYTHANHTOAN)) as GM
                GROUP BY MON
                """;

        try {
            PreparedStatement prestat = DBConnect.getConnection().prepareStatement(query);

            prestat.setDate(1, new java.sql.Date(startDate.getTime()));
            prestat.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = prestat.executeQuery();

            while (resultSet.next()) {
                result.add(new RevenueByMonth(
                        resultSet.getDouble("REVENUE")
                        , resultSet.getByte("MON")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public List<ProductRevenue> getProductRevenue() {

        List<ProductRevenue> result = new ArrayList<>();

        String query = """
                SELECT
                    SUM(REVENUE) AS REVENUE
                , product_name
                from (SELECT
                          SUM(LSG.GIA) * hdct.SOLUONG AS REVENUE
                           , MONTH(HD.NGAYTHANHTOAN) AS MON
                            , SP.TENSP as product_name
                      FROM HOA_DON_CHI_TIET AS HDCT
                               JOIN SAN_PHAM_CHI_TIET AS SPCT ON SPCT.MASPCT = HDCT.MASPCT
                               JOIN SAN_PHAM AS SP ON SPCT.MASP = SP.MASP
                               JOIN LICH_SU_GIA AS LSG ON SPCT.MASPCT = LSG.MASPCT
                               JOIN HOA_DON AS HD ON HDCT.MAHD = HD.MAHD
                      group by hdct.SOLUONG, SP.TENSP, MONTH(HD.NGAYTHANHTOAN)) as GM
                GROUP BY Product_name
                """;

        try {
            PreparedStatement prestat = DBConnect.getConnection().prepareStatement(query);

            ResultSet resultSet = prestat.executeQuery();

            while (resultSet.next()) {
                result.add(new ProductRevenue(
                        resultSet.getString("product_name")
                        , resultSet.getDouble("REVENUE")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }

        return result;

    }

    public List<ProductRevenue> getProductRevenue(Date startDate, Date endDate) {

        List<ProductRevenue> result = new ArrayList<>();

        String query = """
                SELECT
                    SUM(REVENUE) AS REVENUE
                , product_name
                from (SELECT
                          SUM(LSG.GIA) * hdct.SOLUONG AS REVENUE
                           , MONTH(HD.NGAYTHANHTOAN) AS MON
                            , SP.TENSP as product_name
                      FROM HOA_DON_CHI_TIET AS HDCT
                               JOIN SAN_PHAM_CHI_TIET AS SPCT ON SPCT.MASPCT = HDCT.MASPCT
                               JOIN SAN_PHAM AS SP ON SPCT.MASP = SP.MASP
                               JOIN LICH_SU_GIA AS LSG ON SPCT.MASPCT = LSG.MASPCT
                               JOIN HOA_DON AS HD ON HDCT.MAHD = HD.MAHD
                               WHERE  GETDATE() BETWEEN ? AND ?
                      group by hdct.SOLUONG, SP.TENSP, MONTH(HD.NGAYTHANHTOAN)) as GM
                GROUP BY Product_name
                """;

        try {
            PreparedStatement prestat = DBConnect.getConnection().prepareStatement(query);

            prestat.setDate(1, new java.sql.Date(startDate.getTime()));
            prestat.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = prestat.executeQuery();

            while (resultSet.next()) {
                result.add(new ProductRevenue(
                        resultSet.getString("product_name")
                        , resultSet.getDouble("REVENUE")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }

        return result;

    }
}

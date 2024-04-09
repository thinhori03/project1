/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.repository;

import org.project1.nhom8.model.VoucherModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ngtnthori03
 */
public class VoucherRepository extends GeneralRepository<VoucherModel, String> {

    public VoucherRepository() {
        super(VoucherModel.class);
    }


    public List<VoucherModel> getAvailableVoucher(Double totalPrice) {

        List<VoucherModel> result = new ArrayList<>();

        String query = getQueryGenerator().generateSelectAllQuery()
                + "\n"
                + "\n\tWHERE VOUCHER.DIEUKIEN > ?"
                + "\n\tAND NGAYBATDAU BETWEEN VOUCHER.NGAYBATDAU AND VOUCHER.NGAYKETTHUC"
                + "\n\tAND so_luong > 0"
                + "\n\tAND VOUCHER.TRANG_THAI like N'đang hoạt động'"
                + "\n\tORDER by DIEUKIEN";
        try {
            PreparedStatement preStat = getConnection().prepareStatement(query);
            preStat.setDouble(1, totalPrice);

            ResultSet resultSet = preStat.executeQuery();

            while (resultSet.next()) {
                result.add(getQueryGenerator().map(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public VoucherModel getVoucherToApply(Double totalPrice) {
        String query = """
                SELECT TOP 1
                    *
                FROM VOUCHER
                WHERE VOUCHER.DIEUKIEN <= ?
                  AND VOUCHER.GIATRI = (
                      SELECT MAX(VOUCHER.GIATRI)
                      FROM VOUCHER
                      WHERE VOUCHER.DIEUKIEN <= ?
                        AND GETDATE() BETWEEN VOUCHER.NGAYBATDAU AND VOUCHER.NGAYKETTHUC
                        AND VOUCHER.SOLUONG > 0
                        AND VOUCHER.TRANG_THAI like N'đang hoạt động'
                    )
                  AND GETDATE() BETWEEN VOUCHER.NGAYBATDAU AND VOUCHER.NGAYKETTHUC
                  AND SOLUONG > 0
                  AND VOUCHER.TRANG_THAI like N'đang hoạt động'
                """;

        try {
            PreparedStatement preStat = getConnection().prepareStatement(query);
            preStat.setDouble(1, totalPrice);
            preStat.setDouble(2, totalPrice);

            ResultSet resultSet = preStat.executeQuery();

            if (resultSet.next()) {
                return getQueryGenerator().map(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}

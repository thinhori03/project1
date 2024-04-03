package org.project1.nhom8.repository;

import org.apache.xmlbeans.impl.common.ResolverUtil;
import org.project1.nhom8.model.SPCTModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SPCTRepository extends GeneralRepository<SPCTModel, Integer> {

    public SPCTRepository() {
        super(SPCTModel.class);
    }

    /**
     *
     * @param ten
     * @return empty list if failed
     */
    public List<SPCTModel> findByTen(String ten) {

        List<SPCTModel> result = new ArrayList<>();

        String query = getQueryGenerator().generateSelectAllQuery()
                + "\n"
                + "JOIN SAN_PHAM"
                + "\n\tON SAN_PHAM_CHI_TIET.MASP = SAN_PHAM.MASP"
                + "\n\tWHERE SAN_PHAM.TENSP LIKE ?";

        try {
            PreparedStatement preStat = getConnection().prepareStatement(query);

            preStat.setNString(1, "%" + ten + "%");

            ResultSet resultSet = preStat.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    result.add(getQueryGenerator().map(resultSet));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println(query);

        return result;
    }
    public List<SPCTModel> findByMa(int ma) {

        List<SPCTModel> result = new ArrayList<>();

        String query = getQueryGenerator().generateSelectAllQuery()
                + "\n"
                + "JOIN SAN_PHAM"
                + "\n\tON SAN_PHAM_CHI_TIET.MASP = SAN_PHAM.MASP"
                + "\n\tWHERE SAN_PHAM.MASP = ? ";

        try {
            PreparedStatement preStat = getConnection().prepareStatement(query);
            preStat.setInt(1, ma);

            ResultSet resultSet = preStat.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    result.add(getQueryGenerator().map(resultSet));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println(query);

        return result;
    }
}

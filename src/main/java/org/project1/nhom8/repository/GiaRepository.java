package org.project1.nhom8.repository;

import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.VoucherModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiaRepository extends GeneralRepository<GiaModel, Integer> {

    public GiaRepository() {
        super(GiaModel.class);
    }

    public GiaModel getgiaMoiNhat(Integer maspct) {
        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(getQueryGenerator()
                            .generateSelectAllQuery() +
                            """
                                WHERE MASPCT = ?
                                ORDER BY NGAYUPDATE DESC
                            """);
            preparedStatement.setInt(1, maspct);

            ResultSet resultSet = preparedStatement.executeQuery();;

            if (resultSet.next()) {
                return getQueryGenerator().map(resultSet);
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<GiaModel> getLichSugia(Integer maspct) {

        List<GiaModel> gias = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(getQueryGenerator().generateSelectAllQuery() +
                            """
                                WHERE MASPCT = ?
                            """);
            preparedStatement.setInt(1, maspct);
            ResultSet resultSet = preparedStatement.executeQuery();;

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    gias.add(getQueryGenerator().map(resultSet));
                }
            }

            return gias;

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}

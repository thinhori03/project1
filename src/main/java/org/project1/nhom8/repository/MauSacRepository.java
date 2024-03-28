package org.project1.nhom8.repository;

import org.project1.nhom8.model.MauSacModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MauSacRepository extends GeneralRepository<MauSacModel, Integer> {

    public MauSacRepository() {
        super(MauSacModel.class);
    }

    public MauSacModel findByTen(String tenMau) {

        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(getQueryGenerator().generateSelectAllQuery() +
                            """
                                WHERE TENMAU = ?
                            """);
            preparedStatement.setString(1, tenMau);
            ResultSet resultSet = preparedStatement.executeQuery();;

            if (resultSet.next()) {
                return getQueryGenerator().map(resultSet);
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

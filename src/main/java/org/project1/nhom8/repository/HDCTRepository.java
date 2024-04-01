package org.project1.nhom8.repository;

import org.project1.nhom8.model.HDCTModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HDCTRepository extends GeneralRepository<HDCTModel, String> {

    public HDCTRepository() {
        super(HDCTModel.class);
    }


    public List<HDCTModel> findByMaHD(String maHD) {
        List<HDCTModel> hdcts = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(getQueryGenerator()
                            .generateSelectAllQuery() +
                            " WHERE MAHD = ? ");
            preparedStatement.setString(1, maHD);
            ResultSet resultSet = preparedStatement.executeQuery();
            ;

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    hdcts.add(getQueryGenerator().map(resultSet));
                }
            }

            return hdcts;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }
}

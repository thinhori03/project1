package org.project1.nhom8.repository;


import org.project1.nhom8.model.SizeModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeRepository extends GeneralRepository<SizeModel, Integer> {

    public SizeRepository() {
        super(SizeModel.class);
    }

    public SizeModel findByTen(String ten) {

        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(getQueryGenerator().generateSelectAllQuery() +
                            " WHERE TENSIZE = ? ");
            preparedStatement.setString(1, ten);
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

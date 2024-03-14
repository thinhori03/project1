/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import model.VoucherModel;
import service.DBConnect;
import util.data.QueryGenerator;

/**
 *
 * @author ngtnthori03
 */
public class VoucherRepository {

    private java.sql.Connection connection;

    private QueryGenerator<VoucherModel> queryGenerator;

    public VoucherRepository() {

        this.connection = DBConnect.getConnection();
        this.queryGenerator = new QueryGenerator<>(VoucherModel.class);
    }

    public List<VoucherModel> getAll() throws SQLException, IllegalArgumentException, IllegalAccessException {
        
        return this.queryGenerator.executeFindAll(connection);
    }

    public VoucherModel findById(String voucherId) {

        return null;
    }
    
    public VoucherModel add(VoucherModel obj) {
        
        return this.queryGenerator.executeInsert(connection, obj);
    }
}

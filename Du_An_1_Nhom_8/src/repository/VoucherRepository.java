/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import model.VoucherModel;
import service.DBConnect;
import util.data.QueryGenerator;

/**
 *
 * @author ngtnthori03
 */
public class VoucherRepository {

    private java.sql.Connection connection;

    private QueryGenerator<VoucherModel, String> queryGenerator;

    public VoucherRepository() {

        this.connection = DBConnect.getConnection();
        this.queryGenerator = new QueryGenerator<>(VoucherModel.class);
    }

    public List<VoucherModel> getAll(){
        
        return this.queryGenerator.executeFindAll(connection);
    }

    public VoucherModel findById(String maVoucher) {

        return this.queryGenerator.executeFindById(connection, maVoucher);
    }
    
    public VoucherModel add(VoucherModel obj) {
        
        return this.queryGenerator.executeInsert(connection, obj);
    }

    public boolean update(VoucherModel obj) {

        return this.queryGenerator.executeUpdate(connection, obj);
    }
}

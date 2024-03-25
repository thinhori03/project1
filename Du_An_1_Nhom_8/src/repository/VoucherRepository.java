/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.HoaDonModel;
import service.DBConnect;
import util.data.QueryGenerator;

import java.util.List;

/**
 *
 * @author ngtnthori03
 */
public class VoucherRepository {

    private java.sql.Connection connection;

    private QueryGenerator<HoaDonModel, String> queryGenerator;

    public VoucherRepository() {

        this.connection = DBConnect.getConnection();
        this.queryGenerator = new QueryGenerator<>(HoaDonModel.class);
    }

    public List<HoaDonModel> getAll(){
        
        return this.queryGenerator.executeFindAll(connection);
    }

    public HoaDonModel findById(String maVoucher) {

        return this.queryGenerator.executeFindById(connection, maVoucher);
    }
    
    public HoaDonModel add(HoaDonModel obj) {
        
        return this.queryGenerator.executeInsert(connection, obj);
    }

    public boolean update(HoaDonModel obj) {

        return this.queryGenerator.executeUpdate(connection, obj);
    }
}

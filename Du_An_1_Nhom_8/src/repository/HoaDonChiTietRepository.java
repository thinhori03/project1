package repository;

import model.HoaDonChiTietModel;
import service.DBConnect;
import util.data.QueryGenerator;

import java.sql.Connection;
import java.util.List;

public class HoaDonChiTietRepository {

    private Connection connection;

    private QueryGenerator<HoaDonChiTietModel, Integer> queryGenerator;


    public HoaDonChiTietRepository() {
        this.connection = DBConnect.getConnection();

        this.queryGenerator = new QueryGenerator<>(HoaDonChiTietModel.class);
    }

    public List<HoaDonChiTietModel> getAll() {
        return this.queryGenerator.executeFindAll(connection);
    }

    public boolean add(HoaDonChiTietModel hdctm) {
        return this.queryGenerator.executeInsert(connection, hdctm) != null;
    }

    public boolean update(HoaDonChiTietModel hdctm) {
        return this.queryGenerator.executeUpdate(connection, hdctm);
    }

    public HoaDonChiTietModel findById( int id) {
        return this.queryGenerator.executeFindById(connection, id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.repository;

import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.service.DBConnect;
import org.project1.nhom8.util.data.QueryGenerator;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author ngtnthori03
 */
public class HoaDonRepository {
    
    private Connection connection;

    private QueryGenerator<HoaDonModel, Integer> queryGenerator;


    public HoaDonRepository() {

        this.connection = DBConnect.getConnection();

        this.queryGenerator = new QueryGenerator<>(HoaDonModel.class);
    }

    public List<HoaDonModel> getAll() {
        return this.queryGenerator.executeFindAll(connection);
    }

    public boolean add(HoaDonModel hdctm) {
        return this.queryGenerator.executeInsert(connection, hdctm) != null;
    }

    public boolean update(HoaDonModel hdctm) {
        return this.queryGenerator.executeUpdate(connection, hdctm);
    }

    public HoaDonModel findById( int id) {
        return this.queryGenerator.executeFindById(connection, id);
    }
}

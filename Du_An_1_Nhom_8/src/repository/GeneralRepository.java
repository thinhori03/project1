/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import service.DBConnect;
import util.data.QueryGenerator;

/**
 *
 * @author ngtnthori03
 */
public class GeneralRepository<TTable, TId> {

    private final Connection connection;
    private final QueryGenerator<TTable, TId> queryGenerator; 

    public GeneralRepository(Class<TTable> tableClass) {

        this.connection = DBConnect.getConnection();

        this.queryGenerator = new QueryGenerator<>(tableClass);
    }

    /*
     * @return null if insert failed, elsse entity with all generated columns will be returned
    */
    public TTable add(TTable table) {
        return this.queryGenerator.executeInsert(connection, table);
    }

    
    public boolean update(TTable table) {
           return this.queryGenerator.executeUpdate(connection, table);
    }
    
    public Long count() {
        return this.queryGenerater.exwcuteCountAll(connection);;
    }
    
    public TTable findById(TId id) {
        return this.queryGenerator.executeFindById(connection, id);
    }

    public Long pageCount(int items) {
        return this.count() / items;
    }
}

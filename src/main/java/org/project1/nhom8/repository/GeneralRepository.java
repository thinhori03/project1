package org.project1.nhom8.repository;

import java.sql.*;
import java.util.List;

import lombok.Getter;
import org.project1.nhom8.service.DBConnect;
import org.project1.nhom8.util.data.QueryGenerator;

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

   

    public Connection getConnection() {
        return connection;
    }

    public QueryGenerator<TTable, TId> getQueryGenerator() {
        return queryGenerator;
    }
    

    public List<TTable> findAll() {
        return this.queryGenerator.executeFindAll(connection);
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

    public int count() {
        return this.queryGenerator.executeCountAll(connection);
    }

    public TTable findById(TId id) {
        return this.queryGenerator.executeFindById(connection, id);
    }

    public int pageCount(int items) {
        return this.count() / items;
    }
}

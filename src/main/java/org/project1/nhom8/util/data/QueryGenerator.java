/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.util.data;

import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ngtnthori03
 */
public class QueryGenerator<TTable, TId> {

    private final String table_namw;

    private final List<String> fields;

    private final List<Field> fieldRef;

    private final Class<TTable> table;

    private final Field idField;

    private final List<Field> generatedFields;

    public QueryGenerator(Class<TTable> _table) {
        this.table = _table;
        this.table_namw = this.table.getAnnotation(DataTable.class).name();
        this.fields = Arrays.asList(this.table.getDeclaredFields())
                .stream()
                .filter((o) -> (o.isAnnotationPresent(DataField.class)))
                .map((o) -> (o.getAnnotation(DataField.class).name()))
                .collect(Collectors.toList());

        this.fieldRef = Arrays.asList(this.table.getDeclaredFields())
                .stream()
                .filter((o) -> (o.isAnnotationPresent(DataField.class)))
                .collect(Collectors.toList());

        this.idField = Arrays.asList(this.table.getDeclaredFields())
                .stream()
                .filter((o) -> (o.isAnnotationPresent(DataField.class)))
                .findFirst().get();

        this.generatedFields = Arrays.asList(this.table.getDeclaredFields())
                .stream()
                .filter((o) -> (o.isAnnotationPresent(DataGenerated.class)))
                .collect(Collectors.toList());
    }

    public TTable map(ResultSet resultSet) throws SQLException {

        TTable result = null;

        try {
            result = this.table.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (!resultSet.isBeforeFirst()) {
//            return null;
//        }

        for (Field i : this.fieldRef) {
            i.setAccessible(true);
            try {
                i.set(result, resultSet.getObject(i.getAnnotation(DataField.class).name()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public TTable mapGeneratedFields(ResultSet resultSet, TTable object) throws SQLException {

        for (Field i: generatedFields) {
            i.setAccessible(true);
            try {
                i.set(object, resultSet.getObject(idField.getAnnotation(DataField.class).name()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return object;
    }

    public String generateSelectAllQuery() {

        StringBuilder sb = new StringBuilder(" SELECT \n");
        for (int i = 0; i < fields.size() - 1; ++i) {
            sb.append("\t" + table_namw + "." + fields.get(i));
            sb.append(",\n");
        }
        sb.append("\t" + table_namw + "." + fields.get(fields.size() - 1));

        sb.append("\n FROM "
                .concat(table_namw));

        System.out.println("\n\n\n" + sb + "\n\n\n");

        return sb.toString();
    }

    public String generateInsertQuery() {
        StringBuilder sb = new StringBuilder(" INSERT INTO " + table_namw);
        sb.append(" ( ");

        for (int i = 0; i < this.fields.size() - 1; ++i) {
            if (!this.fieldRef.get(i).isAnnotationPresent(DataGenerated.class)) {
                sb.append(this.fields.get(i) + ", ");
            }
        }

        sb.append(this.fields.get(this.fields.size() - 1));
        sb.append(" ) ");
        sb.append("\n\tVALUES (");

        for (int i = 0; i < this.fields.size(); ++i) {

            if (this.fieldRef.get(i).isAnnotationPresent(DataGenerated.class)) {
                continue;
            }

            sb.append(" ? ");
            if (i < this.fields.size() - 1) {
                sb.append(" , ");
            }
        }
        sb.append(" ) \n");

        System.out.println("\n\n\n" + sb + "\n\n\n");
        return sb.toString();
    }

    public String generateUpdateQuery() {

        StringBuilder sb = new StringBuilder(" UPDATE " + table_namw + " ");
        sb.append(" SET ");

        for (int i = 0; i < this.fields.size(); ++i) {
            sb.append(this.fields.get(i) + " = ?");

            if (i < this.fields.size() - 1) {
                sb.append(" , ");
            }
        }

        DataField idName = this.idField.getAnnotation(DataField.class);

        sb.append(" WHERE " + idName.name() + " = ?");

        System.out.println("\n\n\n" + sb + "\n\n\n");
        return sb.toString();
    }

    public String genFindById() {

        StringBuilder sb = new StringBuilder(
                this.generateSelectAllQuery()
        );

        DataField idName = idField.getAnnotation(DataField.class);

        if (Optional.of(idName).isPresent()) {

            sb.append(" WHERE ")
                    .append(table_namw + "." + idName.name() + " = ?");

        }

        System.out.println("\n\n\n" + sb + "\n\n\n");

        return sb.toString();
    }

    public PreparedStatement generatePrepareSeatement(PreparedStatement prepStat, Object... o) {

        try {
            for (int i = 0; i < o.length; ++i) {
                prepStat.setObject(i + 1, o);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prepStat;

    }

    public List<TTable> executeFindAll(Connection connection) {

        List<TTable> ret = new ArrayList<>();

        ResultSet resultSet = null;

        try {

            resultSet = connection.prepareStatement(
                    this.generateSelectAllQuery()
            ).executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    ret.add(this.map(resultSet));
                }
            }

            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public TTable executeInsert(Connection connection, TTable obj) {

        // take fields that not have @DataGenerated
        List<Field> insertedFields = this.fieldRef
                .stream().filter(o -> !o.isAnnotationPresent(DataGenerated.class))
                .toList();

        try {
            PreparedStatement preStat = connection.prepareStatement(
                    this.generateInsertQuery()
                    , generatedFields.stream()
                                    .map(o -> o.getAnnotation(DataField.class).name())
                            .toArray(String[]::new)
            );

            for (int i = 0; i < insertedFields.size(); ++i) {

                if (!insertedFields.get(i).isAnnotationPresent(DataGenerated.class)) {

                    insertedFields.get(i).setAccessible(true);

                    preStat.setObject(i + 1,
                            insertedFields.get(i).get(obj));
                }
            }

            if (preStat.executeUpdate() == 0) {
                return null;
            }

            ResultSet resultSet = preStat.getGeneratedKeys();

//            if (resultSet.next()) {
//                return mapGeneratedFields(resultSet, obj);
//            }

            return obj;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean executeUpdate(Connection connection, TTable object) {

        try {

            PreparedStatement preStat = connection.prepareStatement(
                    generateUpdateQuery()
            );

            try {
                for (int i = 0; i < this.fieldRef.size(); ++i) {
                    this.fieldRef.get(i).setAccessible(true);

                    preStat.setObject(i + 1, this.fieldRef.get(i).get(object));
                }

                this.idField.setAccessible(true);
                preStat.setObject(this.fieldRef.size() + 1,
                        this.idField.get(object));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            return preStat.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

    public TTable executeFindById(Connection connection, TId id) {

        try {

            PreparedStatement preStat = connection.prepareStatement(
                    this.genFindById()
            );

            preStat.setObject(1, id);

            ResultSet resultSet = preStat.executeQuery();

            if (resultSet.next()) {
                return this.map(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<TTable> executeCustomSelectAll(Connection connection, String afterSelect, Object... args) {
        String query = this.generateSelectAllQuery() + "\n" + afterSelect;

        List<TTable> ret = new ArrayList<>();

        ResultSet resultSet = null;

        PreparedStatement preStat = null;

        try {

            preStat = connection.prepareStatement(query);

            for (int i = 0; i < args.length; ++i) {
                preStat.setObject(i + 1, args);
            }

            resultSet = preStat.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    ret.add(this.map(resultSet));
                }
            }

            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public Integer executeCountAll(Connection connection) {

        String query = "SELECT" +
                "\t count(*) AS countAll" + "\nFROM " +
                this.table.getAnnotation(DataTable.class).name();

        ResultSet resultSet = null;

        try {
            resultSet = connection.prepareStatement(query)
                    .executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("countAll");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }
}

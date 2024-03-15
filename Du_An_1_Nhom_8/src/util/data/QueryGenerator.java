/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ngtnthori03
 */
public class QueryGenerator<TTable extends Object> {

    private String table_namw;
    private List<String> fields;

    private List<Field> fieldRef;
    private Class<TTable> table;

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
                .collect(Collectors.toList());;
    }

    public TTable map(ResultSet resultSet) throws SQLException {

        TTable result = null;

        try {
            result = this.table.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (!resultSet.next()) {
            return null;
        }

        for (Field i : this.fieldRef) {
            i.setAccessible(true);
            try {
                i.set(result, resultSet.getObject(i.getAnnotation(DataField.class).name()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            i.setAccessible(false);
        }
        return result;
    }

    public String generateSelectAllQuery() {

        StringBuilder sb = new StringBuilder(" SELECT \n");
        for (int i = 0; i < fields.size() - 1; ++i) {
            sb.append("\t" + table_namw + "." + fields.get(i));
            sb.append(",\n");
        }
        sb.append("\t" + table_namw + "." + fields.get(fields.size() - 1));

        sb.append(new String("\n FROM ")
                .concat(table_namw));

        System.out.println("\n\n\n" + sb.toString() + "\n\n\n");

        return sb.toString();
    }

    public String generateInsertQuery() {
        StringBuilder sb = new StringBuilder(" INSERT INTO " + table_namw);
        sb.append(" ( ");
        for (int i = 0; i < this.fields.size() - 1; ++i) {
            sb.append(this.fields.get(i) + ", ");
        }
        sb.append(this.fields.get(this.fields.size() - 1));
        sb.append(" ) ");
        sb.append("\n\tVALUES (");
        for (int i = 0; i < this.fields.size(); ++i) {
            sb.append(" ? ");
            if (i < this.fields.size() - 1) {
                sb.append(" , ");
            }
        }
        sb.append(" ) \n");

        System.out.println("\n\n\n" + sb.toString() + "\n\n\n");
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

        sb.append(" WHERE " + this.fields.get(0) + " = ?");
        return sb.toString();
    }

    public PreparedStatement generatePrepareSeatement(PreparedStatement prepStat, Object ...o) {


        try {
            for (int i = 0; i < o.length; ++i) {
                prepStat.setObject(i+1, o);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prepStat;

    }

    public List<TTable> executeFindAll(Connection connection) {

        List<TTable> ret = new ArrayList<>();

        TTable obj = null;

        ResultSet resultSet = null;

        try {

            resultSet = connection.prepareStatement(
                    this.generateSelectAllQuery()
            ).executeQuery();

            while (resultSet.isBeforeFirst()) {
                obj =  this.table.getConstructor().newInstance();

                ret.add(this.map(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return ret;
    }

    public TTable executeInsert(Connection connection, TTable obj) {

        try {
            PreparedStatement preStat = connection.prepareStatement(
                    this.generateInsertQuery()
            );

            for (int i = 0; i < this.fieldRef.size(); ++i) {
                this.fieldRef.get(i).setAccessible(true);

                preStat.setObject(i+1,
                        this.fieldRef.get(i).get(obj));
            }

            if (preStat.executeUpdate() > 0) {
                return obj;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

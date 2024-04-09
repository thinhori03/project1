package org.project1.nhom8.util.swing;

import org.project1.nhom8.util.data.convert.DateFormat;
import org.project1.nhom8.util.data.convert.DefaultConverter;
import org.project1.nhom8.util.data.visual.DataHeader;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralTableModel<T> {

    private Class<T> modelClass;

    public GeneralTableModel(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public DefaultTableModel toTableModel(List<T> models) {
        DefaultTableModel defaultTableModel = new ReadableTableModel();

        List<Field> fields = Arrays.asList(modelClass.getDeclaredFields())
                .stream()
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .collect(Collectors.toList());


        defaultTableModel.setColumnCount(0);

        for (Field f : fields) {
            defaultTableModel.addColumn(f.getAnnotation(DataHeader.class).name().trim());
        }

        defaultTableModel.setRowCount(0);

        List<String> rowData = new ArrayList<>();

        for (T model : models) {
            rowData = new ArrayList<>();
            try {
                for (Field j : fields) {
                    j.setAccessible(true);
                    if (j.getType().equals(Date.class)
                            && j.isAnnotationPresent(DateFormat.class)) {
                        rowData.add(DefaultConverter.VietnameseDateFormat((Date) j.get(model)));
                    } else if (j.get(model) == null) {
                        rowData.add("");
                    } else {
                        rowData.add(j.get(model).toString());
                    }
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

            defaultTableModel.addRow(rowData.toArray());
        }

        return defaultTableModel;
    }
}

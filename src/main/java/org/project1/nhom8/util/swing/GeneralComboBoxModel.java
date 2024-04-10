package org.project1.nhom8.util.swing;

import org.project1.nhom8.util.data.convert.DateFormat;
import org.project1.nhom8.util.data.convert.DefaultConverter;
import org.project1.nhom8.util.data.visual.DataHeader;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralComboBoxModel<T> {

    private final Class<T> modelClass;

    public GeneralComboBoxModel(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public DefaultComboBoxModel<String> toComboBoxModel(List<T> models, String separator) throws IllegalAccessException {

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        List<Field> fields = Arrays.asList(modelClass.getDeclaredFields())
                .stream()
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .collect(Collectors.toList());

        comboBoxModel.removeAllElements();

        StringBuilder itemBuilder = null;

        for (T model : models) {
            itemBuilder = new StringBuilder();

            for (Field f : fields) {

                f.setAccessible(true);

                if (f.get(model) == null) {
                    continue;
                }

                itemBuilder.append(f.getAnnotation(DataHeader.class).name().trim())
                        .append(": ");

                if (f.getType().equals(Date.class)
                        && f.isAnnotationPresent(DateFormat.class)) {
                    itemBuilder.append(DefaultConverter.VietnameseDateFormat((Date) f.get(model)));
                } else {
                    itemBuilder.append(f.get(model).toString());
                }

                itemBuilder.append(" ").append(separator).append(" ");
            }
            comboBoxModel.addElement(itemBuilder.toString());
        }

        return comboBoxModel;
    }

}

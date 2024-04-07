package org.project1.nhom8.util.swing;

import javax.swing.table.DefaultTableModel;

public class ReadableTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

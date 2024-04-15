package org.project1.nhom8.util;

public enum InvoiceCreateUpdateState {

    CREATE("tạo hóa đơn"), UPDATE("cập nhật thông tin");

    final String value;

    InvoiceCreateUpdateState(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

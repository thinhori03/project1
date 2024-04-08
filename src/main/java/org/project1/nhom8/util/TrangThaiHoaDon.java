package org.project1.nhom8.util;

public enum TrangThaiHoaDon {

    DA_THANH_TOAN("Đã thanh toán"), DA_HUY("Đã hủy");

    String value;

    TrangThaiHoaDon(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

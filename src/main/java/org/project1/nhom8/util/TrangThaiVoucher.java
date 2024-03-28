package org.project1.nhom8.util;

public enum TrangThaiVoucher {

    DANG_HOAT_DONG ("đang hoạt động"),
    DA_HUY("đã hủy");

    private final String value;

    TrangThaiVoucher(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

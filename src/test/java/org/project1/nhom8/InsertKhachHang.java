package org.project1.nhom8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.model.KhachHangModel;
import org.project1.nhom8.repository.KhachHangConnection;

public class InsertKhachHang {
    private final KhachHangConnection khachHangConnection = new KhachHangConnection();

    public InsertKhachHang() {

    }

    @Test
    public void insert() {
        var kh = new KhachHangModel();
        System.out.println(khachHangConnection.findByNameAndPhoneNumber("deo phai khach hang", "none").getMaKH());
    }
}

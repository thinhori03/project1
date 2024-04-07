package org.project1.nhom8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.model.KhuyenMai;
import org.project1.nhom8.service.KhuyenMaiService;

public class TestKhuyenMai {

    private final KhuyenMaiService khuyenMaiService;

    public TestKhuyenMai() {
        this.khuyenMaiService = new KhuyenMaiService();
    }

    @Test
    public void getAll() {
        for (KhuyenMai km : khuyenMaiService.getall()) {
            System.out.println(km.getGia());
        }
    }
}

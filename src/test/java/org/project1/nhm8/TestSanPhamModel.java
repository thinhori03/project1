package org.project1.nhm8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.repository.SanPhamRepository;

public class TestSanPhamModel {

    private SanPhamRepository sanPhamRepository;

    public TestSanPhamModel() {
        this.sanPhamRepository = new SanPhamRepository();
    }

    @Test
    @DisplayName("fina all SanPham Model")
    public void findAll() {
        System.out.println(sanPhamRepository.findAll().size());
    }
}

package org.project1.nhm8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.repository.MauSacRepository;

public class TestMauSac {

    private MauSacRepository mauSacRepository;

    public TestMauSac() {
        this.mauSacRepository = new MauSacRepository();
    }

    @Test
    public void findByName() {
        System.out.println(this.mauSacRepository.findByTen("Đỏ").getMamau());
    }
}

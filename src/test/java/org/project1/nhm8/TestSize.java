package org.project1.nhm8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.repository.SizeRepository;

public class TestSize {


    private SizeRepository sizeRepository;

    public TestSize() {
        this.sizeRepository = new SizeRepository();
    }

    @Test
    public void findByName() {
        System.out.println(this.sizeRepository.findByTen("40").getTensize());
    }
}

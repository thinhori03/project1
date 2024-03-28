package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.VoucherRepository;

public class TestVoucherModel {

    private VoucherRepository voucherRepository;

    public TestVoucherModel() {
        this.voucherRepository = new VoucherRepository();
    }

    @Test
    public void insertVoucher() {

        Assertions.assertTrue(this.voucherRepository
                .count() > 0);
    }
}

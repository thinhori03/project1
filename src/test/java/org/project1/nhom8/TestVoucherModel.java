package org.project1.nhom8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.repository.VoucherRepository;

public class TestVoucherModel {

    private final VoucherRepository voucherRepository;

    public TestVoucherModel() {
        this.voucherRepository = new VoucherRepository();
    }

    @Test
    public void getApple() {
        System.out.println(voucherRepository.getVoucherToApply((double) 2000000).getGiaTri());
    }
}

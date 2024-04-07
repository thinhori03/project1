package org.project1.nhom8.service;

import org.project1.nhom8.repository.VoucherRepository;

public class VoucherService {
    private final VoucherRepository voucherRepository;

    public VoucherService() {
        voucherRepository = new VoucherRepository();
    }
}

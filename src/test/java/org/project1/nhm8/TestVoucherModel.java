package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.VoucherRepository;

import java.util.Date;

public class TestVoucherModel {

    private final VoucherRepository voucherRepository;

    public TestVoucherModel() {
        this.voucherRepository = new VoucherRepository();
    }

//    @Test
//    @DisplayName("insert")
//    public void insert() {
//        Assertions.assertTrue(voucherRepository.add(VoucherModel.builder()
//                .maVoucher("V" + voucherRepository.count() + 1)
//                .diauKien(100000F)
//                .ngayTao(new Date())
//                .ngayBatDau(new Date())
//                .ngayKetThuc(new Date())
//                .giaTri(20000F)
//                .build()) != null);
//    }

    @Test
    @DisplayName("insert")
    public void insert() {
        Assertions.assertTrue(voucherRepository.add(VoucherModel.builder()
                .maVoucher("V" + voucherRepository.count() + 1)
                .diauKien(100000F)
                .ngayTao(new Date())
                .ngayBatDau(new Date())
                .ngayKetThuc(new Date())
                .giaTri(20000F)
                .build()) != null);
    }

    @Test
    public void insertVoucher() {

        int count = this.voucherRepository
                .count();

        System.out.println(count);
        Assertions.assertTrue(count > 0);
    }
}

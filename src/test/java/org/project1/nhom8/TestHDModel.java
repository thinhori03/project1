package org.project1.nhom8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.model.HDCTModel;
import org.project1.nhom8.repository.HDCTRepository;
import org.project1.nhom8.repository.HoaDonRepository;

public class TestHDModel {

    private HoaDonRepository hoaDonRepository;

    private HDCTRepository hdctRepository;

    public TestHDModel() {
        this.hoaDonRepository = new HoaDonRepository();
        this.hdctRepository = new HDCTRepository();
    }

    @Test
    public void getAllHDCT() {
        for (HDCTModel hdctm : hdctRepository.findBymaHD("HD1")) {
            System.out.println(hdctm.getMaHDCT());
        }
    }
}

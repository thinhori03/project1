package org.project1.nhom8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.service.HoaDonService;

public class TestHoaDonService {

    private final HoaDonService hoaDonService;

    public TestHoaDonService() {
        this.hoaDonService = new HoaDonService();
    }

    @Test
    public void GenId() {
        System.out.println(hoaDonService.taoMaHoaDon());
    }
}

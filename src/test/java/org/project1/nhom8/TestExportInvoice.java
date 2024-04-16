package org.project1.nhom8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.service.HoaDonService;

import java.io.IOException;

public class TestExportInvoice {

    private final HoaDonService hoaDonService = new HoaDonService();

    @Test
    public void export() throws IOException {
        hoaDonService.export("HD1", System.getenv("NTZOE_HOME_DIR") + "/store");
    }
}

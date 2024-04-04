package org.project1.nhm8;

import org.project1.nhom8.model.HDCTModel;
import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.HDCTKMRepository;
import org.project1.nhom8.repository.VoucherRepository;
import org.project1.nhom8.service.HoaDonService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestHoaDonModel {

    private final HoaDonService hoaDonService;

    private final HDCTKMRepository hdctkmRepository;

    private final VoucherRepository voucherRepository;

    private final GiaRepository giaRepository;

    public TestHoaDonModel() {
        hoaDonService = new HoaDonService();

        hdctkmRepository = new HDCTKMRepository();

        voucherRepository = new VoucherRepository();

        giaRepository = new GiaRepository();
    }

    public void createInvoice() {

        String maHD = hoaDonService.taoMaHoaDon();

        HoaDonModel hoaDon = new HoaDonModel();

        hoaDon.setMaHoaDon(maHD);

        hoaDon.setMaVoucher("V1");
        hoaDon.setMaKH(1);
        hoaDon.setMaNV(1);

        hoaDon.setNgayTao(new Date());
        hoaDon.setNgayThanhToan(new Date());

        hoaDon.setPhuongThuc("tiền mặt");

        hoaDon.setTrangThai("đã thanh toán");

        // invoice detail

        HDCTModel hdct1 = new HDCTModel();
        hdct1.setMaHDCT(hdctkmRepository.count() + 1);
        hdct1.setMaSPCT(1);
        hdct1.setMaHoaDon(maHD);
        hdct1.setSoLuong(1);
        hdct1.setMaLSG(giaRepository.getgiaMoiNhat(1)
                .getMaLSG());

        List<HDCTModel> hdcts = Arrays.asList(
                hdct1
        );

        VoucherModel voucher = voucherRepository.findById("V1");

        hoaDonService.taoHoaDon(hoaDon, hdcts, voucher);
    }
}

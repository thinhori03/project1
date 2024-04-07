package org.project1.nhom8.service;

import org.project1.nhom8.model.HDCTModel;
import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.HDCTKMRepository;
import org.project1.nhom8.repository.HDCTRepository;
import org.project1.nhom8.repository.HoaDonRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HoaDonService {

    private final HoaDonRepository hoaDonRepository;

    private final HDCTRepository hdctRepository;

    private final HDCTKMRepository hdctkmRepository;

    private final SimpleDateFormat simpleDateFormat;

    public HoaDonService() {

        hoaDonRepository = new HoaDonRepository();

        hdctRepository = new HDCTRepository();

        hdctkmRepository = new HDCTKMRepository();

        simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    }

    public Boolean taoHoaDon(HoaDonModel hoaDon, List<HDCTModel> hdct, VoucherModel voucher) {

        // generated invoice id
        String maHD = taoMaHoaDon();

        hoaDon.setMaHoaDon(maHD);
        hoaDon.setMaVoucher(voucher.getMaVoucher());

        hoaDonRepository.add(hoaDon);

        for (HDCTModel hdctm : hdct) {
            hdctm.setMaHoaDon(maHD);

            hdctRepository.add(hdctm);
        }

        return true;
    }

    /**
     * @return mã mã hoa đơn mới
     */
    public String taoMaHoaDon() {
        return "HD" + (this.simpleDateFormat.format(new Date()));
    }

}

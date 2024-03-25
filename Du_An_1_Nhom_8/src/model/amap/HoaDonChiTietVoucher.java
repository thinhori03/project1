package model.amap;

import util.data.DataField;
import util.data.DataTable;
/**
 * @author ngtnthori03
 *
 * CREATE TABLE hoa_don_chi_tiet__khuyen_mai
 * (
 *     maHDCT int references HOA_DON_CHI_TIET (MAHDCT),
 *     maKM   VARCHAR(14) references KHUYEN_MAI_COUPON (maKM)
 * )
 */
@DataTable(name = "hoa_don_chi_tiet__khuyen_mai")
public class HoaDonChiTietVoucher {

    @DataField(name = "maHDCT")
    private int maHoaDonChiTiet;

    @DataField(name = "maKM")
    private String mavoucher;
//

    public HoaDonChiTietVoucher(int maHoaDonChiTiet, String mavoucher) {

        this.maHoaDonChiTiet = maHoaDonChiTiet;

        this.mavoucher = mavoucher;
    }

    public int getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public String getMavoucher() {
        return mavoucher;
    }

    public void setMaHoaDonChiTiet(int maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public void setMavoucher(String mavoucher) {
        this.mavoucher = mavoucher;
    }
}

package org.project1.nhom8.model.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataTable;

/**
 * @author ngtnthori03
 *
 * CREATE TABLE hoa_don_chi_tiet__khuyen_mai
 * (
 *     maHDCT int references HOA_DON_CHI_TIET (MAHDCT),
 *     maKM   VARCHAR(14) references KHUYEN_MAI_COUPON (maKM)
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

@DataTable(name = "hoa_don_chi_tiet__khuyen_mai")
public class HoaDonChiTietVoucher {

    @DataField(name = "maHDCT")
    private int maHoaDonChiTiet;

    @DataField(name = "maKM")
    private String mavoucher;
}

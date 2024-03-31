package org.project1.nhom8.model.map;

import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataTable;

/**
 * @author ngtnthori03
 * CREATE TABLE hoa_don_chi_tiet__khuyen_mai
 * (
 *     maHDCT int references HOA_DON_CHI_TIET (MAHDCT),
 *     maKM   VARCHAR(14) references KHUYEN_MAI_COUPON (maKM)
 * )
 */
@DataTable(name = "hoa_don_chi_tiet__khuyen_mai")
public class HDCTKM {

    @DataField(name = "maHDCT")
    private Integer maHDCT;

    @DataField(name = "maKM")
    private Integer maKM;
}

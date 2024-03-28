package org.project1.nhom8.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataGenerated;
import org.project1.nhom8.util.data.DataId;
import org.project1.nhom8.util.data.DataTable;

/**
 * CREATE TABLE HOA_DON_CHI_TIET
 * (
 *     MAHDCT  INT PRIMARY KEY identity (1,1),
 *     MAHD    varchar(20) references HOA_DON (MAHD),
 *     MASPCT  INT references SAN_PHAM_CHI_TIET (MASPCT),
 *     SOLUONG INT,
 *     MALSG   int -- giá hiện tại
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@DataTable(name = "HOA_DON_CHI_TIET")
public class HoaDonChiTietModel {

    @DataId
    @DataGenerated
    @DataField(name = "MAHDCT")
    private int maHDCT;

    @DataField(name = "MAHD")
    private int maHoaDon;

    @DataField(name = "MASPCT")
    private int SPCT;

    @DataField(name = "SOLUONG")
    private int soLuong;

    @DataField(name = "MAKM")
    private int maKM;

    @DataField(name = "MALSG")
    private int maLSG;
}
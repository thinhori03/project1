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

import java.util.Date;

/**
 * create table LICH_SU_GIA
 * (
 *     MALSG      int primary key,
 *     MASPCT     int references SAN_PHAM_CHI_TIET (MASPCT),
 *     GIA        float,
 *     NGAYUPDATE datetime,
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@DataTable(name = "LICH_SU_GIA")
public class GiaModel {

    @DataId
    @DataField(name = "MALSG")
    private int maLSG;

    @DataField(name = "MASPCT")
    private int maSPCT;

    @DataField(name = "GIA")
    private float gia;

    @DataField(name = "NGAYUPDATE")
    private Date ngayCapNhat;
}

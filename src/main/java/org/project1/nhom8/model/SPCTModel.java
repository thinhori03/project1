/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author Admin
 * CREATE TABLE SAN_PHAM_CHI_TIET
 * (
 *     MASPCT    INT PRIMARY KEY identity (1,1),
 *     SOLUONG   INT,
 *     MASIZE    int references SIZE (MASIZE),
 *     MAMAU     int references MAU_SAC (MAMAU),
 *     MASP      INT references SAN_PHAM (MASP),
 *     TRANGTHAI NVARCHAR(100)
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@DataTable(name = "SAN_PHAM_CHI_TIET")
public class SPCTModel {

    @DataId
    @DataGenerated
    @DataField(name = "MASPCT")
    private int maSPCT;

    @DataField(name = "SOLUONG")
    private int soluong;

    @DataField(name = "MASIZE")
    private int masize;

    @DataField(name = "MAMAU")
    private int maMauSac;

    @DataField(name = "MASP")
    private int maSP;

}

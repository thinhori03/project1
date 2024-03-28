/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataId;
import org.project1.nhom8.util.data.DataTable;

import java.util.Date;

/**
 *
 * @author ngtnthori03
 *
 * SQL table
 * CREATE TABLE HOA_DON
 * (
 *     MAHD          varchar(20) PRIMARY KEY,
 *     MAKH          INT references KHACH_HANG (MAKH),
 *     MANV          INT references NHAN_VIEN (MANV),
 *     NGAYTAO       DATETIME,
 *     NGAYTHANHTOAN DATETIME,
 *     TRANGTHAI     VARCHAR(100),
 *     PHUONGTHUC    VARCHAR(100),
 *     MAV           VARCHAR(10) references VOUCHER (MAV)
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@DataTable(name = "HOA_DON")
public class HoaDonModel {

    @DataId
    @DataField(name = "MAHD")
    private int maHoaDon;

    @DataField(name = "MAKH")
    private int maKH;

    @DataField(name = "MANV")
    private int maNV;

    @DataField(name = "TRANGTHAI")
    private String trangThai;

    @DataField(name = "PHUONGTHUC")
    private String phuongThuc;

    @DataField(name = "MAV")
    private String maVoucher;


    @DataField(name = "NGAYTAO")
    private Date ngayTao;

    @DataField(name = "NGAYTHANHTOAN")
    private Date ngayThanhToan;
}
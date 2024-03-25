/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import util.data.DataField;
import util.data.DataId;
import util.data.DataTable;

/**
 *
 * @author ngtnthori03
 *
 * CREATE TABLE VOUCHER
 * (
 *     MAV         VARCHAR(10) PRIMARY KEY,
 *     GIATRI      FLOAT,
 *     NGAYBATDAU  DATE,
 *     NGAYKETTHUC DATE,
 *     DIEUKIEN    FLOAT,
 *     ngay_tao    DATETIME,
 *     TRANG_THAI  VARCHAR(20) -- đang hoạt động đã hủy
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@DataTable(name = "VOUCHER")
public class VoucherModel {

    @DataId
    @DataField(name = "MAV")
    private String maVoucher;

    @DataField(name = "GIATRI")
    private double giaTri;

    @DataField(name = "NGAYBATDAU")
    private Date ngayBatDau;

    @DataField(name = "NGAYKETTHUC")
    private Date ngayKetThuc;

    @DataField(name = "DIEUKIEN")
    private double diauKien;

    @DataField(name = "ngay_tao")
    private Date ngayTao;

    @DataField(name = "TRANG_THAI")
    private String trangThai;

}
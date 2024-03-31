package org.project1.nhom8.model;


import org.project1.nhom8.util.data.DataField;
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

@DataTable(name = "LICH_SU_GIA")
public class GiaModel {

    @DataId
    @DataField(name = "MALSG")
    private int maLSG;

    @DataField(name = "MASPCT")
    private int maSPCT;

    @DataField(name = "GIA")
    private double gia;

    @DataField(name = "NGAYUPDATE")
    private Date ngayCapNhat;

    public GiaModel() {
    }

    public GiaModel(int maLSG, int maSPCT, double gia, Date ngayCapNhat) {
        this.maLSG = maLSG;
        this.maSPCT = maSPCT;
        this.gia = gia;
        this.ngayCapNhat = ngayCapNhat;
    }

    public int getMaLSG() {
        return maLSG;
    }

    public void setMaLSG(int maLSG) {
        this.maLSG = maLSG;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    
}

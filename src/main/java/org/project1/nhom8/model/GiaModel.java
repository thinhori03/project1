package org.project1.nhom8.model;

import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataId;
import org.project1.nhom8.util.data.DataTable;

import java.util.Date;


@DataTable(name = "LICH_SU_GIA")
public class GiaModel {

    @DataId
    @DataField(name = "MALSG")
    private int maLSG;

    @DataField(name = "MASPCT")
    private int maSPCT;

    @DataField(name = "GIA")
    private Double gia;

    @DataField(name = "NGAYBATDAU")
    private Date ngayCapNhat;

    @DataField(name = "NGAYkETTHUC")
    private Date ngayKetThuc;

    public GiaModel() {
    }

    public GiaModel(int maLSG, int maSPCT, Double gia, Date ngayCapNhat, Date ngayKetThuc) {
        this.maLSG = maLSG;
        this.maSPCT = maSPCT;
        this.gia = gia;
        this.ngayCapNhat = ngayCapNhat;
        this.ngayKetThuc = ngayKetThuc;
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

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}

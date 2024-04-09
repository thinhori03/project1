package org.project1.nhom8.model;

import org.project1.nhom8.util.data.DataField;
import org.project1.nhom8.util.data.DataGenerated;
import org.project1.nhom8.util.data.DataId;
import org.project1.nhom8.util.data.DataTable;

/**
 * CREATE TABLE HOA_DON_CHI_TIET
 * (
 * MAHDCT  INT PRIMARY KEY identity (1,1),
 * MAHD    varchar(20) references HOA_DON (MAHD),
 * MASPCT  INT references SAN_PHAM_CHI_TIET (MASPCT),
 * SOLUONG INT,
 * MALSG   int -- giá hiện tại
 * MAKM VARCHAR(14)
 * )
 */

@DataTable(name = "HOA_DON_CHI_TIET")
public class HDCTModel {

    @DataId
    @DataGenerated
    @DataField(name = "MAHDCT")
    private int maHDCT;

    @DataField(name = "MAHD")
    private String maHoaDon;

    @DataField(name = "MASPCT")
    private int maSPCT;

    @DataField(name = "SOLUONG")
    private int soLuong;

    @DataField(name = "MALSG")
    private int maLSG;

    @DataField(name = "MAKM")
    private String maKM;

    public HDCTModel() {

    }

    public HDCTModel(int maHDCT, String maHoaDon, int maSPCT, int soLuong, int maLSG, String maKM) {
        this.maHDCT = maHDCT;
        this.maHoaDon = maHoaDon;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.maLSG = maLSG;
        this.maKM = maKM;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLSG() {
        return maLSG;
    }

    public void setMaLSG(int maLSG) {
        this.maLSG = maLSG;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }


}
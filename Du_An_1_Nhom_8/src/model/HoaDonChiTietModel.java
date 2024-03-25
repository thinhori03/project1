package model;

import util.data.DataField;
import util.data.DataGenerated;
import util.data.DataId;
import util.data.DataTable;

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


    public HoaDonChiTietModel() {

    }

    public HoaDonChiTietModel(int maHDCT, int maHoaDon, int SPCT, int soLuong, int maKM, int maLSG) {
        this.maHDCT = maHDCT;
        this.maHoaDon = maHoaDon;
        this.SPCT = SPCT;
        this.soLuong = soLuong;
        this.maKM = maKM;
        this.maLSG = maLSG;
    }

    /**
     * constructors
     */

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSPCT() {
        return SPCT;
    }

    public void setSPCT(int SPCT) {
        this.SPCT = SPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public int getMaLSG() {
        return maLSG;
    }

    public void setMaLSG(int maLSG) {
        this.maLSG = maLSG;
    }
}
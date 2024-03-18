package model;

import util.data.DataField;
import util.data.DataGenerate;
import util.data.DataId;
import util.data.DataTable;

import java.util.Date;

/**
 * CREATE TABLE HOA_DON_CHI_TIET
 * (
 *     MAHDCT   INT IDENTITY (1,1) PRIMARY KEY,
 *     MAHD     INT references HOA_DON (MAHD),
 *     MASPCT   INT references SAN_PHAM_CHI_TIET (MASPCT),
 *     SOLUONG  INT,
 *     MAUSAC   VARCHAR(100),
 *     THOIGIAN DATE,
 *     MAKM     INT references KHUYEN_MAI (MAKM),
 *     MALSG    int references LICH_SU_GIA (MALSG)
 * )
 */

@DataTable(name = "HOA_DON_CHI_TIET")
public class HoaDonChiTietModel {

    @DataId
    @DataGenerate
    @DataField(name = "MAHDCT")
    private int maHDCT;

    @DataField(name = "MAHD")
    private int maHoaDon;

    @DataField(name = "MASPCT")
    private int SPCT;

    @DataField(name = "SOLUONG")
    private int soLuong;

    @DataField(name = "MAUSAC")
    private String mauSac;

    @DataField(name = "THOIGIAN")
    private Date thoiGian;

    @DataField(name = "MAKM")
    private int maKM;

    @DataField(name = "MALSG")
    private int maLSG;


    public HoaDonChiTietModel() {

    }

    public HoaDonChiTietModel(int maHDCT, int maHoaDon, int SPCT, int soLuong, String mauSac, Date thoiGian, int maKM, int maLSG) {
        this.maHDCT = maHDCT;
        this.maHoaDon = maHoaDon;
        this.SPCT = SPCT;
        this.soLuong = soLuong;
        this.mauSac = mauSac;
        this.thoiGian = thoiGian;
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

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
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

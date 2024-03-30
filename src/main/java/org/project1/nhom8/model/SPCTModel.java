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

@Builder
@DataTable(name = "SAN_PHAM_CHI_TIET")

public class SPCTModel {
    
    @DataId
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

    public SPCTModel() {
    }

    public SPCTModel(int maSPCT, int soluong, int masize, int maMauSac, int maSP) {
        this.maSPCT = maSPCT;
        this.soluong = soluong;
        this.masize = masize;
        this.maMauSac = maMauSac;
        this.maSP = maSP;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMasize() {
        return masize;
    }

    public void setMasize(int masize) {
        this.masize = masize;
    }

    public int getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(int maMauSac) {
        this.maMauSac = maMauSac;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    
}

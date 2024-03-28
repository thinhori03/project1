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
import org.project1.nhom8.util.data.DataTable;

import javax.xml.namespace.QName;

/**
 * @author Admin
 * <p>
 * CREATE TABLE KHUYEN_MAI_COUPON
 * (
 * MAKM        VARCHAR(14) PRIMARY KEY,
 * NGAYBATDAU  DATE,
 * NGAYKETTHUC DATE,
 * SOLUONG     INT,
 * GIA         FLOAT,
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@DataTable(name = "KHUYEN_MAI_COUPON")
public class KhuyenMai {
    @DataField(name = "MAKM")
    String makm;

    @DataField(name = "NGAYBATDAU")
    String ngayBd;

    @DataField(name = "NGAYKETTHUC")
    String ngayKt;

    @DataField(name = "SOLUONG")
    int soluong;

    @DataField(name = "GIA")
    float gia;

    public Object[] toDataRow() {
        return new Object[]{
                this.getMakm(), this.getNgayBd(), this.getNgayKt(), this.getSoluong(), this.getGia()
        };
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "makm=" + makm + ", ngayBd=" + ngayBd + ", ngayKt=" + ngayKt + ", soluong=" + soluong + ", gia=" + gia + '}';
    }
}

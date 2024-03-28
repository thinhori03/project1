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

/**
 *
 * @author Admin
 *
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
public class SanPhamChiTietModel {

    private int maSPCT;
    private int soluong;
    private int masize;
    private int maMauSac;
    private int maSP;
    private String trangThai;
}

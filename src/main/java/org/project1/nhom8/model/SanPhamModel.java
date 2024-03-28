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
 * CREATE TABLE SAN_PHAM
 * (
 *     MASP  INT PRIMARY KEY identity (1,1),
 *     TENSP NVARCHAR(100)
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SanPhamModel {
    private int masp;
    private String tensp;
//   private  String trangthai;
}

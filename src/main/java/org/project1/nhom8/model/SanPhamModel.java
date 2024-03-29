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
@DataTable(name = "SAN_PHAM")
public class SanPhamModel {
    
    @DataId
    @DataGenerated
    @DataField(name = "MASP")
    private int masp;
    
    @DataField(name = "TENSP")
    private String tensp;

    @DataField(name = "TRANGTHAI")
    private String trangThai;
}

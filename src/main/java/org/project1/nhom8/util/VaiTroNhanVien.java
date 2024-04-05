/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.util;

/**
 *
 * @author ngtnthori03
 */
public enum VaiTroNhanVien {
    
    QUANR_LY ("Quản lý"),
    nhan_vien("Nhân viên");

    private final String value;

    VaiTroNhanVien(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

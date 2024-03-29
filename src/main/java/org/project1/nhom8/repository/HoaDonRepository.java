/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.repository;

import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.service.DBConnect;
import org.project1.nhom8.util.data.QueryGenerator;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author ngtnthori03
 */
public class HoaDonRepository extends GeneralRepository<HoaDonModel, String> {

    public HoaDonRepository() {
        super(HoaDonModel.class);
    }
}

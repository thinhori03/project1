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

/**
 *
 * @author Admin
 * CREATE TABLE MAU_SAC
 * (
 *     MAMAU  int PRIMARY KEY identity (1,1),
 *     TENMAU NVARCHAR(100) unique
 * )
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@DataTable(name = "MAU_SAC")
public class MauSacModel {

    @DataField(name = "MAMAU")
    private int id_Mamau;

    @DataField(name = "TENMAU")
    private String tenmau;
}

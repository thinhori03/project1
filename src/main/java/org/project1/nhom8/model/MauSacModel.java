/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;

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
@DataTable (name="MAU_SAC")
public class MauSacModel {
    @DataField(name ="MAMAU")
    int mamau;
    @DataField(name="TENMAU")
    String tenmau;

    public MauSacModel() {
    }

    public MauSacModel(int mamau, String tenmau) {
        this.mamau = mamau;
        this.tenmau = tenmau;
    }

    public int getMamau() {
        return mamau;
    }

    public void setMamau(int mamau) {
        this.mamau = mamau;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    
    
   
}

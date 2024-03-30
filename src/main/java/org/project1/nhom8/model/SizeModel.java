/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
 * CREATE TABLE SIZE
 * (
 *     MASIZE  int PRIMARY KEY identity (1,1),
 *     TENSIZE NVARCHAR(10) unique
 * )
 */
@DataTable(name = "SIZE")
public class SizeModel {
    @DataField (name ="MASIZE") 
    int masize;
    @DataField(name="TENSIZE")
    String tensize;

    public SizeModel() {
    }

    public SizeModel(int masize, String tensize) {
        this.masize = masize;
        this.tensize = tensize;
    }

    public int getMasize() {
        return masize;
    }

    public void setMa(int masize) {
        this.masize = masize;
    }

    public String getTensize() {
        return tensize;
    }

    public void setTensize(String tensize) {
        this.tensize = tensize;
    }
    
}

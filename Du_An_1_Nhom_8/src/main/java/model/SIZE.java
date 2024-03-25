/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class SIZE {
    int id_Masize;
    String tensize;

    public SIZE() {
    }

    public SIZE(int id_Masize, String tensize) {
        this.id_Masize = id_Masize;
        this.tensize = tensize;
    }

    public int getId_Masize() {
        return id_Masize;
    }

    public void setId_Masize(int id_Masize) {
        this.id_Masize = id_Masize;
    }

    public String getTensize() {
        return tensize;
    }

    public void setTensize(String tensize) {
        this.tensize = tensize;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class MAU_SAC {
    int id_Mamau;
    String tenmau;

    public MAU_SAC() {
    }

    public MAU_SAC(int id_Mamau, String tenmau) {
        this.id_Mamau = id_Mamau;
        this.tenmau = tenmau;
    }

    public int getId_Mamau() {
        return id_Mamau;
    }

    public void setId_Mamau(int id_Mamau) {
        this.id_Mamau = id_Mamau;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.HoaDonModel;

/**
 *
 * @author ngtnthori03
 */
public class HoaDonRepository 
        extends GeneralRepository<HoaDonModel, String> {
    
    public HoaDonRepository() {
        super (HoaDonModel.class);
    }
}

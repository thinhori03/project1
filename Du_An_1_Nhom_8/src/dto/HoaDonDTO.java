/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;
import model.HoaDonChiTietModel;
import model.HoaDonModel;

/**
 *
 * @author ngtnthori03
 */
public class HoaDonDTO {
    
    private HoaDonModel hoaDonModel;
    
    private List<HoaDonChiTietModel> hoaDonChiTietModels;

    public HoaDonDTO(HoaDonModel hoaDonModel, List<HoaDonChiTietModel> hoaDonChiTietModels) {
        this.hoaDonModel = hoaDonModel;
        this.hoaDonChiTietModels = hoaDonChiTietModels;
    }\
    
    public HoaDonModel getHoaDonModel() {
        return hoaDonModel;
    }

    public void setHoaDonModel(HoaDonModel hoaDonModel) {
        this.hoaDonModel = hoaDonModel;
    }

    public List<HoaDonChiTietModel> getHoaDonChiTietModels() {
        return hoaDonChiTietModels;
    }

    public void setHoaDonChiTietModels(List<HoaDonChiTietModel> hoaDonChiTietModels) {
        this.hoaDonChiTietModels = hoaDonChiTietModels;
    }
}

package repository;

import model.HoaDonChiTietModel;

public class HoaDonChiTietRepository 
        extends GeneralRepository<HoaDonChiTietModel, Integer> {
    
    public HoaDonChiTietRepository() {
        super(HoaDonChiTietModel.class);
    }
}

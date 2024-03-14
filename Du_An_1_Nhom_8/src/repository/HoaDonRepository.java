/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.HoaDonModel;
import java.sql.Connection;
import model.VoucherModel;
import service.DBConnect;
import util.data.QueryGenerator;

/**
 *
 * @author ngtnthori03
 */
public class HoaDonRepository {
    
    private Connection connection;
    
    public HoaDonRepository() {
        this.connection = DBConnect.getConnection();
    }
    
    public List<HoaDonModel> getAll() {
        
        return new ArrayList<>();
    }
    
    public HoaDonModel getByMaHoaDon(int maHoadon) {
        return null;
    }
    
    public boolean add(HoaDonModel hoaDon) {
        
        return false;
    }
    
    /*
        maNhanVien: int
    */
    public List<HoaDonModel> getByMaNhanVien(int maNhanvien) {
        
        return new ArrayList<>();
    }
    
    /*
    maKhachhang: int
    */
    public List<HoaDonModel> getByMaKhachHang(int maKhachHang) {
        
        return new ArrayList<>();
    }
}

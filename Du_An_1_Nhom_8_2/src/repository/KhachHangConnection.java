/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import model.KhachHangModel;
import service.DBConnect;

/**
 *
 * @author acer
 */
public class KhachHangConnection {

    DBConnect dBConnect;

    public ArrayList<KhachHangModel> getAll() {
        String sql = "Select * from KHACH_HANG";
        ArrayList<KhachHangModel> list = new ArrayList<>();
        try (Connection con = dBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer ma = rs.getInt("MAKH");
                String ten = rs.getString("TENKH");
                Integer sdt = rs.getInt("SDT");
                String gioiTinh = rs.getString("GIOITINH");
                String email = rs.getString("EMAIL");
                String ngay = rs.getString("NGAYSINH");
                String diaChi = rs.getString("DIACHI");
                KhachHangModel kh = new KhachHangModel( ten, sdt, gioiTinh, email, ngay, diaChi);

                list.add(kh);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public Boolean add(KhachHangModel kh) {
        String sql = "insert into KHACH_HANG (  TENKH, SDT,GIOITINH,EMAIL,NGAYSINH,DIACHI)\n"
                + "values ( ? ,?,?, ?,?,?)";

        try (Connection con = dBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setObject(1, kh.getTen());
            pst.setObject(2, kh.getSdt());
            pst.setObject(3, kh.getGioiTinh());
            pst.setObject(4, kh.getEmail());
            pst.setObject(5, kh.getNgay());
            pst.setObject(6, kh.getDiaChi());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean remove(int ma) {
        String sql = "delete KHACH_HANG where TENKH = ?";
        try (Connection con = dBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setObject(1, ma);

            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
        }
        return false;

    }

    public Boolean Update(KhachHangModel khachHang) {
        String sql = "	update KHACH_HANG\n"
                + "set TENKH = ?,\n"
                + "	SDT = ?,\n"
                + "	GIOITINH=?,\n"
                + "	EMAIl = ?,\n"
                + "	NGAYSINH = ?,\n"
                + "	DIACHI = ?\n"
                + "	where MAKH = ?";

        try (Connection con = dBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setObject(1, khachHang.getTen());
            pst.setObject(2, khachHang.getSdt());
            pst.setObject(3, khachHang.getGioiTinh());
            pst.setObject(4, khachHang.getEmail());
            pst.setObject(5, khachHang.getNgay());
            pst.setObject(6, khachHang.getDiaChi());
            pst.setObject(7, khachHang.getMaKH());

            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<KhachHangModel> timKiem(String ten1) {
        String sql = "select * from KHACH_HANG Where TENKH like '%" + ten1 + "%'";
        ArrayList<KhachHangModel> list = new ArrayList<>();
        try (Connection con = dBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)){
        ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer ma = rs.getInt("MAKH");
                String ten = rs.getString("TENKH");
                Integer sdt = rs.getInt("SDT");
                String gioiTinh = rs.getString("GIOITINH");
                String email = rs.getString("EMAIL");
                String ngay = rs.getString("NGAYSINH");
                String diaChi = rs.getString("DIACHI");
                KhachHangModel kh = new KhachHangModel(ma, ten, sdt, gioiTinh, email, ngay, diaChi);
                list.add(kh);
            }
        } catch(Exception e){
        e.printStackTrace();
        }
        return list;
        }
    }

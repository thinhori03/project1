
import DTO.ChuyenDuLieu;
import java.util.Date;
import model.Gia;
import service.GiaService;
import service.Mau_Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class main {

    public static void main(String[] args) {
        Mau_Service ms = new Mau_Service();
        
        System.out.println(new GiaService().ADDGia(new Gia(6,26,new Date(),new Date())));
    }
}

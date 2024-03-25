/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import repository.KhachHangConnection;
import java.util.ArrayList;
import model.KhachHangModel;

/**
 *
 * @author acer
 */
public class KhachHangservice {
    KhachHangConnection khc = new KhachHangConnection();
    
    public ArrayList<KhachHangModel> getlist(){
    return khc.getAll();
    }

    public String Add(KhachHangModel kh){
        if (khc.add(kh)== true){ 
            return "Them Thanh Cong";  
        }else{
        return"Them That Bai";
        }
    }

    
    public String rv(int ma){
    Boolean check = khc.remove(ma);
    if(check){
    return "Xoa Thanh Cong ";
    }else{
        return "Xoa That Bai";
    }
    }
    public String Update(KhachHangModel kh){
   
        if (khc.Update(kh)== true) {
            return "Sua Thanh Cong"; 
        }else {
        return "Sua That Bai";
        }
        
    }
    public ArrayList<KhachHangModel> timKiem(String ten){
    ArrayList<KhachHangModel> list = khc.timKiem(ten);
    return list;
    }
}



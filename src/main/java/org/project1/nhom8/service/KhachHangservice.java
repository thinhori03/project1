/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import org.project1.nhom8.repository.KhachHangConnection;
import java.util.ArrayList;
import org.project1.nhom8.model.KhachHangModel;

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
    public String Update(Integer makh,KhachHangModel kh){
   
        if (khc.Update(makh, kh)== true) {
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



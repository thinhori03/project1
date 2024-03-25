/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import model.ThongKeModel;
import repository.ThongKeConnection;

/**
 *
 * @author acer
 */
public class ThongKeService {
    ThongKeConnection tkc = new ThongKeConnection();
    
    public ArrayList<ThongKeModel> getlist(){
    return tkc.getAll();
    }
   public ArrayList<ThongKeModel> timKiem(String ngay){
   ArrayList<ThongKeModel> kq = tkc.timkiem(ngay);
   return kq;
   }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import org.project1.nhom8.model.ThongKeModel;
import org.project1.nhom8.repository.ThongKeConnection;

import java.util.ArrayList;

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

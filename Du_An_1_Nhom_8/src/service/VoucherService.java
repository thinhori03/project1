/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.VoucherModel;
import repository.VoucherRepository;

/**
 *
 * @author ngtnthori03
 */
public class VoucherService {
    
    private VoucherRepository voucherRepository;
    
    public VoucherService() {
        this.voucherRepository = new VoucherRepository();
        
    }
    
    public boolean addVoucher(VoucherModel model){
        return this.voucherRepository.add(model) != null;
    }
}

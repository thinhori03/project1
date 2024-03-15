
import model.VoucherModel;
import repository.VoucherRepository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) {
        VoucherRepository vr = new VoucherRepository();

        try {
            
            VoucherModel vm = vr.findById("v000000001");
            
            System.out.println(vm.getMaVoucher());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

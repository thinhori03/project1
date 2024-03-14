
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

            for (VoucherModel vm : vr.getAll()) {
                System.out.println(vm.getMaVoucher());

                // test insertion
                vm.setMaVoucher("v000000006");
                vr.add(vm);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

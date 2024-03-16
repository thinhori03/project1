
import model.VoucherModel;
import repository.VoucherRepository;

import java.util.Date;

/*
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) {
        VoucherRepository vr = new VoucherRepository();

        try {
            
            VoucherModel vm = new VoucherModel();

            vm.setMaVoucher("V000000003");
            vm.setGiaTri(0);
            vm.setDiauKien(100000);
            vm.setNgayBatDau(new Date());
            vm.setNgyKetThuc(new Date());

            vr.update(vm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


import model.VoucherModel;
import repository.VoucherRepository;

 *
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) {
        VoucherRepository vr = new VoucherRepository();

        try {
            
            VoucherModel vm = vr.findById("v000000001");
            
            
            vm.setSoLuong(123);
            
            vr.update(vm);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

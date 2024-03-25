
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import View.VoucherPanel;

/*
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) {
        
        JFrame f = new JFrame();
        
        f.add(new VoucherPanel());
        
        f.setSize(500, 500);
        
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        f.setLocationRelativeTo(null);
        
        f.setVisible(true);
        
    }

}
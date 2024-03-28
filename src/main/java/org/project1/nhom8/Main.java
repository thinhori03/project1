
package org.project1.nhom8;

import javax.swing.*;

import org.project1.nhom8.View.VoucherPanel;

/*
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    "com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme"
            );
        } catch (ClassNotFoundException
                 | InstantiationException
                 | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {
            System.out.println("cannot aply look and feel");
        }

        JFrame f = new JFrame();
        
        f.add(new VoucherPanel());
        
        f.setSize(500, 500);
        
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        f.setLocationRelativeTo(null);
        
        f.setVisible(true);
        
    }
}
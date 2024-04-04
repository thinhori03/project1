package org.project1.nhom8;

import java.awt.Button;
import javax.swing.*;
import org.project1.nhom8.View.LoadingPanel;
import org.project1.nhom8.service.MailService;


/*
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        var f = new JFrame();
        var l = new JLayeredPane();
        LoadingPanel loading = new LoadingPanel() {
            @Override
            public Boolean onLoading() {
                MailService mailService = new MailService();
                
                mailService.autoAuth();
                
                mailService.send("ngtnthori03@gmail.com", "hi", "????");
            
                return mailService.isResult();
            }
            
            @Override
            public void onSuccess() {
                
            }
            
            @Override
            public void onFailed() {
                
            }
        };
        
        loading.setSize(500, 500);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JButton b = new JButton("check me");
        b.setSize(200, 200);
        
        f.setBounds(0, 0, 500, 500);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        f.add(l);
        l.add(b, JLayeredPane.DEFAULT_LAYER);
        l.add(loading, JLayeredPane.POPUP_LAYER);
        // f.add(loading);
        loading.start();

//        try {
//
//            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
//
//        new org.project1.nhom8.View.main().setVisible(true);
    }
}

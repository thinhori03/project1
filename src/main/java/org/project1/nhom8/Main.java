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

        try {

            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        

        new org.project1.nhom8.View.LoginDialog().setVisible(true);
    }
}

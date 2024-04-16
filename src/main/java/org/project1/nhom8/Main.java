package org.project1.nhom8;

import javax.swing.*;

/*
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        try {

            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new org.project1.nhom8.View.LoginDialog().setVisible(true);
    }
}

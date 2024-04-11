package org.project1.nhom8;

import java.util.Scanner;
import javax.swing.*;
import org.project1.nhom8.util.MD5Util;

/*
 * @author ngtnthori03
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
       
        try {

            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new org.project1.nhom8.View.LoginDialog().setVisible(true);

    }
}

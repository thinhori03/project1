/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.util.swing;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author ngtnthori03
 */
public class DefaultPopupPanel {
    
    public static JPanel createPanel(JComponent component) {
        JPanel panel = new JPanel();
        
        panel.add(component);
        
        return panel;
    }
}

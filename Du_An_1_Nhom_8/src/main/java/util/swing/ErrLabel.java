/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.swing;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author ngtnthori03
 */
public class ErrLabel extends JLabel {
    
    public ErrLabel(String text) {
        super(text);
        
        this.setForeground(Color.red);
    }
    
}

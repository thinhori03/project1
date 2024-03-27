/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.swing;

import java.awt.*;
import java.util.Optional;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
/**
 *
 * @author ngtnthori03
 */
public class ValidatedTextField {
   
    private JTextField target;
    
    private String pattern;
    
    private String errorMessage;
    
    private Boolean isPopupReady;
    
    private Popup popup;
    
    private JPanel panel;
    
    private PopupFactory popupFactory;

    private JLabel label;
    
    public ValidatedTextField(JTextField target, String pattern
            , JLabel label,  String errorMessage) {
        this.pattern = pattern;
        this.errorMessage = errorMessage;
        
        this.target = target;
        this.label = label;

        this.label.setText(this.errorMessage);
        this.label.setVisible(false);
        this.label.setForeground(Color.RED);


        this.isPopupReady = false;
        
        this.popupFactory = new PopupFactory();
        
        this.panel = DefaultPopupPanel.createPanel(new JLabel("error"));
        // validation event
        target.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onValidate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onValidate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onValidate();
            }
        });
    }
    
    public void onValidate() {
        if (this.target.getText().trim().matches(pattern)) {
           this.label.setVisible(false);
        }
        else {
            this.label.setVisible(true);
        }
    }
    
    public boolean isValidate() {
        // popup ready when validation falied
        return !isPopupReady;
    }
    
    public boolean isPopupReady() {
        return this.isPopupReady;
    }
    
    public void PreparePopupAndShow() {
        Point p = new Point();
        SwingUtilities.convertPointToScreen(p, this.target);
        
        popup = popupFactory.getPopup(target, panel, p.x
                , p.y + panel.getPreferredSize().height);
        
        popup.show();
    }
}

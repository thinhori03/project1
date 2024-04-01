/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.util.swing;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ngtnthori03
 */
public abstract class GeneralDocumentListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        onChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        onChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        onChange();
    }
    
    public abstract void onChange();
}

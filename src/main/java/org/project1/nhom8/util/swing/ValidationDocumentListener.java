/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.util.swing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ngtnthori03
 */
public abstract class ValidationDocumentListener implements DocumentListener {

    private JTextField textField;

    public ValidationDocumentListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.onChange(textField, e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.onChange(textField, e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        this.onChange(textField, e);
    }

    public abstract void onChange(JTextField textField, DocumentEvent e);
    
}

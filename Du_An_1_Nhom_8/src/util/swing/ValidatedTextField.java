package util.swing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * @author ngtnthori03
 */
public class ValidatedTextField extends JTextField {


    public String pattern;
    public String err_msg;
    public JLabel err_label;

    public ValidatedTextField() {
        this.pattern = new String("^*$");
        this.err_msg = new String("^*$");
        this.err_label = new JLabel("");
        init();
    }

    // @param _pattern | regex đểcheck vaildate
    // @Param _err_msg | mesage khi faildate fail
    // @param _err_label | label để hiện _err_msg
    public ValidatedTextField(String _pattern, String _err_msg, JLabel _err_label) {
        this.pattern = _pattern;
        this.err_msg = _err_msg;
        this.err_label = _err_label;
        this.err_label.setVisible(true);
        init();
    }

    public void init() {

        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                vaildate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                vaildate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                vaildate();
            }
        });
        this.err_label.setForeground(Color.RED);
    }

    public void setPattern(String _pattern) {
        this.pattern = _pattern;
    }

    public void setErrorMsg(String _err_msg) {
        this.err_msg = _err_msg;
    }

    public JLabel getLabel() {
        return this.err_label;
    }

    public void vaildate() {
        if (this.getText().matches(this.pattern)) {
            this.err_label.setText("");
        } else if (getText().isEmpty()) {
            this.err_label.setText("thông tin không được để trống");
        } else {
            this.err_label.setText(this.err_msg);
        }
        System.out.println(err_label.getText());
        System.out.println(getText());
    }

    public boolean isResult() {
        return this.err_label.getText().isEmpty() && !getText().isEmpty();
    }

    @Override
    public void setText(String t) {
        super.setText(t); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.err_label.setText("");
    }
}

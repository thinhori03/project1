package util.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class PopupNotification {


    private final JComponent target;

    private final JComponent component;

    private JPanel panel;

    private Popup popup;

    public PopupNotification(JComponent target, JComponent component) {

        this.target = target;

        this.component = component;

        this.panel = new JPanel();

        this.panel.add(this.component);

        this.target.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                Point p = new Point();

                SwingUtilities.convertPointToScreen(p, target);

                popup = PopupFactory.getSharedInstance()
                        .getPopup(target, panel, p.x, p.y - panel.getHeight());

                popup.show();

                System.out.println("show ... ");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Optional.ofNullable(popup).ifPresent(Popup::hide);
                System.out.println("hide ...");
            }
        });
    }

    public JComponent getNotifyComponent() {
        return this.component;
    }
}

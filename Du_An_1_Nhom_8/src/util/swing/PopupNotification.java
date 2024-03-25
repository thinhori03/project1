package util.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class PopupNotification {


    private final JComponent target;

    private final JComponent component;
    
    private final JComponent src;

    private JPanel panel;

    private Popup popup;
    
    private Point targetPoint;

    public PopupNotification(JComponent target, JComponent src, JComponent component) {

        this.target = target;
        this.component = component;
        this.src = src;
        
        this.targetPoint = new Point();

        this.panel = new JPanel();
        this.panel.add(this.component);

        this.src.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                targetPoint = new Point();
                SwingUtilities.convertPointToScreen(targetPoint, target);

                popup = PopupFactory.getSharedInstance()
                        .getPopup(src, panel, targetPoint.x, targetPoint.y - panel.getHeight() - 10);

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

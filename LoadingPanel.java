package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class LoadingPanel extends JPanel {
    public JLabel loadingLabel;
    public JLabel logoLabel;

    public LoadingPanel() {
        setOpaque(false);
        loadingLabel = new JLabel();
        logoLabel = new JLabel();
        loadingLabel.setIcon(new ImageIcon("img/loading.gif"));
        logoLabel.setIcon(new ImageIcon("img/PeeKaBoo_300.png"));

        setLayout(new BorderLayout(4, 4));// 可傳參數(間隙)
        add(loadingLabel, BorderLayout.CENTER);
        add(logoLabel, BorderLayout.SOUTH);
    }
}
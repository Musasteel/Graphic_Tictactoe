import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class ObstaclesMultiplayer extends JPanel {
    private CardLayout cardLayout;
    public ObstaclesMultiplayer(final JPanel cardPanel) {
        cardLayout = (CardLayout) cardPanel.getLayout();
        setLayout(null);

    }
}

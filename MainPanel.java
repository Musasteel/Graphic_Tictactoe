import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    public MainPanel(final JPanel cardPanel) {
        cardLayout = (CardLayout) cardPanel.getLayout();
        setLayout(null);
        setBackground(Color.BLACK);
        JButton startButton = ButtonFactory.createButton("images/START.png", "images/STARTHOVER.png", true, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.playSound("audio/defaultclick.wav");
                cardLayout.show(cardPanel, "start");
            }
        });
        startButton.setBounds(350, 260, 220, 70);
        add(startButton);

        JButton settingsButton = ButtonFactory.createButton("images/SETTINGS.png", "images/SETTINGSHOVER.png", true, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.playSound("audio/defaultclick.wav");
                cardLayout.show(cardPanel, "settings");
            }
        });
        settingsButton.setBounds(273, 360, 370, 70);
        add(settingsButton);
    }
}

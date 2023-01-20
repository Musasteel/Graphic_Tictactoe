import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class StartPanel extends JPanel {
    private CardLayout cardLayout;
    public StartPanel(final JPanel cardPanel) {
        setBackground(Color.BLACK);
        cardLayout = (CardLayout) cardPanel.getLayout();
        setLayout(null);
        // Create the back button
        JButton backButton = ButtonFactory.createButton("images/BACK.png", "images/BACKHOVER.PNG", true, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.playSound("audio/defaultclick.wav");
                cardLayout.show(cardPanel, "main");
            }
        });
        backButton.setBounds(20, 20, 100, 50);
        add(backButton);

        // OBSTACLE OPTIONS PANEL
        // Create a scores button
        JButton scoresButton = ButtonFactory.createButton("images/scores.png", "images/scoreshover.png", true, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.playSound("audio/defaultclick.wav");
                cardLayout.show(cardPanel, "scores");
            }
        });

        scoresButton.setBounds(550, 380, 370, 70);
        add(scoresButton);

        // Create a AI Option button
        JButton AIButton = ButtonFactory.createButton("images/AI.png", "images/AIhover.png", true, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.playSound("audio/defaultclick.wav");
                cardLayout.show(cardPanel, "minimax");
            }
        });

        AIButton.setBounds(600, 200, 70, 50);
        add(AIButton);

        // Create a Multiplayer Option  button
        JButton MPButton = ButtonFactory.createButton("images/MP.png", "images/MPhover.png", true, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.playSound("audio/defaultclick.wav");
                cardLayout.show(cardPanel, "multiplayer");
            }
        });
        MPButton.setBounds(500, 200, 100, 50);
        add(MPButton);
      }
}


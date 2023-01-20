
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class GameWindow extends JFrame {
  MusicPlayer audioPlayer = new MusicPlayer();
  public GameWindow() {
      //MAIN PANEL
      audioPlayer.playAudio();
      // Create a JPanel
      JFrame frame = new JFrame("GAME");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Creating The Panels
      JPanel cardPanel = new JPanel();
      cardPanel.setLayout(new CardLayout());

      MainPanel mainPanel = new MainPanel(cardPanel);
      cardPanel.add(mainPanel, "main");

      JPanel startPanel = new StartPanel(cardPanel);
      cardPanel.add(startPanel, "start");

      JPanel panel2 = new JPanel();
      panel2.add(new JLabel("settings"));
      cardPanel.add(panel2, "settings");
      panel2.setBackground(Color.BLACK);
      panel2.setLayout(null);

      JPanel MPPanel = new JPanel();
      cardPanel.add (MPPanel, "multiplayer");
      TicTacToe MPTicTacToe = new TicTacToe();
      MPPanel.add(MPTicTacToe);

      // Add the card panel to the frame
      frame.add(cardPanel);
      frame.pack();
      frame.setVisible(true);

      // Set the size and title of the window
      frame.setSize(1000, 700);
      CardLayout cardLayout = (CardLayout)(cardPanel.getLayout());
      cardLayout.first(cardPanel);
    
      //SETTINGS PANEL
        
        JButton pauseButton = ButtonFactory.createButton("images/pause.png", "images/pause.png", false, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              SoundPlayer soundPlayer = new SoundPlayer();
              soundPlayer.playSound("audio/defaultclick.wav");
              audioPlayer.pauseAudio();

          }
      });
      
      pauseButton.setBounds(410, 200, 220, 70);
      panel2.add(pauseButton);

        // Create a play button
        JButton playButton = ButtonFactory.createButton("images/play.png", "images/play.png", false, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              SoundPlayer soundPlayer = new SoundPlayer();
              soundPlayer.playSound("audio/defaultclick.wav");
              audioPlayer.playAudio();
          }
        });
      playButton.setBounds(410, 115, 220, 70);
      panel2.add(playButton);


        // Create the trackone button
        JButton trackone = ButtonFactory.createButton("images/trackonehover.gif", "images/trackonehover.gif", false, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              SoundPlayer soundPlayer = new SoundPlayer();
              soundPlayer.playSound("audio/defaultclick.wav");
              audioPlayer.setFileName("audio/defaulttrack.wav");
              audioPlayer.playAudio();

          }
      });

      trackone.setBounds(20, 90, 200, 200);
      panel2.add(trackone);



        // Create the tracktwo button
        JButton tracktwo = ButtonFactory.createButton("images/tracktwohover.gif", "images/tracktwohover.gif", false, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              SoundPlayer soundPlayer = new SoundPlayer();
              soundPlayer.playSound("audio/defaultclick.wav");
              audioPlayer.setFileName("audio/secondarytrack.wav");
              audioPlayer.playAudio();
          }
      });

      tracktwo.setBounds(260, 90, 200, 200);
      panel2.add(tracktwo);


        // Create the back button
        JButton backsettings = ButtonFactory.createButton("images/BACK.png", "images/BACKHOVER.PNG", true, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              SoundPlayer soundPlayer = new SoundPlayer();
              soundPlayer.playSound("audio/defaultclick.wav");
              cardLayout.show(cardPanel, "main");
          }
      });

      backsettings.setBounds(-60, -80, 200, 200);
      panel2.add(backsettings);

      MPPanel.setBackground(Color.BLACK);

      // Create the back button
      JButton backstart = ButtonFactory.createButton("images/BACK.png", "images/BACKHOVER.PNG", true, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              SoundPlayer soundPlayer = new SoundPlayer();
              soundPlayer.playSound("audio/defaultclick.wav");

              cardLayout.show(cardPanel, "start");
          }
      });
  
      backstart.setBounds(-60, -80, 200, 200);
      MPPanel.add(backstart);

    }

}
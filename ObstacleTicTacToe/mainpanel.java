package ObstacleTicTacToe;

import javax.swing.*;

public class mainpanel {
    public static void main(String[] args) {
      JFrame frame = new JFrame("Tic Tac Toe");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600, 600);
      
      JPanel gamePanel = new JPanel();
      addTicTacToeToPanel(gamePanel);
      
      frame.add(gamePanel);
      frame.setVisible(true);
      
    }
    
    public static void addTicTacToeToPanel(JPanel panel) {
      TicTacToe tficTacToe = new TicTacToe();
      panel.add(tficTacToe);
      
    }
    
  }
  
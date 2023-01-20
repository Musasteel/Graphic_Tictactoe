package ObstacleTicTacToe;
import javax.swing.*;

public class TicTacToeFrame {
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
      minimax ticTacToe = new minimax();
      panel.add(ticTacToe);
      
    }
    
  }
  
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.*;
import java.util.Random;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class TicTacToe extends JPanel implements ActionListener {
  private static final int ROWS = 4;
  private static final int COLS = 4;

  private JButton[][] buttons;
  private char[][] board;
  private char currentPlayer;
  private JButton resetButton;
  private ImageIcon xIcon;
  private ImageIcon oIcon;
  private ImageIcon obstacleIcon;
  private boolean[][] obstacles;

  public TicTacToe() {
    setLayout(new BorderLayout());

    JPanel boardPanel = new JPanel();
    boardPanel.setPreferredSize(new Dimension(500, 500));
    boardPanel.setLayout(new GridLayout(ROWS, COLS));
    buttons = new JButton[ROWS][COLS];
    board = new char[ROWS][COLS];
    obstacles = new boolean[ROWS][COLS];

    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].addActionListener(this);
        boardPanel.add(buttons[i][j]);
        board[i][j] = ' ';
        obstacles[i][j] = false;
      }
    }

    add(boardPanel);

    resetButton = new JButton("Reset");
    resetButton.addActionListener(this);
    add(resetButton, BorderLayout.SOUTH);

    xIcon = new ImageIcon("images/X.png");
    oIcon = new ImageIcon("images/O.png");
    obstacleIcon = new ImageIcon("images/obstacle.png");

    currentPlayer = 'X';
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == resetButton) {
        start();
        return;
    }
  
    JButton button = (JButton) e.getSource();
  
    int row = -1;
    int col = -1;
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        if (button == buttons[i][j]) {
          row = i;
          col = j;
          break;
        }
      }
    }
  
    if (board[row][col] != ' ') {
      JOptionPane.showMessageDialog(this, "That spot is already taken. Try again.");
      return;
    }
  
    if (obstacles[row][col]) {
      JOptionPane.showMessageDialog(this, "That spot has an obstacle. Try again.");
      return;
    }
  
    board[row][col] = currentPlayer;
    if (currentPlayer == 'X') {
      button.setIcon(xIcon);
    } else {
      button.setIcon(oIcon);
    }
  
    if (checkWin(currentPlayer)) {
      JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " has won!");
      writeScore(currentPlayer, false);
      start();
      return;
    }
  
    // Check if the board is full
    boolean full = true;
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        if (board[i][j] == ' ' && !obstacles[i][j]) {
          full = false;
          break;
        }
      }
    }
  
    if (full) {
      JOptionPane.showMessageDialog(this, "Tie!");
      writeScore(' ', true);
      start();
      return;
    }

      if (currentPlayer == 'X') {
          currentPlayer = 'O';
      } else {
        currentPlayer = 'X';
      }
    }
    
    private boolean checkWin(char player) {
      for (int i = 0; i < ROWS; i++) {
        if (board[i][0] == player && board[i][1] == player && board[i][2] == player && board[i][3] == player) {
          return true;
        }
      }
    
      for (int i = 0; i < COLS; i++) {
        if (board[0][i] == player && board[1][i] == player && board[2][i] == player && board[3][i] == player) {
          return true;
        }
      }
    
      if (board[0][0] == player && board[1][1] == player && board[2][2] == player && board[3][3] == player) {
        return true;
      }
      if (board[0][3] == player && board[1][2] == player && board[2][1] == player && board[3][0] == player) {
        return true;
      }
    
      return false;
    }

    private void writeScore(char player, boolean isTie) {
      try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("textfiles/scores.txt", true));
        if (isTie) {
          writer.write("Tie");
        } else {
          writer.write(player);
        }
        writer.newLine();
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    
    private void start() {
        currentPlayer = 'X';
        for (int i = 0; i < ROWS; i++) {
          for (int j = 0; j < COLS; j++) {
            board[i][j] = ' ';
            obstacles[i][j] = false;
            buttons[i][j].setIcon(null);
          }
        }
      
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
          int row = random.nextInt(ROWS);
          int col = random.nextInt(COLS);
          obstacles[row][col] = true;
          buttons[row][col].setIcon(obstacleIcon);
        }
      }

}
  
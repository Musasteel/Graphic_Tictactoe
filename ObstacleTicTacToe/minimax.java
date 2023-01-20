package ObstacleTicTacToe;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.Random;

public class minimax extends JPanel implements ActionListener {
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

  public minimax() {
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
  
    if (checkwin.checkWin(board, ROWS, COLS, currentPlayer)) {
      JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " has won!");
      score.writeScore(currentPlayer, false);
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
      score.writeScore(' ', true);
      start();
      return;
    }

      if (currentPlayer == 'X') {
          currentPlayer = 'O';
      } else {
        currentPlayer = 'X';
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
  
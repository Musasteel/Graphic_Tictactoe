package ObstacleTicTacToe;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.Random;

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
    setLayout(new BorderLayout()); // Use a border layout

    JPanel boardPanel = new JPanel();
    boardPanel.setPreferredSize(new Dimension(500, 500)); // 500x500 pixels
    boardPanel.setLayout(new GridLayout(ROWS, COLS)); // 4x4 grid
    buttons = new JButton[ROWS][COLS]; // 2D array of buttons
    board = new char[ROWS][COLS]; // 2D array of characters
    obstacles = new boolean[ROWS][COLS]; // 2D array of booleans

    for (int i = 0; i < ROWS; i++) { // Loop through the rows
      for (int j = 0; j < COLS; j++) { // Loop through the columns
          buttons[i][j] = new JButton(); // Create a new button
          buttons[i][j].addActionListener(this); // Add an action listener
          boardPanel.add(buttons[i][j]); // Add the button to the panel
          board[i][j] = ' '; // Set the character to a space
          obstacles[i][j] = false; // Set the obstacle to false
      }
  }
  add(boardPanel); // Add the board panel to the main panel

    resetButton = new JButton("Reset"); // Create a new button
    resetButton.addActionListener(this); // Add an action listener
    add(resetButton, BorderLayout.SOUTH); // Add the button to the bottom of the main panel

    xIcon = new ImageIcon("images/X.png"); // Create an image icon for X
    oIcon = new ImageIcon("images/O.png"); // Create an image icon for O
    obstacleIcon = new ImageIcon("images/obstacle.png"); // Create an image icon for obstacles

    this.currentPlayer = 'X';

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
    
    board[row][col] = this.currentPlayer;
    if (this.currentPlayer == 'X') {
      button.setIcon(xIcon);
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
    
    // If it's the computer's turn, make the computer's move
    if (this.currentPlayer == 'O') {
      makeComputerMove();
    } else {
      this.currentPlayer = 'O';
      makeComputerMove();
    }

  }

  private void makeComputerMove() {
    int[] move = findBestMove();
    int row = move[0];
    int col = move[1];
    board[row][col] = this.currentPlayer;
    buttons[row][col].setIcon(oIcon);
    if (checkwin.checkWin(board, ROWS, COLS, 'O')) {
        JOptionPane.showMessageDialog(this, "Player O has won!");
        score.writeScore('O', false);
        start();
        return;
    }
    if (checkwin.checkWin(board, ROWS, COLS, this.currentPlayer)) {
        JOptionPane.showMessageDialog(this, "Computer has won!");
        score.writeScore(this.currentPlayer, true);
        start();
        return;
    }

    boolean isTie = true;
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (board[i][j] == ' ' && !obstacles[i][j]) {
                isTie = false;
                break;
            }
        }
    }

    if (isTie) {
        JOptionPane.showMessageDialog(this, "It's a tie!");
        score.writeScore('T', true);
        start();
        return;
    }
    
    this.currentPlayer = this.currentPlayer == 'X' ? 'O' : 'X';
    currentPlayer = 'X';
  }

  private int[] findBestMove() {
    int bestScore = Integer.MIN_VALUE;
    int[] bestMove = {-1, -1};

    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (board[i][j] == ' ' && !obstacles[i][j]) { // If the spot is empty minds the obstacle as well as the Xs and Os
                board[i][j] = 'O';
                int score = minimax(board, 0, false);
                board[i][j] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    bestMove[0] = i;
                    bestMove[1] = j;
                }
            }
        }
    }
    return bestMove;
  }


private int minimax(char[][] board, int depth, boolean isMaximizing) {
    char winner = 'O'; // The computer is always O
    if (winner == 'X') { // If the winner is X, then the computer lost
        return -10 + depth; // The depth is added to the score to make the computer prefer moves that are closer to winning
    } else if (winner == 'O') { // If the winner is O, then the computer won
        return 10 - depth; //  The depth is subtracted from the score to make the computer prefer moves that are closer to winning
    } else if (isBoardFull()) { // If the board is full, then it's a tie
        return 0; 
    }

    if (isMaximizing) {
        int bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int score = minimax(board, depth + 10, false);
                    board[i][j] = ' ';
                    bestScore = Math.max(score, bestScore);
                }
            }
        }
        return bestScore;
    } else {
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'X';
                    int score = minimax(board, depth + 10, true);
                    board[i][j] = ' ';
                    bestScore = Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }
}


    private boolean isBoardFull() {
      for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
          if (board[row][col] == ' ' && !obstacles[row][col]) {
            return false;
          }
        }
            }
      return true;
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
 
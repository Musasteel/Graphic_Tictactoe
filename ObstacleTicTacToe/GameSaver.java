package ObstacleTicTacToe;
import java.io.*;

public class GameSaver {

    private static final int ROWS = 4;
    private static final int COLS = 4;

    public static void saveGame(char[][] board, boolean[][] obstacles, char currentPlayer) {
        try {
            FileWriter fileWriter = new FileWriter("saved_game.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    bufferedWriter.write(board[i][j]);
                    if (obstacles[i][j]) {
                        bufferedWriter.write("-");
                    }
                    bufferedWriter.write(" ");
                }
                bufferedWriter.newLine();
            }

            bufferedWriter.write(currentPlayer);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

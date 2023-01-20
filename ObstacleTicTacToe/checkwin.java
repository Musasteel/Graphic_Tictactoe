package ObstacleTicTacToe;
public class checkwin {
    public static boolean checkWin(char[][] board, int rows, int cols, char player) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((board[i][0] == player && board[i][1] == player && board[i][2] == player && board[i][3] == player) ||
                        (board[0][j] == player && board[1][j] == player && board[2][j] == player && board[3][j] == player) ||
                        (board[0][0] == player && board[1][1] == player && board[2][2] == player && board[3][3] == player) ||
                        (board[0][3] == player && board[1][2] == player && board[2][1] == player && board[3][0] == player)) {
                    return true;
                }
            }
        }
        return false;
    }
}

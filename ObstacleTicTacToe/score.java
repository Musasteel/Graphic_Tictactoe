package ObstacleTicTacToe;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class score {
    public static void writeScore(char player, boolean isTie) {
        try {
            FileWriter fileWriter = new FileWriter("textfiles/scores.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (!isTie) {
                bufferedWriter.write(player);
                bufferedWriter.newLine();
            } else {
                bufferedWriter.write("tie");
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

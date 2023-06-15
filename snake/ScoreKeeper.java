import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class ScoreKeeper {
    
    private String name = "";
    private int score = 0;

    public ScoreKeeper() {
        
    }

    public ScoreKeeper(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void saveScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true))) {
            writer.write(name + ": " + score);
            writer.newLine();
            // System.out.println("Content appended to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

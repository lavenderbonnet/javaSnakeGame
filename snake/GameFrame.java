import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private GamePanel gp;
    
    GameFrame() {
        gp = new GamePanel();
        this.add(gp);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public void startGame(){
        gp.startGame();
    }

}

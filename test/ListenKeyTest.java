import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class ListenKeyTest extends JFrame implements KeyListener {

    public ListenKeyTest() {
        super("Space Bar Detection Example");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ListenKeyTest();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        switch (keyCode) {
            case KeyEvent.VK_W:
                System.out.println("Go Up");
                break;
            case KeyEvent.VK_UP:
                System.out.println("Go Up");
                break;
            case KeyEvent.VK_A:
                System.out.println("Go Left");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Go Left");
                break;
            case KeyEvent.VK_S:
                System.out.println("Go Down");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Go Down");
                break;
            case KeyEvent.VK_D:
                System.out.println("Go Right");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Go Right");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Begin Game");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}
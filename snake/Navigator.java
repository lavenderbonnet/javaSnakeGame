import java.awt.event.*;

public class Navigator extends KeyAdapter {
    private GamePanel gp;

    public Navigator(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int direction = gp.getDirection();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (direction != Direction.EAST) {
                    direction = Direction.WEST;
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (direction != Direction.WEST) {
                    direction = Direction.EAST;
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (direction != Direction.SOUTH) {
                    direction = Direction.NORTH;
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (direction != Direction.NORTH) {
                    direction = Direction.SOUTH;
                }
                break;
        }
        gp.setDirection(direction);
    }
}
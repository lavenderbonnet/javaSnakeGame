import java.awt.Color;
import java.awt.Graphics;
// import java.awt.GraphicsDevice;
import java.awt.Dimension;
// import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveBlock extends JPanel implements KeyListener, Runnable {
    private static final int BLOCK_SIZE = 50;
    private static final int MOVE_AMOUNT = 20;
    // private static final int GRID_SIZE = 20;

    private int blockX = 0;
    private int blockY = 0;

    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveRight = false;
    private boolean moveDown = false;

    private int windowWidth;
    private int windowHeight;

    public MoveBlock() {
        super();
        // setPreferredSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
        setPreferredSize(new Dimension(600, 700));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        windowWidth = getPreferredSize().width;
        windowHeight = getPreferredSize().height;

        // block starting point
        blockX = (windowWidth - BLOCK_SIZE) / 2;
        blockY = (windowHeight - BLOCK_SIZE) / 2;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the grid background
        g.setColor(Color.LIGHT_GRAY);
        int gridSize = 25; // Adjust the size of each grid cell
        for (int x = 0; x < windowWidth; x += gridSize) {
            g.drawLine(x, 0, x, windowHeight);
        }
        for (int y = 0; y < windowHeight; y += gridSize) {
            g.drawLine(0, y, windowWidth, y);
        }
        
        // Draw the block
        g.setColor(Color.RED);
        g.fillRect(blockX, blockY, BLOCK_SIZE, BLOCK_SIZE);
    }

    public void movingBlock() {
        if (moveLeft && blockX > 0) {
            blockX -= MOVE_AMOUNT;
        }
        if (moveUp && blockY > 0) {
            blockY -= MOVE_AMOUNT;
        }
        if (moveRight && blockX < windowWidth - BLOCK_SIZE) {
            blockX += MOVE_AMOUNT;
        }
        if (moveDown && blockY < windowHeight - BLOCK_SIZE) {
            blockY += MOVE_AMOUNT;
        }
        repaint();

        // if (moveLeft) {
        // // if (moveLeft && blockX > 0) {
        //     int newX = blockX - MOVE_AMOUNT;
        //     if (newX >= 0 && newX % GRID_SIZE == 0) {
        //         blockX = newX;
        //     }
        // }
        // if (moveUp) {
        // // if (moveUp && blockY > 0) {
        //     int newY = blockY - MOVE_AMOUNT;
        //     if (newY >= 0 && newY % GRID_SIZE == 0) {
        //         blockY = newY;
        //     }
        // }
        // if (moveRight) {
        // // if (moveRight && blockX < windowWidth - BLOCK_SIZE) {
        //     int newX = blockX + MOVE_AMOUNT;
        //     if (newX + BLOCK_SIZE <= windowWidth && newX % GRID_SIZE == 0) {
        //         blockX = newX;
        //     }
        // }
        // if (moveDown) {
        // // if (moveDown && blockY < windowHeight - BLOCK_SIZE) {
        //     int newY = blockY + MOVE_AMOUNT;
        //     if (newY + BLOCK_SIZE <= windowHeight && newY % GRID_SIZE == 0) {
        //         blockY = newY;
        //     }
        // }
        // repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                moveLeft = true;
                moveRight = false;
                moveUp = false;
                moveDown = false;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                moveUp = true;
                moveDown = false;
                moveLeft = false;
                moveRight = false;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                moveRight = true;
                moveLeft = false;
                moveUp = false;
                moveDown = false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                moveDown = true;
                moveUp = false;
                moveLeft = false;
                moveRight = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nothing :/
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nothing :/
    }

    @Override
    public void run() {
        while (true) {
            movingBlock();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

     public static void main(String[] args) {
        JFrame frame = new JFrame("Block Movement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MoveBlock block = new MoveBlock();
        frame.getContentPane().add(block);
        
        // GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // if (graphicsDevice.isFullScreenSupported()) {
        //     frame.setUndecorated(true);
        //     graphicsDevice.setFullScreenWindow(frame);
        // } else {
        //     frame.setPreferredSize(new Dimension(800, 600)); // Fallback dimension
        //     frame.pack();
        //     frame.setLocationRelativeTo(null);
        // }

        // frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Thread blockThread = new Thread(block);
        blockThread.start();
    }
}
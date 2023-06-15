import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    
    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 600;
    static final int GRID_SIZE = 50;
    static final int GRID_NUMBER = (SCREEN_HEIGHT*SCREEN_WIDTH)/(GRID_SIZE*GRID_SIZE);
    static final int GAME_SPEED = 100;
    static final int SNAKE_START_LENGTH = 5;

    static final Color CLR_SNAKE_HEAD = Color.RED;
    static final Color CLR_SNAKE = Color.PINK;
    static final Color CLR_BACKGROUND = new Color(150, 187, 180);
    static final Color CLR_SCORE = Color.GREEN;
    static final Color CLR_GAMEOVER = Color.RED;
    static final String GAME_FONT = "Monospaced Plain";

    final int x[] = new int[GRID_NUMBER];
    final int y[] = new int[GRID_NUMBER];

    int score;
    ScoreKeeper s = new ScoreKeeper();
    String name = "lilac";

    Position posFrog = new Position();

    Position posSnake[] = new Position[GRID_NUMBER];     
    Position posSnakeHead = null;  

    // initial snake length
    int snakeLength = 0;

    int direction = Direction.EAST;
    boolean gameOver = false;

    Timer timer;
    Random random;

    public GamePanel() {
        random = new Random();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(CLR_BACKGROUND);
        this.setFocusable(true);
        this.addKeyListener(new Navigator(this));

        initializeSnake();
    }

    private void initializeSnake()
    {
        snakeLength = SNAKE_START_LENGTH;
        for(int i = 0; i < snakeLength; i++)
        {
            posSnake[i] = new Position((snakeLength - i)*GRID_SIZE, 0);
        }
        posSnakeHead = posSnake[0];
    } 

    public void startGame() {
        newFrog();
        
        // start the timer
        timer = new Timer(GAME_SPEED, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (!gameOver){
            drawGrid(g);
            drawFrog(g);
            drawSnake(g);
            drawScore(g, GRID_SIZE);            
        } else {
            drawScore(g, 2*GRID_SIZE);
            drawGameOver(g);
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            g.drawLine(i*GRID_SIZE, 0, i*GRID_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*GRID_SIZE, SCREEN_WIDTH, i*GRID_SIZE);
        }
    }

    private void drawScore(Graphics g, int scoreSize) {
        drawMessage(g, CLR_SCORE, "Score: " + score, scoreSize, scoreSize);
    }

    private void drawGameOver(Graphics g) {
        drawMessage(g, CLR_GAMEOVER,"GAME OVER", 3*GRID_SIZE, SCREEN_HEIGHT/2);
    }

    private void drawMessage(Graphics g, Color c, String message, int msgSize, int y) {
        g.setColor(c);
        g.setFont(new Font(GAME_FONT, Font.BOLD, msgSize));

        // center the message
        FontMetrics metrics = getFontMetrics(g.getFont());
        int msgWidth = metrics.stringWidth(message);
        int center_x = (SCREEN_WIDTH - msgWidth)/2;

        g.drawString(message, center_x, y);
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeLength; i++) {
            if (i == 0) {
                g.setColor(CLR_SNAKE_HEAD);
            } else {
                g.setColor(CLR_SNAKE);
            }
            //g.fillRect(x[i], y[i], GRID_SIZE, GRID_SIZE);
            g.fillRect(posSnake[i].X, posSnake[i].Y, GRID_SIZE, GRID_SIZE);
        }
    }

    private void drawFrog(Graphics g) {
        Image frogIcon = getFrog();
        g.drawImage(frogIcon, posFrog.X, posFrog.Y, this);

        // g.setColor(Color.green);
        // g.fillOval(frogPosition.X, frogPosition.Y, GRID_SIZE, GRID_SIZE);
    }

    private Image getFrog() {
        // Load the image from file
        ImageIcon originalIcon = new ImageIcon("SnakeGameFrog.png");
        Image originalImage = originalIcon.getImage();

        // Scale down the image
        int scaledWidth = GRID_SIZE;
        int scaledHeight = GRID_SIZE;
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the scaled image
        ImageIcon frogIcon = new ImageIcon(scaledImage);

        return frogIcon.getImage();
    }

    private void newFrog() {
        posFrog.X = random.nextInt((int) (SCREEN_WIDTH/GRID_SIZE)) * GRID_SIZE;
        posFrog.Y = random.nextInt((int) (SCREEN_HEIGHT/GRID_SIZE)) * GRID_SIZE;
    }

    private void eatFrog()
    {
        // eat frog and grow
        Position oldTail = posSnake[snakeLength-1];
        snakeLength++;
        // create new tail
        posSnake[snakeLength-1] = new Position(oldTail.X, oldTail.Y);
        score++;
    }

    private void checkForFrog() {
        //if(x[0] == posFrog.X && y[0] == posFrog.Y) {
        if(posSnakeHead.Equals(posFrog)){
            eatFrog();
            // new frog shows up
            newFrog();
        }
    }

    private void moveSnake() {
        
        if(gameOver) return;

        // slither snake body forward
        for (int i = snakeLength-1; i > 0; i--) {
            if(posSnake[i] == null)
                posSnake[i] = new Position(0, 0);
            posSnake[i].Copy(posSnake[i-1]);
        }

        // move snake head
        switch(direction) {
            case Direction.NORTH:
                posSnakeHead.Y -= GRID_SIZE;
                break;
            case Direction.EAST:
                posSnakeHead.X += GRID_SIZE;
                break;
            case Direction.WEST:
                posSnakeHead.X -= GRID_SIZE;
                break;
            case Direction.SOUTH:
                posSnakeHead.Y += GRID_SIZE;
                break;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int d) {
        this.direction = d;
    }

    private void checkGameOver() {
        // hits wall
        boolean hitsWall = false;
        if (posSnakeHead.X < 0 || posSnakeHead.X > SCREEN_WIDTH 
            || posSnakeHead.Y < 0 || posSnakeHead.Y > SCREEN_HEIGHT) {
            hitsWall = true;
        }

        // hits itself
        boolean hitsSelf = false;
        for (int i = snakeLength-1; i > 0; i--) {
            if(posSnakeHead.Equals(posSnake[i])) {
                hitsSelf = true;
                break;
            }
        }

        // stop game if hits wall or self
        gameOver = hitsWall || hitsSelf;
        if(gameOver) {
            timer.stop();

            // saves score into scores.txt
            s.setName(name);
            s.setScore(score);
            s.saveScore();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver) {
            checkGameOver();
            checkForFrog();
            moveSnake();
        }
        repaint();
    }
}

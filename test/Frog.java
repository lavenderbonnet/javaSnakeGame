import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

public class Frog {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();

        // Load the image from file
        ImageIcon originalIcon = new ImageIcon("SnakeGameFrog.png");
        Image originalImage = originalIcon.getImage();

        // Scale down the image
        int scaledWidth = 25; // Specify the desired width
        int scaledHeight = 25; // Specify the desired height
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        // Add the JLabel to the panel
        panel.add(imageLabel);

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }
}
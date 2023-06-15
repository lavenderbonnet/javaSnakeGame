import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome Player! Please enter your name: ");
        String name = scanner.nextLine();

        Player player = new Player(name);
        System.out.println(player.getName() + ", are you ready? Hit space to begin playing.");
        scanner.close();
    }
}
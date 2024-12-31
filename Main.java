// Main.java
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = displayMenu(scanner);

            switch (choice) {
                case 1 -> continueGame();
                case 2 -> newGame(scanner);
                case 3 -> {
                    System.out.println("Exiting the game. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int displayMenu(Scanner scanner) {
        System.out.println("\nMain Menu");
        System.out.println("1. Continue");
        System.out.println("2. New Game");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public static void continueGame() {
        Character character = Character.loadCharacter();
        try {
            spinnerAnimation();
        } catch (InterruptedException e) {
            System.err.println("Animation interrupted: " + e.getMessage());
        }
        if (character != null) {
            character.displayCharacterInfo();
        }
    }
    
    private static void newGame(Scanner scanner) {
        Character character = CharacterCreator.createCharacter(scanner);
        character.saveCharacterToCSV();
        character.displayCharacterInfo();
        System.out.println("Created Character.csv file! Character saved!");
    }

    public static void spinnerAnimation() throws InterruptedException {
        String[] spinner = { "\\", "|", "/", "_" }; // Frames for the spinner
        for (int i = 0; i < 17; i++) { 
            System.out.print("\rLoading... " + spinner[i % spinner.length]);
            Thread.sleep(100); // 100ms delay for animation
        }
        System.out.print("\r"); // Clears the spinner line
    }
}

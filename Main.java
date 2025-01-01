// Main.java
import java.io.*;
import java.util.Scanner;

import javaTBRPG.Encounter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = displayMenu(scanner);

            switch (choice) {
                case 1 -> continueGame(scanner);
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

    // TODO
    // Modularize non-main Methods into new files for handling Game Start/Load?
    private static int displayMenu(Scanner scanner) {
        System.out.println("\n+----------------------------------------------+");
        System.out.println("                   Main Menu");
        System.out.println("+----------------------------------------------+");
        System.out.printf("| %-15s : %-25s  |\n", "1. Continue", "");
        System.out.printf("| %-15s : %-25s  |\n", "2. New Game", "");
        System.out.printf("| %-15s : %-25s  |\n", "3. Exit", "");
        System.out.println("+----------------------------------------------+");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public static void continueGame(Scanner scanner) {
        Character character = Character.loadCharacter();
        try {
            spinnerAnimation();
        } catch (InterruptedException e) {
            System.err.println("Animation interrupted: " + e.getMessage());
        }

        if (character != null) {
            character.displayCharacterInfo();
            startEncounter(scanner, character);
        }
    }
    
    private static void newGame(Scanner scanner) {
        Character character = CharacterCreator.createCharacter(scanner);
        character.saveCharacterToCSV();
        character.displayCharacterInfo();
        System.out.println("Created Character.csv file! Character saved!");
        startEncounter(scanner, character);
    }

    private static void startEncounter(Scanner scanner, Character character) {
        System.out.println("Starting an encounter...");
        Encounter encounter = new Encounter();
        encounter.startEncounter(character);
    }

    // TODO
    // Create Utils file for utility functions 
    public static void spinnerAnimation() throws InterruptedException {
        String[] spinner = { "\\", "|", "/", "-" }; // Frames for the spinner
        for (int i = 0; i < 17; i++) { 
            System.out.print("\rLoading... " + spinner[i % spinner.length]);
            Thread.sleep(75); // 100ms delay for animation
        }
        System.out.print("\r"); // Clear the spinner line
    }
}

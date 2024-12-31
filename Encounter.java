// Encounter.java
import java.util.*;
import java.io.*;

public class Encounter {

    private static class EncounterNode {
        List<String> dialogLines;
        List<String> actions;
        Map<String, EncounterNode> nextNodes;

        public EncounterNode(List<String> dialogLines, List<String> actions) {
            this.dialogLines = dialogLines;
            this.actions = actions;
            this.nextNodes = new HashMap<>();
        }

        public void addNextNode(String action, EncounterNode node) {
            nextNodes.put(action, node);
        }

        public EncounterNode getNextNode(String action) {
            return nextNodes.get(action);
        }
    }

    private EncounterNode root;

    public Encounter() {
        root = createSampleEncounter(); // Replace with file-based loader if needed
    }

    public void startEncounter(Character character) {
        Scanner scanner = new Scanner(System.in);
        EncounterNode currentNode = root;

        while (currentNode != null) {
            displayDialog(currentNode.dialogLines, character);

            if (currentNode.actions.isEmpty()) {
                break; // End of encounter
            }

            System.out.println("Choose an action:");
            for (int i = 0; i < currentNode.actions.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, currentNode.actions.get(i));
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice < 1 || choice > currentNode.actions.size()) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            String selectedAction = currentNode.actions.get(choice - 1);
            currentNode = currentNode.getNextNode(selectedAction);
        }

        System.out.println("The encounter has ended.");
    }

    private void displayDialog(List<String> dialogLines, Character character) {
        for (String line : dialogLines) {
            String processedLine = line.replace("{character.name}", character.name)
                                       .replace("{character.class}", character.getClass().getSimpleName());
            System.out.println(processedLine);
            delay(1000); // Delay between lines
        }
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private EncounterNode createSampleEncounter() {
        // Sample Encounter Setup
        EncounterNode start = new EncounterNode(
            Arrays.asList(
                "{character.name}'s adventure begins in the land of Eryndor.",
                "A realm where royalty rules with iron crowns and commonfolk serve the ruling kingdom steadfastly."
            ),
            Arrays.asList("Proceed to Castle", "Explore Nearby")
        );

        EncounterNode castle = new EncounterNode(
            Arrays.asList(
                "{character.name} arrives at the perimeter of Castle Varathorn.",
                "Clutched in your hand is a letter bearing the royal seal of King Aldrin Greyhawk."
            ),
            Arrays.asList("Enter the Castle", "Turn Back")
        );

        EncounterNode explore = new EncounterNode(
            Arrays.asList(
                "You explore the nearby forest.",
                "The area is dense and full of mysterious creatures."
            ),
            Collections.singletonList("Continue Exploring")
        );

        start.addNextNode("Proceed to Castle", castle);
        start.addNextNode("Explore Nearby", explore);

        return start;
    }
}
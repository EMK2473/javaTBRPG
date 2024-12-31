import java.util.*;
import java.io.*;

public class Encounter {

    private static class EncounterNode {
        List<String> dialogLines;
        List<String> visitedDialogLines; // Dialog for revisited nodes
        List<String> actions;
        Map<String, EncounterNode> nextNodes;
        boolean visited; // Track if the node has been visited
    
        public EncounterNode(List<String> dialogLines, List<String> visitedDialogLines, List<String> actions) {
            this.dialogLines = dialogLines;
            this.visitedDialogLines = visitedDialogLines;
            this.actions = actions;
            this.nextNodes = new HashMap<>();
            this.visited = false; // Default to not visited
        }
    
        public void addNextNode(String action, EncounterNode node) {
            nextNodes.put(action, node);
        }
    
        public EncounterNode getNextNode(String action) {
            return nextNodes.get(action);
        }
    
        public boolean isVisited() {
            return visited;
        }
    
        public void setVisited(boolean visited) {
            this.visited = visited;
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
            if (currentNode.isVisited() && currentNode.visitedDialogLines != null) {
                displayDialog(currentNode.visitedDialogLines, character);
            } else {
                displayDialog(currentNode.dialogLines, character);
                currentNode.setVisited(true); // Mark as visited
            }
    
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
                                       .replace("{character.class}", character.classType);
            System.out.println(processedLine);
            delay(2000); // Delay between lines
        }
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // private EncounterNode createSampleEncounter() {
    //     // Sample Encounter Setup
    //     EncounterNode start = new EncounterNode(
    //         Arrays.asList(
    //             "{character.name}'s adventure begins in the land of Eryndor.",
    //             "A realm where royalty rules with iron crowns and commonfolk serve the ruling kingdom steadfastly.",
    //             "{character.name} arrives at the perimeter of Castle Varathorn, a towering fortress nestled in the mountains.",
    //             "Clutched in your hand is a letter bearing the royal seal of King Aldrin Greyhawk.",
    //             "The letter, though cryptic, makes it clear: you have been summoned by the king for your unique {character.class} skills.",
    //             "The letter insists that this is a matter of great importance, one that cannot be refused.",
    //             "Failure to comply would surely result in consequences you cannot afford."
    //         ),
    //         Arrays.asList("Proceed to Castle")
    //     );

    //     EncounterNode castle = new EncounterNode(
    //         Arrays.asList(
    //             "{character.name} arrives at the perimeter of Castle Varathorn.",
    //             "Clutched in your hand is a letter bearing the royal seal of King Aldrin Greyhawk."
    //         ),
    //         Arrays.asList("Enter the Castle", "Explore Nearby", "Turn Back")
    //     );

        // EncounterNode explore = new EncounterNode(
        //     Arrays.asList(
        //         "You explore the nearby forest.",
        //         "The wooded grove is dense and full of the sounds of mysterious creatures."
        //     ),
        //     Arrays.asList("Turn Back")
        // );

    //     castle.addNextNode("Turn Back", start);
    //     start.addNextNode("Proceed to Castle", castle);
    //     start.addNextNode("Explore Nearby", explore);

    //     return start;
    // }


    private EncounterNode createSampleEncounter() {
        // Sample Encounter Setup
        EncounterNode start = new EncounterNode(
            Arrays.asList(
                "{character.name}'s adventure begins in the land of Eryndor.",
                "A realm where royalty rules with iron crowns and commonfolk serve the ruling kingdom steadfastly.",
                "{character.name} arrives at the perimeter of Castle Varathorn, a towering fortress nestled in the mountains.",
                "Clutched in your hand is a letter bearing the royal seal of King Aldrin Greyhawk.",
                "The letter, though cryptic, makes it clear: you have been summoned by the king for your unique {character.class} skills.",
                "The letter insists that this is a matter of great importance, one that cannot be refused.",
                "Failure to comply would surely result in consequences you cannot afford."
            ),
            Arrays.asList(
                "{character.name} stands again at the gates of Eryndor, reminded of past choices."
            ),
            Arrays.asList("Proceed to Castle", "Explore Nearby")
        );
    
        EncounterNode castle = new EncounterNode(
            Arrays.asList(
                "{character.name} arrives at the perimeter of Castle Varathorn.",
                "Clutched in your hand is a letter bearing the royal seal of King Aldrin Greyhawk."
            ),
            Arrays.asList(
                "The castle gates loom large again. This time, there's no hesitation in your steps."
            ),
            Arrays.asList("Enter the Castle", "Turn Back")
        );
    
        EncounterNode explore = new EncounterNode(
            Arrays.asList(
                "You explore the nearby forest.",
                "The wooded grove is dense and full of the sounds of mysterious creatures."
            ),
            Arrays.asList(
                "The forest path seems familiar, and the sounds of the creatures no longer unsettle you."
            ),
            Arrays.asList("Turn Back")
        );
    
        // Map the actions to the appropriate nodes
        start.addNextNode("Proceed to Castle", castle);
        start.addNextNode("Explore Nearby", explore);
        castle.addNextNode("Turn Back", start);
        explore.addNextNode("Turn Back", start);
    
        return start;
    }
    
}

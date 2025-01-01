import java.util.*;

public class Encounter {

    public static class EncounterNode {
        List<String> dialogLines;
        List<String> visitedDialogLines;
        List<String> actions;
        Map<String, EncounterNode> nextNodes;
        boolean visited;

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
        root = EncounterLoader.createSampleEncounter(); // Loads encounters from separate class
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
            delay(2500); // Delay between lines
        }
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

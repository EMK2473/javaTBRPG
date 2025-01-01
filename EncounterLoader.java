import java.util.*;

public class EncounterLoader {

    public static Encounter.EncounterNode createSampleEncounter() {
        // Sample Encounter Setup
        Encounter.EncounterNode start = new Encounter.EncounterNode(
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

        Encounter.EncounterNode castle = new Encounter.EncounterNode(
            Arrays.asList(
                "{character.name} arrives at the perimeter of Castle Varathorn.",
                "Clutched in your hand is a letter bearing the royal seal of King Aldrin Greyhawk."
            ),
            Arrays.asList(
                "The castle gates loom large again. This time, there's no hesitation in your steps."
            ),
            Arrays.asList("Enter the Castle", "Turn Back")
        );

        Encounter.EncounterNode explore = new Encounter.EncounterNode(
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

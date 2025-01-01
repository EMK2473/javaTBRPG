// CharacterCreator.java
import java.util.LinkedHashMap;
import java.util.Scanner;

public class CharacterCreator {

    public static Character createCharacter(Scanner scanner) {
        // Enter name
        System.out.println("\n+----------------------------------------------+");
        System.out.println("              Enter character name: ");
        System.out.println("+----------------------------------------------+");
        String name = scanner.nextLine();
        
        // Enter race option
        System.out.println("\n+----------------------------------------------+");
        System.out.println("            Choose a character race");
        System.out.println("+----------------------------------------------+");
        System.out.printf("| %-15s : %-25s  |\n", "1. Human", "");
        System.out.printf("| %-15s : %-25s  |\n", "2. Half-Elf", "");
        System.out.printf("| %-15s : %-25s  |\n", "3. Elf", "");
        System.out.printf("| %-15s : %-25s  |\n", "4. Dwarf", "");
        System.out.printf("| %-15s : %-25s  |\n", "5. Halfling", "");
        System.out.printf("| %-15s : %-25s  |\n", "6. Tiefling", "");
        System.out.printf("| %-15s : %-25s  |\n", "7. Dragonborn", "");
        System.out.printf("| %-15s : %-25s  |\n", "8. Gnome", "");
        System.out.printf("| %-15s : %-25s  |\n", "9. Half-Orc", "");
        System.out.println("+----------------------------------------------+");
        System.out.print("Choose a race (1-9): ");
        
        int raceChoice = scanner.nextInt();
        scanner.nextLine();

        // Assign race
        String race = "";
        switch (raceChoice) {
            case 1: race = "Human"; break;
            case 2: race = "Half-Elf"; break;
            case 3: race = "Elf"; break;
            case 4: race = "Dwarf"; break;
            case 5: race = "Halfling"; break;
            case 6: race = "Tiefling"; break;
            case 7: race = "Dragonborn"; break;
            case 8: race = "Gnome"; break;
            case 9: race = "Half-Orc"; break;
            default: race = "Unknown"; break;
        }


        System.out.println("Chosen race: " + race);
        try {
            spinnerAnimation();
        } catch (InterruptedException e) {
            System.err.println("Animation interrupted: " + e.getMessage());
        }

        
        System.out.println("\n+----------------------------------------------+");
        System.out.println("             Choose a character class");
        System.out.println("+----------------------------------------------+");
        System.out.printf("| %-15s : %-25s  |\n", "1. Barbarian", "");
        System.out.printf("| %-15s : %-25s  |\n", "2. Bard", "");
        System.out.printf("| %-15s : %-25s  |\n", "3. Cleric", "");
        System.out.printf("| %-15s : %-25s  |\n", "4. Druid", "");
        System.out.printf("| %-15s : %-25s  |\n", "5. Fighter", "");
        System.out.printf("| %-15s : %-25s  |\n", "6. Monk", "");
        System.out.printf("| %-15s : %-25s  |\n", "7. Paladin", "");
        System.out.printf("| %-15s : %-25s  |\n", "8. Ranger", "");
        System.out.printf("| %-15s : %-25s  |\n", "9. Rogue", "");
        System.out.printf("| %-15s : %-25s  |\n", "10. Sorcerer", "");
        System.out.printf("| %-15s : %-25s  |\n", "11. Warlock", "");
        System.out.printf("| %-15s : %-25s  |\n", "12. Wizard", "");
        System.out.println("+----------------------------------------------+");
        System.out.print("Choose a class (1-12): ");

        int classChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            spinnerAnimation();
        } catch (InterruptedException e) {
            System.err.println("Animation interrupted: " + e.getMessage());
        }

        String classType = "";
        int HP = 0, maxHP = 0, MP = 0, maxMP = 0, AC = 0, DC = 0;
        String weapon = "";
        LinkedHashMap<String, Integer> spells = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> abilities = new LinkedHashMap<>();

        switch (classChoice) {
            case 1 -> {
                classType = "Barbarian";
                HP = 14; MP = 2; maxMP = 2; AC = 14; DC = 10;
                maxHP = 14;
                weapon = "Greataxe";
                spells.put("Rage", 0);
                abilities.put("Strength", 15);
                abilities.put("Dexterity", 10);
                abilities.put("Constitution", 14);
                abilities.put("Intelligence", 8);
                abilities.put("Wisdom", 12);
                abilities.put("Charisma", 10);
            }
            case 2 -> {
                classType = "Bard";
                HP = 8; MP = 6; maxMP = 6; AC = 12; DC = 14;
                maxHP = 8;
                weapon = "Rapier";
                spells.put("Charm Person", 2);
                abilities.put("Strength", 10);
                abilities.put("Dexterity", 14);
                abilities.put("Constitution", 12);
                abilities.put("Intelligence", 12);
                abilities.put("Wisdom", 10);
                abilities.put("Charisma", 15);
            }
            case 3 -> {
                classType = "Cleric";
                HP = 10; MP = 6; maxMP = 6; AC = 16; DC = 14;
                maxHP = 10;
                weapon = "Mace";
                spells.put("Cure Wounds", 3);
                abilities.put("Strength", 12);
                abilities.put("Dexterity", 10);
                abilities.put("Constitution", 14);
                abilities.put("Intelligence", 10);
                abilities.put("Wisdom", 15);
                abilities.put("Charisma", 8);
            }
            case 4 -> {
                classType = "Druid";
                HP = 8; MP = 8; maxMP = 8; AC = 12; DC = 14;
                maxHP = 8;
                weapon = "Quarterstaff";
                spells.put("Entangle", 3);
                abilities.put("Strength", 10);
                abilities.put("Dexterity", 12);
                abilities.put("Constitution", 14);
                abilities.put("Intelligence", 12);
                abilities.put("Wisdom", 15);
                abilities.put("Charisma", 8);
            }
            case 5 -> {
                classType = "Fighter";
                HP = 12; MP = 2; maxMP = 2; AC = 16; DC = 10;
                maxHP = 12;
                weapon = "Longsword";
                spells.put("Second Wind", 0);
                abilities.put("Strength", 15);
                abilities.put("Dexterity", 12);
                abilities.put("Constitution", 14);
                abilities.put("Intelligence", 10);
                abilities.put("Wisdom", 10);
                abilities.put("Charisma", 8);
            }
            case 6 -> {
                classType = "Monk";
                HP = 10; MP = 4; maxMP = 4; AC = 15; DC = 12;
                maxHP = 10;
                weapon = "Shortsword";
                spells.put("Flurry of Blows", 1);
                abilities.put("Strength", 14);
                abilities.put("Dexterity", 15);
                abilities.put("Constitution", 12);
                abilities.put("Intelligence", 10);
                abilities.put("Wisdom", 12);
                abilities.put("Charisma", 10);
            }
            case 7 -> {
                classType = "Paladin";
                HP = 12; MP = 4; maxMP = 4; AC = 18; DC = 14;
                maxHP = 12;
                weapon = "Warhammer";
                spells.put("Divine Smite", 2);
                abilities.put("Strength", 16);
                abilities.put("Dexterity", 10);
                abilities.put("Constitution", 14);
                abilities.put("Intelligence", 8);
                abilities.put("Wisdom", 12);
                abilities.put("Charisma", 14);
            }
            case 8 -> {
                classType = "Ranger";
                HP = 10; MP = 4; maxMP = 4; AC = 14; DC = 12;
                maxHP = 10;
                weapon = "Longbow";
                spells.put("Hunter's Mark", 1);
                abilities.put("Strength", 12);
                abilities.put("Dexterity", 16);
                abilities.put("Constitution", 12);
                abilities.put("Intelligence", 10);
                abilities.put("Wisdom", 14);
                abilities.put("Charisma", 8);
            }
            case 9 -> {
                classType = "Rogue";
                HP = 8; MP = 2; maxMP = 2; AC = 14; DC = 10;
                maxHP = 8;
                weapon = "Dagger";
                spells.put("Sneak Attack", 0);
                abilities.put("Strength", 10);
                abilities.put("Dexterity", 16);
                abilities.put("Constitution", 12);
                abilities.put("Intelligence", 12);
                abilities.put("Wisdom", 10);
                abilities.put("Charisma", 14);
            }
            case 10 -> {
                classType = "Sorcerer";
                HP = 6; MP = 10; maxMP = 10; AC = 12; DC = 15;
                maxHP = 6;
                weapon = "Light Crossbow";
                spells.put("Magic Missile", 3);
                abilities.put("Strength", 8);
                abilities.put("Dexterity", 12);
                abilities.put("Constitution", 10);
                abilities.put("Intelligence", 14);
                abilities.put("Wisdom", 10);
                abilities.put("Charisma", 16);
            }
            case 11 -> {
                classType = "Warlock";
                HP = 8; MP = 8; maxMP = 8; AC = 13; DC = 14;
                maxHP = 8;
                weapon = "Pact Weapon";
                spells.put("Eldritch Blast", 2);
                abilities.put("Strength", 10);
                abilities.put("Dexterity", 12);
                abilities.put("Constitution", 12);
                abilities.put("Intelligence", 10);
                abilities.put("Wisdom", 14);
                abilities.put("Charisma", 16);
            }
            case 12 -> {
                classType = "Wizard";
                HP = 6; MP = 12; maxMP = 12; AC = 12; DC = 15;
                maxHP = 6;
                weapon = "Quarterstaff";
                spells.put("Fireball", 3);
                abilities.put("Strength", 8);
                abilities.put("Dexterity", 14);
                abilities.put("Constitution", 12);
                abilities.put("Intelligence", 16);
                abilities.put("Wisdom", 10);
                abilities.put("Charisma", 12);
            }
            default -> {
                System.out.println("Invalid choice. Defaulting to Fighter.");
                classType = "Fighter";
                HP = 12; MP = 2; maxMP = 2; AC = 16; DC = 10;
                maxHP = 12;
                weapon = "Longsword";
                spells.put("Second Wind", 0);
                abilities.put("Strength", 15);
                abilities.put("Dexterity", 12);
                abilities.put("Constitution", 14);
                abilities.put("Intelligence", 10);
                abilities.put("Wisdom", 10);
                abilities.put("Charisma", 8);
            }
        }
        System.out.println("HP: " + HP + ", maxHP: " + maxHP);
        System.out.println("MP: " + MP + ", maxMP: " + maxMP);
        return new Character(name, race, classType, HP, maxHP, MP, maxMP, 0, 100, AC, DC, weapon, spells, abilities);
    }

    public static void spinnerAnimation() throws InterruptedException {
        String[] spinner = { "\\", "|", "/", "-" }; // Frames for the spinner
        for (int i = 0; i < 17; i++) { 
            System.out.print("\rLoading... " + spinner[i % spinner.length]);
            Thread.sleep(75); // 100ms delay for animation
        }
        System.out.print("\r"); // Clear the spinner line
    }
}

//Character.java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Character {
    String name;
    String race;
    String classType;
    int HP, maxHP, MP, maxMP, XP, xpToLevel, AC, DC;
    String weapon;
    LinkedHashMap<String, Integer> spells;
    LinkedHashMap<String, Integer> abilities; // LinkedHashMap to preserve order when iterating

    public Character(String name, String race, String classType, int HP, int maxHP, int MP, int maxMP, int XP, int xpToLevel, int AC, int DC, String weapon, LinkedHashMap<String, Integer> spells, LinkedHashMap<String, Integer> abilities) {
        this.name = name;
        this.race = race;
        this.classType = classType;
        this.HP = HP;
        this.maxHP = maxHP;
        this.MP = MP;
        this.maxMP = maxMP;
        this.XP = XP;
        this.xpToLevel = xpToLevel;
        this.AC = AC;
        this.DC = DC;
        this.weapon = weapon;
        this.spells = spells;
        this.abilities = abilities;
    }

    public void displayCharacterInfo() {
        System.out.println("\nCharacter Information:");
        System.out.println("Name: " + name);
        System.out.println("Race: " + race);
        System.out.println("Class: " + classType);
        System.out.println("HP: " + HP + "/" + maxHP);
        System.out.println("MP: " + MP + "/" + maxMP);
        System.out.println("XP: " + XP);
        System.out.println("XP to Level: " + xpToLevel);
        System.out.println("AC: " + AC);
        System.out.println("DC: " + DC);
        System.out.println("Weapon: " + weapon);
        
         // Display abilities using the LinkedHashMap (which maintains insertion order)
        System.out.println("Abilities:");
        for (Map.Entry<String, Integer> entry : abilities.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    
        // Display spells
        System.out.println("Spells:");
        for (Map.Entry<String, Integer> spell : spells.entrySet()) {
            System.out.println(spell.getKey() + " (Cost: " + spell.getValue() + " MP)");
        }
    }

    // Load character data from csv file
    public static Character loadCharacter() {
        File file = new File("character.csv");
        if (!file.exists()) {
            System.out.println("Character file not found.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read character information (Line 1)
            String[] characterInfo = br.readLine().split(",");
            String name = characterInfo[0];
            String race = characterInfo[1];
            String classType = characterInfo[2];

            // Read stats (Line 2)
            String[] stats = br.readLine().split(",");
            int HP = Integer.parseInt(stats[0]);
            int maxHP = Integer.parseInt(stats[1]);
            int MP = Integer.parseInt(stats[2]);
            int maxMP = Integer.parseInt(stats[3]);
            int XP = Integer.parseInt(stats[4]);
            int xpToLevel = Integer.parseInt(stats[5]);
            int AC = Integer.parseInt(stats[6]);
            int DC = Integer.parseInt(stats[7]);

            // Read abilities (Line 3)
            String[] abilityStrings = br.readLine().split(",");
            LinkedHashMap<String, Integer> abilities = new LinkedHashMap<>();
            for (String abilityString : abilityStrings) {
                String[] abilityData = abilityString.split(":");
                String abilityName = abilityData[0].trim();
                int abilityValue = Integer.parseInt(abilityData[1].trim());
                abilities.put(abilityName, abilityValue);
            }

            // Read spells (Line 4)
            String[] spellStrings = br.readLine().split(",");
            LinkedHashMap<String, Integer> spells = new LinkedHashMap<>();
            for (String spellString : spellStrings) {
                String[] spellData = spellString.split(":");
                String spellName = spellData[0].trim();
                int mpCost = Integer.parseInt(spellData[1].trim());
                spells.put(spellName, mpCost);
            }

            // Return the Character object
            return new Character(name, race, classType, HP, maxHP, MP, maxMP, XP, xpToLevel, AC, DC, "Default Weapon", spells, abilities);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("No saved character found.");
        return null;
    }

    // Method to convert abilities to the CSV format
    public String abilitiesToCSV() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : abilities.entrySet()) {
            sb.append(entry.getKey())
              .append(":")
              .append(entry.getValue())
              .append(",");
        }
        // Remove the last comma
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Method to convert spells to the CSV format
    public String spellsToCSV() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : spells.entrySet()) {
            sb.append(entry.getKey())
              .append(":")
              .append(entry.getValue())
              .append(",");
        }
        // Remove the last comma
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Method to save the character to CSV
    public void saveCharacterToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("character.csv"))) {
            // Line 1: CharacterName,Race,Class
            writer.write(name + "," + race + "," + classType);
            writer.newLine();
            
            // Line 2: HP,maxHP,MP,XP,XPtoLevel,AC,DC
            writer.write(HP + "," + maxHP + "," + MP + "," + maxMP + "," + XP + "," + xpToLevel + "," + AC + "," + DC);
            writer.newLine();
            
            // Line 3: Strength:Value(int),Dexterity:Value(int),Constitution:Value(int),Intelligence:Value(int),Wisdom:Value(int),Charisma:Value(int)
            writer.write(abilitiesToCSV());
            writer.newLine();

            // Line 4: spellName:mpCost(int),
            writer.write(spellsToCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

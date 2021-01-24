package developer.javokhirbek;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // System objects
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // Game Variables
        String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assassin"};
        int maxEnergyHealth = 75;
        int enemyAttackDamage = 25;

        // Player variables
        int health = 100;
        int attackDamage = 50;
        int numHealthPotions = 3; // Player has 3 health pots (can play for 3 times)
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // percentage

        boolean running = true;

        System.out.println("Welcome to the Dungeon!");

        GAME:
        while (running) {
            System.out.println("-------------------------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnergyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " appeared! #\n");
            //     Ex:   # Skeleton appeared! #

            // loop till enemy is dead
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = sc.nextLine();
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTaken;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\t> You  receive " + damageTaken + " in retaliation!");

                    if (health < 1) {
                        System.out.println("\t> You have taken too much damage, you are to weak to go on!");
                        break;
                    }
                } else if (input.equals("2")) {
                    if (numHealthPotions > 0) {
                        health += healthPotionHealAmount;
                        numHealthPotions--;
                        System.out.println("\t> You drink health potion, healing yourself for " + healthPotionHealAmount + "."
                        + "\n\t> You now have " + health + " HP." +
                                "\n\t> You have " + numHealthPotions + " health potions left.\n");
                    } else {
                        System.out.println("\t> You have no health potions left! Deaf enemy for a chance to get one!");
                    }
                } else if (input.equals("3")) {
                    System.out.println("\tYou ran away from the " + enemy + "!");
                    continue GAME;
                } else {
                    System.out.println("\tInvalid command!");
                }
            }

            if (health < 1) {
                System.out.println("You limp out of the dungeon, weak from battle");
                break;
            }

            System.out.println("-------------------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + health + "HP left #");
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You now have " + numHealthPotions + " health position(s). # ");
            }
            System.out.println("-------------------------------------------------------------");
            System.out.println("What would we like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = sc.nextLine();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command!");
                input = sc.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("You continue on your adventure!");
            } else if (input.equals("2")){
                System.out.println("You exited dungeon successfully from your adventures");
                break;
            }
        }

        System.out.println("#######################");
        System.out.println("# THANKS FOR PLAYING! #");
        System.out.println("#######################");

    }
}

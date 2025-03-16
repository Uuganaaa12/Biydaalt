package com.mycompany.app.cli;

public class HelpFormatter {

    public static void displayHelp() {
        System.out.println("--------< FlashCard >--------");
        System.out.println("  - How many correct repetitions are required for each card");
        System.out.println("  - The order of flashcards (random, worst-first, recent-mistakes-first)");
        System.out.println("  - Whether to invert cards (swap questions and answers)");
        System.out.println("\nCommands during the flashcard session:");
        System.out.println("  Type 'skip' to skip the current question");
        System.out.println("  Type 'exit' to quit the application");
        System.out.println("\nAfter completing all cards, you can:");
        System.out.println("  - Restart with the same cards");
        System.out.println("  - Reconfigure options (repetitions, order, inversion)");
    }
}
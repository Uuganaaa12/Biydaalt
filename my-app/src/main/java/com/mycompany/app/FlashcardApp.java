package com.mycompany.app;

import com.mycompany.app.cli.HelpFormatter;
import com.mycompany.app.model.Card;
import com.mycompany.app.service.CardLoader;
import com.mycompany.app.service.FlashcardSession;
import com.mycompany.app.achievement.AchievementTracker;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FlashcardApp {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("--------< FlashCard App >--------");
        
        if (args.length > 0 && (args[0].equals("--help") || args[0].equals("-h"))) {
            HelpFormatter.displayHelp();
            return;
        }
        
        String cardsFile = getCardsFile(args);
        if (cardsFile == null) {
            System.err.println("Error: No valid card file specified.");
            scanner.close();
            return;
        }
        
        try {
            List<Card> cards = CardLoader.loadCardsFromFile(cardsFile);
            if (cards.isEmpty()) {
                System.err.println("Error: The file doesn't contain any valid flashcards.");
                scanner.close();
                return;
            }
            
            AchievementTracker tracker = new AchievementTracker();
            
            // Create session without options - prompting will happen inside
            FlashcardSession session = new FlashcardSession(cards, tracker);
            session.start();
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Please ensure the file exists and is accessible.");
        } finally {
            scanner.close();
        }
    }
    
    private static String getCardsFile(String[] args) {
        String fileName = null;
        
        // Check if file path was provided in args
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                File file = new File(arg);
                if (file.exists() && file.isFile()) {
                    return arg;
                }
            }
        }
        
        // If no valid file in args, prompt the user
        System.out.println("Please enter the path to the flashcard file:");
        fileName = scanner.nextLine().trim();
        
        // Validate the file exists
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Error: The specified file does not exist or is not accessible.");
            return null;
        }
        
        return fileName;
    }
}
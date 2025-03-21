package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.mycompany.app.achievement.AchievementTracker;
import com.mycompany.app.cli.HelpFormatter;
import com.mycompany.app.model.Card;
import com.mycompany.app.service.CardLoader;
import com.mycompany.app.service.FlashcardSession;

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
            scanner.close();
            return;
        }
        
        try {
            List<Card> cards = CardLoader.loadCardsFromFile(cardsFile);
            if (cards.isEmpty()) {
                System.err.println("Error: file dotor card baihgui baina.");
                scanner.close();
                return;
            }
            
            AchievementTracker tracker = new AchievementTracker();
            FlashcardSession session = new FlashcardSession(cards, tracker);
            session.start();
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private static String getCardsFile(String[] args) {
        String fileName = null;
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                File file = new File(arg);
                if (file.exists() && file.isFile()) {
                    return arg;
                }
            }
        }

        fileName = scanner.nextLine().trim();

        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Error: Zaasan file baihgui esvel handah bolomjgui baina.");
            return null;
        }
        
        return fileName;
    }
}
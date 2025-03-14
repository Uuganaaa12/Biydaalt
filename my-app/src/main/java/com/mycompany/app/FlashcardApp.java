package com.mycompany.app;

import com.mycompany.app.cli.CommandLineOptions;
import com.mycompany.app.cli.HelpFormatter;
import com.mycompany.app.model.Card;
import com.mycompany.app.service.CardLoader;
import com.mycompany.app.service.FlashcardSession;
import com.mycompany.app.organizer.CardOrganizer;
import com.mycompany.app.organizer.RandomSorter;
import com.mycompany.app.organizer.WorstFirstSorter;
import com.mycompany.app.organizer.RecentMistakesFirstSorter;
import com.mycompany.app.achievement.AchievementTracker;

import java.io.IOException;
import java.util.List;

public class FlashcardApp {
    public static void main(String[] args) {
        CommandLineOptions options = new CommandLineOptions(args);
   
        if (options.isHelp()) {
            HelpFormatter.displayHelp();
            return;
        }
        
        try {
            List<Card> cards = CardLoader.loadCardsFromFile(options.getCardsFile());
            if (options.isInvertCards()) {
                cards.forEach(Card::invert);
            }

            CardOrganizer organizer = createOrganizer(options.getOrder());
            
            AchievementTracker tracker = new AchievementTracker();
            
            FlashcardSession session = new FlashcardSession(cards, organizer, 
                                                          options.getRepetitions(), tracker);
            session.start();

            tracker.displayAchievements();
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static CardOrganizer createOrganizer(String order) {
        switch (order) {
            case "worst-first":
                return new WorstFirstSorter();
            case "recent-mistakes-first":
                return new RecentMistakesFirstSorter();
            case "random":
            default:
                return new RandomSorter();
        }
    }
}
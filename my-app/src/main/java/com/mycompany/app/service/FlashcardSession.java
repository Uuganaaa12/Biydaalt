package com.mycompany.app.service;

import com.mycompany.app.model.Card;
import com.mycompany.app.organizer.CardOrganizer;
import com.mycompany.app.achievement.AchievementTracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlashcardSession {
    private final List<Card> cards;
    private final CardOrganizer organizer;
    private final int requiredRepetitions;
    private final AchievementTracker tracker;
    private final Scanner scanner;
    private final Map<Card, Integer> correctAnswers;
    private final Map<Card, Integer> totalAttempts;

    public FlashcardSession(List<Card> cards, CardOrganizer organizer, 
                          int requiredRepetitions, AchievementTracker tracker) {
        this.cards = cards;
        this.organizer = organizer;
        this.requiredRepetitions = requiredRepetitions;
        this.tracker = tracker;
        this.scanner = new Scanner(System.in);
        this.correctAnswers = new HashMap<>();
        this.totalAttempts = new HashMap<>();

        for (Card card : cards) {
            correctAnswers.put(card, 0);
            totalAttempts.put(card, 0);
        }
    }

    public void start() {
        boolean allCorrect = true;
        int round = 1;
        
        while (true) {
            System.out.println("\n--- Round " + round + " ---");

            List<Card> organizedCards = organizer.organize(cards, correctAnswers, totalAttempts);
            
            boolean roundComplete = true;
            long roundStartTime = System.currentTimeMillis();
            int roundAnswers = 0;
            
            for (Card card : organizedCards) {
                if (correctAnswers.get(card) < requiredRepetitions) {
                    roundComplete = false;
                    
                    long startTime = System.currentTimeMillis();
                    
                    System.out.println("\nQuestion: " + card.getQuestion());
                    System.out.print("Your answer: ");
                    String answer = scanner.nextLine().trim();
                    if (answer.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting the session. Goodbye!");
                        scanner.close();
                        return;
                    }
                    
                    long responseTime = System.currentTimeMillis() - startTime;
                    
                    totalAttempts.put(card, totalAttempts.get(card) + 1);
                    roundAnswers++;
                    
                    if (card.checkAnswer(answer)) {
                        System.out.println("Correct!");
                        correctAnswers.put(card, correctAnswers.get(card) + 1);

                        tracker.trackResponseTime(responseTime / 1000.0);

                        if (totalAttempts.get(card) > 5) {
                            tracker.unlockAchievement("REPEAT");
                        }

                        if (correctAnswers.get(card) >= 3) {
                            tracker.unlockAchievement("CONFIDENT");
                        }
                    } else {
                        System.out.println("Incorrect");
                        System.out.println("The correct answer is: " + card.getAnswer());
                        allCorrect = false;
                    }
                }
            }
            if (roundComplete) {
                break;
            }
            long roundEndTime = System.currentTimeMillis();
            double avgResponseTime = roundAnswers > 0 ? 
                    (roundEndTime - roundStartTime) / 1000.0 / roundAnswers : 0;
            
            System.out.println("\nAverage response time for this round: " + 
                    String.format("%.2f", avgResponseTime) + " seconds");
            
            if (avgResponseTime < 5.0) {
                tracker.unlockAchievement("FAST");
            }
            round++;
        }

        if (allCorrect && round > 1) {
            tracker.unlockAchievement("CORRECT");
        }
        
        System.out.println("\nCongratulations! You've completed all flashcards.");
        
        scanner.close();
    }
}
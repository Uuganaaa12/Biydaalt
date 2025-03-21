package com.mycompany.app.organizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.app.model.Card;

public class CardOrganizerTest {

    private List<Card> cards;
    private Map<Card, Integer> correctAnswers;
    private Map<Card, Integer> totalAttempts;
    private Card card1, card2, card3;

    @BeforeEach
    public void setUp() {
        cards = new ArrayList<>();
        correctAnswers = new HashMap<>();
        totalAttempts = new HashMap<>();

        card1 = new Card("Question1", "Answer1");
        card2 = new Card("Question2", "Answer2");
        card3 = new Card("Question3", "Answer3");

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        correctAnswers.put(card1, 5);
        correctAnswers.put(card2, 2);
        correctAnswers.put(card3, 0);

        totalAttempts.put(card1, 6);
        totalAttempts.put(card2, 5);
        totalAttempts.put(card3, 3);
    }

    @Test
    public void testRandomSorter() {
        CardOrganizer organizer = new RandomSorter();
        List<Card> organized = organizer.organize(cards, correctAnswers, totalAttempts);

        assertEquals(cards.size(), organized.size());
        assertTrue(organized.containsAll(cards));
    }

    @Test
    public void testWorstFirstSorter() {
        CardOrganizer organizer = new WorstFirstSorter();
        List<Card> organized = organizer.organize(cards, correctAnswers, totalAttempts);

        int card2Incorrect = totalAttempts.get(card2) - correctAnswers.get(card2); // 5 - 2 = 3
        int card3Incorrect = totalAttempts.get(card3) - correctAnswers.get(card3); // 3 - 0 = 3
        
    
        if (card3Incorrect > card2Incorrect) {
            assertEquals(card3.getQuestion(), organized.get(0).getQuestion());
            assertEquals(card2.getQuestion(), organized.get(1).getQuestion());
        } else if (card2Incorrect > card3Incorrect) {
            assertEquals(card2.getQuestion(), organized.get(0).getQuestion());
            assertEquals(card3.getQuestion(), organized.get(1).getQuestion());
        } else {

            assertTrue(
                (organized.get(0).getQuestion().equals(card2.getQuestion()) && 
                 organized.get(1).getQuestion().equals(card3.getQuestion())) ||
                (organized.get(0).getQuestion().equals(card3.getQuestion()) && 
                 organized.get(1).getQuestion().equals(card2.getQuestion()))
            );
        }
        

        assertEquals(card1.getQuestion(), organized.get(2).getQuestion());
    }

    @Test
    public void testRecentMistakesFirstSorter() {
        CardOrganizer organizer = new RecentMistakesFirstSorter();
        List<Card> organized = organizer.organize(cards, correctAnswers, totalAttempts);

        assertEquals(cards.size(), organized.size());
        assertTrue(organized.containsAll(cards));
        
        List<Card> newCards = new ArrayList<>(cards);
        
        List<Card> reordered = organizer.organize(newCards, correctAnswers, totalAttempts);
        assertEquals(newCards.size(), reordered.size());
        assertTrue(reordered.containsAll(newCards));
    }
}
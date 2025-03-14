package com.mycompany.app.organizer;

import com.mycompany.app.model.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CardOrganizerTest {
    
    private List<Card> cards;
    private Map<Card, Integer> correctAnswers;
    private Map<Card, Integer> totalAttempts;
    private Card card1, card2, card3;
    
    @Before
    public void setup() {
        card1 = new Card("Question1", "Answer1");
        card2 = new Card("Question2", "Answer2");
        card3 = new Card("Question3", "Answer3");
        
        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        
        correctAnswers = new HashMap<>();
        totalAttempts = new HashMap<>();
        
        // Card1: 3 correct out of 5 attempts (2 incorrect)
        correctAnswers.put(card1, 3);
        totalAttempts.put(card1, 5);
        
        // Card2: 1 correct out of 4 attempts (3 incorrect)
        correctAnswers.put(card2, 1);
        totalAttempts.put(card2, 4);
        
        // Card3: 2 correct out of 2 attempts (0 incorrect)
        correctAnswers.put(card3, 2);
        totalAttempts.put(card3, 2);
    }
    
    @Test
    public void testRandomSorter() {
        CardOrganizer sorter = new RandomSorter();
        List<Card> organized = sorter.organize(cards, correctAnswers, totalAttempts);
        
        // Verify all cards are present (no additions/removals)
        assertEquals(cards.size(), organized.size());
        assertTrue(organized.contains(card1));
        assertTrue(organized.contains(card2));
        assertTrue(organized.contains(card3));
        
        // Note: We can't test randomness directly, but we can ensure the function returns
        // a valid list with all the cards
    }
    
    @Test
    public void testWorstFirstSorter() {
        CardOrganizer sorter = new WorstFirstSorter();
        List<Card> organized = sorter.organize(cards, correctAnswers, totalAttempts);
        
        // Cards should be sorted by incorrect answers (most incorrect first)
        // Card2: 3 incorrect
        // Card1: 2 incorrect
        // Card3: 0 incorrect
        assertEquals(card2, organized.get(0));
        assertEquals(card1, organized.get(1));
        assertEquals(card3, organized.get(2));
    }
    
    @Test
    public void testRecentMistakesFirstSorter() {
        CardOrganizer sorter = new RecentMistakesFirstSorter();
        
        // First organization - card2 and card1 have mistakes
        List<Card> organized = sorter.organize(cards, correctAnswers, totalAttempts);
        
        // Second organization - now the "recent mistakes" should be card2 and card1
        organized = sorter.organize(cards, correctAnswers, totalAttempts);
        
        // Verify cards with mistakes are prioritized
        assertTrue(organized.indexOf(card2) < organized.indexOf(card3));
        assertTrue(organized.indexOf(card1) < organized.indexOf(card3));
    }
}
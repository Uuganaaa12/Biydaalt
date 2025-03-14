package com.mycompany.app.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CardTest {
    
    @Test
    public void testCardCreation() {
        Card card = new Card("Question", "Answer");
        assertEquals("Question", card.getQuestion());
        assertEquals("Answer", card.getAnswer());
    }
    
    @Test
    public void testInvert() {
        Card card = new Card("Question", "Answer");
        card.invert();
        assertEquals("Answer", card.getQuestion());
        assertEquals("Question", card.getAnswer());
    }
    
    @Test
    public void testCheckAnswerCorrect() {
        Card card = new Card("Question", "Answer");
        assertTrue(card.checkAnswer("Answer"));
        assertTrue(card.checkAnswer("answer")); // Case insensitive
        assertTrue(card.checkAnswer("ANSWER")); // Case insensitive
    }
    
    @Test
    public void testCheckAnswerIncorrect() {
        Card card = new Card("Question", "Answer");
        assertFalse(card.checkAnswer("Wrong"));
        assertFalse(card.checkAnswer(""));
        assertFalse(card.checkAnswer("Answe")); // Partial match
    }
}
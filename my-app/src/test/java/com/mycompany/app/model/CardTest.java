package com.mycompany.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void testCardCreation() {
        Card card = new Card("Question", "Answer");
        assertEquals("Question", card.getQuestion());
        assertEquals("Answer", card.getAnswer());
    }

    @Test
    public void testCardInversion() {
        Card card = new Card("Question", "Answer");
        card.invert();
        assertEquals("Answer", card.getQuestion());
        assertEquals("Question", card.getAnswer());
    }

    @Test
    public void testCheckAnswerCaseSensitive() {
        Card card = new Card("Question", "Answer");
        assertTrue(card.checkAnswer("Answer"));
        assertFalse(card.checkAnswer("WrongAnswer"));
    }

    @Test
    public void testCheckAnswerCaseInsensitive() {
        Card card = new Card("Question", "Answer");
        assertTrue(card.checkAnswer("answer"));
        assertTrue(card.checkAnswer("ANSWER"));
        assertTrue(card.checkAnswer("AnSwEr"));
    }

    @Test
    public void testCheckAnswerWithWhitespace() {
        Card card = new Card("Question", "Answer with spaces");
        assertTrue(card.checkAnswer("Answer with spaces"));
        assertFalse(card.checkAnswer("Answerwithspaces"));
    }
}
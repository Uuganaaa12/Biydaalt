package com.mycompany.app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.mycompany.app.achievement.AchievementTracker;
import com.mycompany.app.model.Card;

public class FlashcardIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
    @TempDir
    Path tempDir;
    
    private File testFile;
    private List<Card> testCards;
    
    @BeforeEach
    public void setUp() throws IOException {

        testFile = tempDir.resolve("test_cards.txt").toFile();
        List<String> lines = Arrays.asList(
            "Question 1", "Answer 1",
            "Question 2", "Answer 2"
        );
        Files.write(testFile.toPath(), lines);
        

        testCards = new ArrayList<>();
        testCards.add(new Card("Question 1", "Answer 1"));
        testCards.add(new Card("Question 2", "Answer 2"));
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    

    @Test
    public void testFileCreation() {
        assertTrue(testFile.exists(), "Test file should exist");
        assertTrue(testFile.isFile(), "Test file should be a file");
    }
    

    @Test
    public void testCardBehavior() {
        Card card = new Card("Test Question", "Test Answer");
        
        assertEquals("Test Question", card.getQuestion(), "Card should store question");
        assertEquals("Test Answer", card.getAnswer(), "Card should store answer");
        

        card.invert();
        assertEquals("Test Answer", card.getQuestion(), "Inverted card should swap question and answer");
        assertEquals("Test Question", card.getAnswer(), "Inverted card should swap question and answer");
        
      
        assertTrue(card.checkAnswer("Test Question"), "Should accept correct answer");
        assertFalse(card.checkAnswer("Wrong Answer"), "Should reject incorrect answer");
    }
    

    @Test
    public void testAchievementTracker() {
        AchievementTracker tracker = new AchievementTracker();
        

        tracker.trackResponseTime(5.0);
        tracker.trackResponseTime(7.0);
        assertEquals(6.0, tracker.getAverageResponseTime(), 0.001, 
                "Should calculate correct average response time");
        
        
        tracker.unlockAchievement("TEST");
    }
}
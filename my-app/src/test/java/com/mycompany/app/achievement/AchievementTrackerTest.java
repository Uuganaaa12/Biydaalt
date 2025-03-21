package com.mycompany.app.achievement;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AchievementTrackerTest {

    private AchievementTracker tracker;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        tracker = new AchievementTracker();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testInitialState() {
        assertEquals(0, tracker.getAverageResponseTime());
    }

    @Test
    public void testTrackResponseTime() {
        tracker.trackResponseTime(2.5);
        tracker.trackResponseTime(3.5);
        assertEquals(3.0, tracker.getAverageResponseTime());
    }

    @Test
    public void testUnlockAchievement() {
        tracker.unlockAchievement("FAST");
        tracker.displayAchievements();
        
        String output = outContent.toString();
        assertTrue(output.contains("FAST"));
        assertFalse(output.contains("Odoogoor ymar ch amjilt gargaagui baina"));
    }

    @Test
    public void testMultipleAchievements() {
        tracker.unlockAchievement("FAST");
        tracker.unlockAchievement("CORRECT");
        tracker.displayAchievements();
        
        String output = outContent.toString();
        assertTrue(output.contains("FAST"));
        assertTrue(output.contains("CORRECT"));
    }

    @Test
    public void testNoAchievements() {
        tracker.displayAchievements();
        
        String output = outContent.toString();
        assertTrue(output.contains("Odoogoor ymar ch amjilt gargaagui baina"));
    }

    @Test
    public void testDuplicateAchievements() {
        tracker.unlockAchievement("FAST");
        tracker.unlockAchievement("FAST"); 
        
        outContent.reset();
        tracker.displayAchievements();
        String output = outContent.toString();
        int count = output.split("FAST").length - 1;
        assertEquals(1, count);  
    }

    @Test
    public void testAverageResponseTimeWithNoData() {
        assertEquals(0, tracker.getAverageResponseTime());
    }

    @Test
    public void testAverageResponseTimeRounding() {
        tracker.trackResponseTime(1.23);
        tracker.trackResponseTime(4.56);
        assertEquals(2.895, tracker.getAverageResponseTime(), 0.001);
    }
}
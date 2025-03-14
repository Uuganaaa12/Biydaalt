package com.mycompany.app.achievement;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AchievementTrackerTest {
    
    private AchievementTracker tracker;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    
    @Before
    public void setup() {
        tracker = new AchievementTracker();
        
        // Capture System.out for testing output
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
    public void testUnlockAchievement() {
        tracker.unlockAchievement("FAST");
        tracker.displayAchievements();
        
        String output = outContent.toString();
        assertTrue(output.contains("FAST"));
        assertFalse(output.contains("CORRECT"));
        
        // Reset output for next test
        outContent.reset();
        
        // Unlock another achievement
        tracker.unlockAchievement("CORRECT");
        tracker.displayAchievements();
        
        output = outContent.toString();
        assertTrue(output.contains("FAST"));
        assertTrue(output.contains("CORRECT"));
    }
    
    @Test
    public void testTrackResponseTime() {
        // No times tracked yet
        assertEquals(0, tracker.getAverageResponseTime(), 0.001);
        
        // Add some response times
        tracker.trackResponseTime(2.5);
        tracker.trackResponseTime(3.5);
        
        // Check average
        assertEquals(3.0, tracker.getAverageResponseTime(), 0.001);
        
        // Add another time
        tracker.trackResponseTime(6.0);
        
        // Check updated average
        assertEquals(4.0, tracker.getAverageResponseTime(), 0.001);
    }
    
    @Test
    public void testDisplayAchievementsEmpty() {
        tracker.displayAchievements();
        
        String output = outContent.toString();
        assertTrue(output.contains("Odoogoor ymar ch amjilt gargaagui baina"));
    }
    
    @Test
    public void testDisplayAllAchievements() {
        tracker.unlockAchievement("FAST");
        tracker.unlockAchievement("CORRECT");
        tracker.unlockAchievement("REPEAT");
        tracker.unlockAchievement("CONFIDENT");
        
        tracker.displayAchievements();
        
        String output = outContent.toString();
        assertTrue(output.contains("FAST"));
        assertTrue(output.contains("CORRECT"));
        assertTrue(output.contains("REPEAT"));
        assertTrue(output.contains("CONFIDENT"));
    }
    
    @Test
    public void tearDown() {
        System.setOut(originalOut);
    }
}
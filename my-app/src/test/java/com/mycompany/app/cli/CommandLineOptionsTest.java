package com.mycompany.app.cli;

import static org.junit.Assert.*;
import org.junit.Test;

public class CommandLineOptionsTest {
    
    @Test
    public void testDefaultValues() {
        CommandLineOptions options = new CommandLineOptions(new String[]{});
        assertFalse(options.isHelp());
        assertEquals("random", options.getOrder());
        assertEquals(1, options.getRepetitions());
        assertFalse(options.isInvertCards());
        assertNull(options.getCardsFile());
    }
    
    @Test
    public void testHelpFlag() {
        CommandLineOptions options = new CommandLineOptions(new String[]{"--help"});
        assertTrue(options.isHelp());
    }
    
    @Test
    public void testOrderOption() {
        CommandLineOptions options = new CommandLineOptions(new String[]{"--order", "worst-first"});
        assertEquals("worst-first", options.getOrder());
        
        options = new CommandLineOptions(new String[]{"--order", "recent-mistakes-first"});
        assertEquals("recent-mistakes-first", options.getOrder());
    }
    
    @Test
    public void testInvalidOrderOption() {
        // Invalid order should result in showing help and using default
        CommandLineOptions options = new CommandLineOptions(new String[]{"--order", "invalid-order"});
        assertTrue(options.isHelp());
        assertEquals("random", options.getOrder());
    }
    
    @Test
    public void testRepetitionsOption() {
        CommandLineOptions options = new CommandLineOptions(new String[]{"--repetitions", "3"});
        assertEquals(3, options.getRepetitions());
    }
    
    @Test
    public void testInvalidRepetitionsOption() {
        // Invalid repetitions should result in showing help and using default
        CommandLineOptions options = new CommandLineOptions(new String[]{"--repetitions", "invalid"});
        assertTrue(options.isHelp());
        assertEquals(1, options.getRepetitions());
        
        options = new CommandLineOptions(new String[]{"--repetitions", "-5"});
        assertTrue(options.isHelp());
    }
    
    @Test
    public void testInvertCardsOption() {
        CommandLineOptions options = new CommandLineOptions(new String[]{"--invertCards"});
        assertTrue(options.isInvertCards());
    }
    
    @Test
    public void testCardsFileOption() {
        CommandLineOptions options = new CommandLineOptions(new String[]{"flashcards.txt"});
        assertEquals("flashcards.txt", options.getCardsFile());
    }
    
    @Test
    public void testCombinedOptions() {
        CommandLineOptions options = new CommandLineOptions(new String[]{
            "--order", "worst-first",
            "--repetitions", "2",
            "--invertCards",
            "flashcards.txt"
        });
        
        assertEquals("worst-first", options.getOrder());
        assertEquals(2, options.getRepetitions());
        assertTrue(options.isInvertCards());
        assertEquals("flashcards.txt", options.getCardsFile());
    }
}
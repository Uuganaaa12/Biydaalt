package com.mycompany.app.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CommandLineOptionsTest {

    @Test
    public void testDefaultOptions() {
        CommandLineOptions options = new CommandLineOptions(new String[] {"file.txt"});
        assertFalse(options.isHelp());
        assertEquals("random", options.getOrder());
        assertEquals(1, options.getRepetitions());
        assertFalse(options.isInvertCards());
        assertEquals("file.txt", options.getCardsFile());
    }

    @Test
    public void testHelpOption() {
        CommandLineOptions options = new CommandLineOptions(new String[] {"--help"});
        assertTrue(options.isHelp());
        assertNull(options.getCardsFile());
    }

    @Test
    public void testOrderOption() {
        CommandLineOptions options = new CommandLineOptions(
                new String[] {"--order", "worst-first", "file.txt"});
        assertEquals("worst-first", options.getOrder());
        
        // Test invalid order
        options = new CommandLineOptions(
                new String[] {"--order", "invalid-order", "file.txt"});
        assertTrue(options.isHelp() || "random".equals(options.getOrder()));
    }

    @Test
    public void testRepetitionsOption() {
        CommandLineOptions options = new CommandLineOptions(
                new String[] {"--repetitions", "3", "file.txt"});
        assertEquals(3, options.getRepetitions());

        options = new CommandLineOptions(
                new String[] {"--repetitions", "-1", "file.txt"});
        assertTrue(options.isHelp() || options.getRepetitions() == 1);
        
        options = new CommandLineOptions(
                new String[] {"--repetitions", "not-a-number", "file.txt"});
        assertTrue(options.isHelp() || options.getRepetitions() == 1);
    }

    @Test
    public void testInvertCardsOption() {
        CommandLineOptions options = new CommandLineOptions(
                new String[] {"--invertCards", "file.txt"});
        assertTrue(options.isInvertCards());
    }

    @Test
    public void testMultipleOptions() {
        CommandLineOptions options = new CommandLineOptions(
                new String[] {"--order", "recent-mistakes-first", "--repetitions", "5", 
                              "--invertCards", "file.txt"});
        assertEquals("recent-mistakes-first", options.getOrder());
        assertEquals(5, options.getRepetitions());
        assertTrue(options.isInvertCards());
        assertEquals("file.txt", options.getCardsFile());
    }
}
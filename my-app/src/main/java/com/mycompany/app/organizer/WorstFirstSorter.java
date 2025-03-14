package com.mycompany.app.organizer;

import com.mycompany.app.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorstFirstSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards, Map<Card, Integer> correctAnswers, Map<Card, Integer> totalAttempts) {
        List<Card> organizedCards = new ArrayList<>(cards);
        
        organizedCards.sort((c1, c2) -> {
            int c1Incorrect = totalAttempts.get(c1) - correctAnswers.get(c1);
            int c2Incorrect = totalAttempts.get(c2) - correctAnswers.get(c2);
            return Integer.compare(c2Incorrect, c1Incorrect);
        });
        
        return organizedCards;
    }
}
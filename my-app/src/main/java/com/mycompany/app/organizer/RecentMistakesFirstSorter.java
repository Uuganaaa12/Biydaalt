package com.mycompany.app.organizer;

import com.mycompany.app.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecentMistakesFirstSorter implements CardOrganizer {
    private List<Card> recentMistakes = new ArrayList<>();
    private List<Card> otherCards = new ArrayList<>();
    
    @Override
    public List<Card> organize(List<Card> cards, Map<Card, Integer> correctAnswers, Map<Card, Integer> totalAttempts) {

        List<Card> result = new ArrayList<>();

        for (Card card : recentMistakes) {
            if (cards.contains(card)) {
                result.add(card);
            }
        }

        for (Card card : otherCards) {
            if (cards.contains(card) && !result.contains(card)) {
                result.add(card);
            }
        }

        for (Card card : cards) {
            if (!result.contains(card)) {
                result.add(card);
            }
        }

        recentMistakes.clear();
        otherCards.clear();  
        
        for (Card card : cards) {
            if (totalAttempts.get(card) > 0 && totalAttempts.get(card) > correctAnswers.get(card)) {
                recentMistakes.add(card);
            } else {
                otherCards.add(card);
            }
        }
        
        return result;
    }
}
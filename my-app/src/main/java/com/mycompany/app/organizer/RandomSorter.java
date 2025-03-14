package com.mycompany.app.organizer;

import com.mycompany.app.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RandomSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards, Map<Card, Integer> correctAnswers, Map<Card, Integer> totalAttempts) {
        List<Card> organizedCards = new ArrayList<>(cards);
        Collections.shuffle(organizedCards);
        return organizedCards;
    }
}
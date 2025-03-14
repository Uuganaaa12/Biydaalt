package com.mycompany.app.organizer;

import com.mycompany.app.model.Card;

import java.util.List;
import java.util.Map;

public interface CardOrganizer {

    List<Card> organize(List<Card> cards, Map<Card, Integer> correctAnswers, Map<Card, Integer> totalAttempts);
}
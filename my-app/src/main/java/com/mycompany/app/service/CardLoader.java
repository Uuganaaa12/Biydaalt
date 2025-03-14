package com.mycompany.app.service;

import com.mycompany.app.model.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CardLoader {

    public static List<Card> loadCardsFromFile(String filename) throws IOException {
        List<Card> cards = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (int i = 0; i < lines.size(); i += 2) {
            if (i + 1 < lines.size()) {
                cards.add(new Card(lines.get(i), lines.get(i + 1)));
            }
        }
        return cards;
    }
}
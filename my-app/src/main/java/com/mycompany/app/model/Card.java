package com.mycompany.app.model;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void invert() {
        String temp = question;
        question = answer;
        answer = temp;
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }
}
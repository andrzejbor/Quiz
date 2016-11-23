package com.example.andrzej.quiz;

import java.util.List;

/**
 * Created by Andrzej on 23.11.2016.
 */

public class Question {

    private String content;
    private int difficulty;
    private List<String> answers;
    private int correctAnswer;

    public Question(String content, int difficulty, List<String> answers, int correctAnswer) {
        this.content = content;
        this.difficulty = difficulty;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

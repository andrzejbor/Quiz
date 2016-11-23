package com.example.andrzej.quiz;

import java.util.List;

/**
 * Created by Andrzej on 23.11.2016.
 */

public interface QuestionsDatabase {

    List<Question> getQuestions(int difficulty);
}

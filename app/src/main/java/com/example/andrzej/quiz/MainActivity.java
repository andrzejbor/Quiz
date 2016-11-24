package com.example.andrzej.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.player_name)
    protected EditText mName;
    @BindView(R.id.difficulty)
    protected Spinner mDifficulty;
    private UserPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPrefs = new UserPreferences(this);
        mName.setText(mPrefs.getUsername());
        mDifficulty.setSelection(mPrefs.getLevel());
    }

    @OnClick(R.id.next)
    protected void onNextClick(){
        // Sprawdzanie czy w ogóle wpisano coś w pole imię
        String name = mName.getText().toString();
        if (name.trim().isEmpty()){
            mName.setError("Brak nazwy gracza!");
            return;
        }

        int selectedLevel = mDifficulty.getSelectedItemPosition();
        if (selectedLevel == 0) {
            Toast.makeText(this,"Wybierz poziom trudności!", Toast.LENGTH_SHORT).show();
        }

        // Zapamiętanie nazwy i poziomu trudności
        mPrefs.setUsername(name);
        mPrefs.setLevel(selectedLevel);

        // Losowanie puli pytań

        QuestionsDatabase db = new MemoryQuestionsDatabase();
        List<Question> questions = db.getQuestions(selectedLevel);
        Random random = new Random();
        while(questions.size() > 5){
            //usuwamy losowe pytania aż zostanie nam ich 5
            questions.remove(random.nextInt(questions.size()));
        }
        //przetasowujemy kolekcje pozostałych pytań
        Collections.shuffle(questions);

        // TODO Przekazanie lub zapisanie wylosowanych pytań na potrzeby następnego ekranu

        // Otwarcie nowego ekranu
        Intent questionIntent = new Intent(this, QuestionActivity.class);
        questionIntent.putExtra("questions", new ArrayList<>(questions));
        startActivity(questionIntent);
    }

}

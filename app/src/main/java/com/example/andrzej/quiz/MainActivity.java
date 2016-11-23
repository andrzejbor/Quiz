package com.example.andrzej.quiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        // TODO Losowanie puli pytań
       // TODO Otwarcie nowego ekranu
    }

}

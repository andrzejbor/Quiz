package com.example.andrzej.quiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.player_name)
    protected EditText mName;
    @BindView(R.id.difficulty)
    protected Spinner mDifficulty;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPrefs = getSharedPreferences("user", MODE_PRIVATE);
        mName.setText(mPrefs.getString("username", ""));
    }

    @OnClick(R.id.next)
    protected void onNextClick(){
        // Sprawdzanie czy w ogóle wpisano coś w pole imię
        String name = mName.getText().toString();
        if (name.trim().isEmpty()){
            mName.setError("Brak nazwy gracza!");
            return;
        }
        // TODO Zapamiętanie nazwy i poziomu trudności
        mPrefs.edit().putString("username", name).apply();
        // TODO Losowanie puli pytań
       // TODO Otwarcie nowego ekranu
    }

}

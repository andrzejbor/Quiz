package com.example.andrzej.quiz;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends AppCompatActivity {

    @BindView(R.id.question_text)
    protected TextView mTitle;

    @BindView(R.id.answers)
    protected RadioGroup mAnswers;

    @BindViews({R.id.answer_a, R.id.answer_b, R.id.answer_c})
    protected List<RadioButton> mAnswersButtons;

    @BindView(R.id.btn_next)
    protected Button mNextButton;

    private int mCurrentQuestion = 0;
    private List<Question> mQuestions;
    private int[] mAnswersArray;
    private boolean mFirstBackClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        Log.d("QuestionActivity", "onCreate");

        mQuestions = (List<Question>) getIntent().getSerializableExtra("questions");
        mAnswersArray = new int[mQuestions.size()];
        refreshQuestionView();
    }


    //zapisywanie odpowiedzi i pozycji pytań (stan Quizu) potzrebne przy obrocie
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAnswersArray[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        outState.putInt("position", mCurrentQuestion);
        outState.putIntArray("answers", mAnswersArray);
    }

    //wczytywanie stanu Quizu po obrocie ekranu
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentQuestion = savedInstanceState.getInt("position");
        mAnswersArray = savedInstanceState.getIntArray("answers");
        refreshQuestionView();
    }

    @Override
    public void onBackPressed() {
        onBackTapped();
    }

    private void onBackTapped() {
        if (!mFirstBackClicked) {
            mFirstBackClicked = true;
            Toast.makeText(this, "Kliknij ponownie by zakończyć Quiz", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mFirstBackClicked = false;
                }
            }, 1000);
        } else { // Drugie kliknięcie (w ciągu 1 s)
            finish();
        }
    }

    private void refreshQuestionView() {
        mAnswers.clearCheck();
        Question q1 = mQuestions.get(mCurrentQuestion);
        mTitle.setText(q1.getContent());
        for (int i = 0; i < 3; i++) {
            mAnswersButtons.get(i).setText(q1.getAnswers().get(i));
        }
        //Sprawdza czy dla danego pytania zostałą udzielona odpowiedź, jeśli tak to zaznacza wybraną
        if (mAnswersArray[mCurrentQuestion] > 0) {
            mAnswers.check(mAnswersArray[mCurrentQuestion]);
        }

        mNextButton.setText(mCurrentQuestion < mQuestions.size() -1 ? "Dalej" : "Zakończ");
    }

    @OnClick(R.id.btn_back)
    protected void onBackClick() {
        if (mCurrentQuestion == 0) {
            onBackTapped();
            return;
        }
        //Zapisanie udzielonej odpowiedzi na aktualne pytanie
        mAnswersArray[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        mCurrentQuestion--;
        refreshQuestionView();
    }

    @OnClick(R.id.btn_next)
    protected void onNextClick() {
        mAnswersArray[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        if (mAnswersArray[mCurrentQuestion] == -1) {
            Toast.makeText(this, "Musisz udzielić odpowiedzi", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mCurrentQuestion == mQuestions.size() - 1) {
            int correctAnswers = countCorrectAnswers();
            int totalAnswers = mAnswersArray.length;
            displayResults(correctAnswers, totalAnswers);
            return;
        }
        mCurrentQuestion++;
        refreshQuestionView();
    }

    private void displayResults(int correctAnswers, int totalAnswers) {
        QuizResultsDialog.newInstance(correctAnswers, totalAnswers).show(getSupportFragmentManager(), null);
    }


    private int countCorrectAnswers() {
        int sum = 0;

        for (int i = 0; i < mQuestions.size(); i++) {
            Question question = mQuestions.get(i);
            int userAnswerId = mAnswersArray[i];
            int correctAnswerId = mAnswersButtons.get(question.getCorrectAnswer()).getId();
            if (userAnswerId == correctAnswerId) {
                sum++;
            }
        }

        return sum;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("QuestionActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("QuestionActivity", "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("QuestionActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("QuestionActivity", "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("QuestionActivity", "onDestroy");
    }
}

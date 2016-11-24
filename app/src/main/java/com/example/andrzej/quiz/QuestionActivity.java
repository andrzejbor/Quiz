package com.example.andrzej.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private int mCurrentQuestion = 0;
    private List<Question> mQuestions;
    private int[] mAnswersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        mQuestions = (List<Question>) getIntent().getSerializableExtra("questions");
        mAnswersArray = new int[mQuestions.size()];
        refreshQuestionView();
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
    }

    @OnClick(R.id.btn_back)
    protected void onBackClick() {
        if (mCurrentQuestion == 0) {
            finish();
            return;
        }
        //Zapisanie udzielonej odpowiedzi na aktualne pytanie
        mAnswersArray[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        mCurrentQuestion--;
        refreshQuestionView();
    }

    @OnClick(R.id.btn_next)
    protected void onNextClick() {
        if (mCurrentQuestion == mQuestions.size() - 1) {
            return;
        }
        mAnswersArray[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        if (mAnswersArray[mCurrentQuestion] == -1) {
            Toast.makeText(this, "Musisz udzielić odpowiedzi", Toast.LENGTH_SHORT).show();
            return;
        }
        mCurrentQuestion++;
        refreshQuestionView();
    }
}

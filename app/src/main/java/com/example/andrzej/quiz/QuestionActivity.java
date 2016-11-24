package com.example.andrzej.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        mQuestions = (List<Question>) getIntent().getSerializableExtra("questions");
        refreshQuestionView();
    }

    private void refreshQuestionView() {
        Question q1 = mQuestions.get(mCurrentQuestion);
        mTitle.setText(q1.getContent());
        for (int i = 0; i < 3; i++) {
            mAnswersButtons.get(i).setText(q1.getAnswers().get(i));
        }
    }

    @OnClick(R.id.btn_back)
    protected void onBackClick() {
        if (mCurrentQuestion == 0) {
            return;
        }
        mCurrentQuestion--;
        refreshQuestionView();
    }

    @OnClick(R.id.btn_next)
    protected void onNextClick() {
        if (mCurrentQuestion == mQuestions.size() - 1) {
            return;
        }
        mCurrentQuestion++;
        refreshQuestionView();
    }
}

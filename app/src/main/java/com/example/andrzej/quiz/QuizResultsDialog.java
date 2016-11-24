package com.example.andrzej.quiz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Andrzej on 24.11.2016.
 */

public class QuizResultsDialog extends DialogFragment {

    public static QuizResultsDialog newInstance(int correctAnswers, int totalAnswers){
        QuizResultsDialog dialog = new QuizResultsDialog();
        Bundle args = new Bundle();

        args.putInt("correct", correctAnswers);
        args.putInt("total", totalAnswers);
        dialog.setArguments(args);

        return dialog;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int correctAnswers = getArguments().getInt("correct");
        int totalAnswers = getArguments().getInt("total");
        setCancelable(false);
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Wynik Quizu")
                .setMessage("Odpowiedziałeś poprawnie na " + correctAnswers + " pytań z " + totalAnswers)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .create();
        return dialog;
    }
}

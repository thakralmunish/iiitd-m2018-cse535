package com.example.thakr.quizapp_test1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionDisplay extends Fragment {
    public View view;
    public TextView textView;
    public Button ClearButton;
    public Button SaveButton;

    public static boolean DialogOutput;

    public static String UserAnswer = "-";

    public QuestionDisplay() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question_display, container, false);

        final int QuestionNumber = getArguments().getInt(QuizQuestionContract.TableEntry.QUESTION_NUMBER);

        textView = view.findViewById(R.id.Question);
        textView.setText(getArguments().getString(QuizQuestionContract.TableEntry.QUESTION));

        final RadioButton True = view.findViewById(R.id.True);
        final RadioButton False = view.findViewById(R.id.False);

        ClearButton = view.findViewById(R.id.ClearButton);
        SaveButton = view.findViewById(R.id.SaveButton);

        UserAnswer = getArguments().getString(QuizQuestionContract.TableEntry.USER_ANSWER);

        if (UserAnswer.equals("True")) {
            True.setChecked(true);
            False.setChecked(false);
        }
        else if (UserAnswer.equals("False")) {
            False.setChecked(true);
            True.setChecked(false);
        }
        else {
            True.setChecked(false);
            False.setChecked(false);
        }

        RadioGroup radioGroup = view.findViewById(R.id.Options);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (UserAnswer.equals("True") && False.isChecked()) {
                    MainActivity.Back = false;
                }
                else if (UserAnswer.equals("False") && True.isChecked()) {
                    MainActivity.Back = false;
                }
                else {
                    MainActivity.Back = true;
                }
            }
        });


        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (True.isChecked()) {
                    True.setChecked(false);
                }
                else if (False.isChecked()) {
                    False.setChecked(false);
                }
                UserAnswer = "-";
                QuestionsListDisplay.QQ.get(QuestionNumber - 1).setUserAnswered(UserAnswer);
                QuizQuestionDBHelper QQDBH = new QuizQuestionDBHelper(getContext());
                SQLiteDatabase db = QQDBH.getWritableDatabase();
                QQDBH.UpdateInfoInDatabase(QuestionsListDisplay.QQ.get(QuestionNumber - 1), db);
                db.close();
                Toast.makeText(getContext(), "CLEARED", Toast.LENGTH_SHORT).show();
            }
        });


        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (True.isChecked() == false && False.isChecked() == false) {
                    Toast.makeText(getContext(), "PLEASE SELECT AN OPTION", Toast.LENGTH_LONG).show();
                }
                else {
                    if (True.isChecked()) {
                        UserAnswer = "True";
                    }
                    else {
                        UserAnswer = "False";
                    }
                    QuestionsListDisplay.QQ.get(QuestionNumber - 1).setUserAnswered(UserAnswer);
                    QuizQuestionDBHelper QQDBH = new QuizQuestionDBHelper(getContext());
                    SQLiteDatabase db = QQDBH.getWritableDatabase();
                    QQDBH.UpdateInfoInDatabase(QuestionsListDisplay.QQ.get(QuestionNumber - 1), db);
                    db.close();
                    Toast.makeText(getContext(), "ANSWER SAVED", Toast.LENGTH_SHORT).show();
                    MainActivity.fragmentManager.popBackStack();
                }


            }
        });


        return view;
    }

}

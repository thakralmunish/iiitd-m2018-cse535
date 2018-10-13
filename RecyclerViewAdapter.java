package com.example.thakr.quizapp_test1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<QuizQuestion> L = new ArrayList<QuizQuestion>();
    public static int I;

    public RecyclerViewAdapter(ArrayList<QuizQuestion> arrayList) {
        L = arrayList;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout Row;
        TextView Number, Question, Answer;
        public static View.OnClickListener itemView;


        public RecyclerViewHolder(View view) {
            super(view);
            Row = view.findViewById(R.id.Row);
            Number = view.findViewById(R.id.Number);
            Question = view.findViewById(R.id.Question);
            Answer = view.findViewById(R.id.Answer);
            view.setOnClickListener(itemView);
        }

        @Override
        public void onClick(View v) {
            Log.d("ABC", "1");
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_row, viewGroup, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder viewHolder, int i) {
        I = i;
        final QuizQuestion Q = L.get(i);
        viewHolder.Number.setText(Q.getQuestionNumber());
        viewHolder.Question.setText(Q.getQuestion());
        viewHolder.Answer.setText(Q.getUserAnswered());
        if (viewHolder.Answer.getText().toString().equals("True")) {
            viewHolder.Answer.setTextColor(Color.parseColor("green"));
        }
        else if (viewHolder.Answer.getText().toString().equals("False")) {
            viewHolder.Answer.setTextColor(Color.parseColor("red"));
        }
        else {
            viewHolder.Answer.setText("-");
            viewHolder.Answer.setTextColor(Color.parseColor("gray"));
        }

        viewHolder.Row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ABC", "POS " + Integer.toString(viewHolder.getLayoutPosition()));
                FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt(QuizQuestionContract.TableEntry.QUESTION_NUMBER, Q.getNumber());
                bundle.putString(QuizQuestionContract.TableEntry.QUESTION, Q.getQuestion());
                bundle.putString(QuizQuestionContract.TableEntry.USER_ANSWER, Q.getUserAnswered());
                QuestionDisplay QD = new QuestionDisplay();
                QD.setArguments(bundle);
                fragmentTransaction.replace(R.id.MainFrame, QD);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return L.size();
    }

}

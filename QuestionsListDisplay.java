package com.example.thakr.quizapp_test1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class QuestionsListDisplay extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecyclerView.Adapter rv_adapter;
    RecyclerView.LayoutManager rv_layoutManager;
    QuizQuestionDBHelper QQDBH;

    public static ArrayList<QuizQuestion> QQ = new ArrayList<QuizQuestion>();

    public QuestionsListDisplay() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QQDBH = new QuizQuestionDBHelper(getContext());
        SQLiteDatabase db = QQDBH.getWritableDatabase();
        QuizQuestion Q1 = new QuizQuestion(1, "IS 1 = 1?", new String[]{"True", "False"}, "True");
        QuizQuestion Q2 = new QuizQuestion(2, "IS 2 = 2?", new String[]{"True", "False"}, "True");
        QuizQuestion Q3 = new QuizQuestion(3, "IS 3 = 3?", new String[]{"True", "False"}, "True");
        QuizQuestion Q4 = new QuizQuestion(4, "IS 4 = 4?", new String[]{"True", "False"}, "True");
        QuizQuestion Q5 = new QuizQuestion(5, "IS 5 = 5?", new String[]{"True", "False"}, "True");
        QuizQuestion Q6 = new QuizQuestion(6, "IS 6 = 6?", new String[]{"True", "False"}, "True");
        QuizQuestion Q7 = new QuizQuestion(7, "IS 7 = 7?", new String[]{"True", "False"}, "True");
        QuizQuestion Q8 = new QuizQuestion(8, "IS 8 = 8?", new String[]{"True", "False"}, "True");
        QuizQuestion Q9 = new QuizQuestion(9, "IS 9 = 9?", new String[]{"True", "False"}, "True");
        QuizQuestion Q10 = new QuizQuestion(10, "IS 10 = 10?", new String[]{"True", "False"}, "True");
        QuizQuestion Q11 = new QuizQuestion(11, "IS 11 = 11?", new String[]{"True", "False"}, "True");
        QuizQuestion Q12 = new QuizQuestion(12, "IS 12 = 12?", new String[]{"True", "False"}, "True");
        QuizQuestion Q13 = new QuizQuestion(13, "IS 13 = 13?", new String[]{"True", "False"}, "True");
        QuizQuestion Q14 = new QuizQuestion(14, "IS 14 = 14?", new String[]{"True", "False"}, "True");
        QuizQuestion Q15 = new QuizQuestion(15, "IS 15 = 15?", new String[]{"True", "False"}, "True");
        QuizQuestion Q16 = new QuizQuestion(16, "IS 16 = 16?", new String[]{"True", "False"}, "True");
        QuizQuestion Q17 = new QuizQuestion(17, "IS 17 = 17?", new String[]{"True", "False"}, "True");
        QuizQuestion Q18 = new QuizQuestion(18, "IS 18 = 18?", new String[]{"True", "False"}, "True");
        QuizQuestion Q19 = new QuizQuestion(19, "IS 19 = 19?", new String[]{"True", "False"}, "True");
        QuizQuestion Q20 = new QuizQuestion(20, "IS 20 = 20?", new String[]{"True", "False"}, "True");
        QuizQuestion Q21 = new QuizQuestion(21, "IS 21 = 21?", new String[]{"True", "False"}, "True");
        QuizQuestion Q22 = new QuizQuestion(22, "IS 22 = 22?", new String[]{"True", "False"}, "True");
        QuizQuestion Q23 = new QuizQuestion(23, "IS 23 = 23?", new String[]{"True", "False"}, "True");
        QuizQuestion Q24 = new QuizQuestion(24, "IS 24 = 24?", new String[]{"True", "False"}, "True");
        QuizQuestion Q25 = new QuizQuestion(25, "IS 25 = 25?", new String[]{"True", "False"}, "True");
        QuizQuestion Q26 = new QuizQuestion(26, "IS 26 = 26?", new String[]{"True", "False"}, "True");
        QuizQuestion Q27 = new QuizQuestion(27, "IS 27 = 27?", new String[]{"True", "False"}, "True");
        QuizQuestion Q28 = new QuizQuestion(28, "IS 28 = 28?", new String[]{"True", "False"}, "True");
        QuizQuestion Q29 = new QuizQuestion(29, "IS 29 = 29?", new String[]{"True", "False"}, "True");
        QuizQuestion Q30 = new QuizQuestion(30, "IS 30 = 30?", new String[]{"True", "False"}, "True");
        QuizQuestion Q31 = new QuizQuestion(31, "IS 31 = 31?", new String[]{"True", "False"}, "True");
        QuizQuestion Q32 = new QuizQuestion(32, "IS 32 = 32?", new String[]{"True", "False"}, "True");
        QuizQuestion Q33 = new QuizQuestion(33, "IS 33 = 33?", new String[]{"True", "False"}, "True");
        QuizQuestion Q34 = new QuizQuestion(34, "IS 34 = 34?", new String[]{"True", "False"}, "True");
        QuizQuestion Q35 = new QuizQuestion(35, "IS 35 = 35?", new String[]{"True", "False"}, "True");
        QuizQuestion Q36 = new QuizQuestion(36, "IS 36 = 36?", new String[]{"True", "False"}, "True");
        QuizQuestion Q37 = new QuizQuestion(37, "IS 37 = 37?", new String[]{"True", "False"}, "True");
        QuizQuestion Q38 = new QuizQuestion(38, "IS 38 = 38?", new String[]{"True", "False"}, "True");
        QuizQuestion Q39 = new QuizQuestion(39, "IS 39 = 39?", new String[]{"True", "False"}, "True");
        QuizQuestion Q40 = new QuizQuestion(40, "IS 40 = 40?", new String[]{"True", "False"}, "True");
        QuizQuestion Q41 = new QuizQuestion(41, "IS 41 = 41?", new String[]{"True", "False"}, "True");
        QuizQuestion Q42 = new QuizQuestion(42, "IS 42 = 42?", new String[]{"True", "False"}, "True");
        QuizQuestion Q43 = new QuizQuestion(43, "IS 43 = 43?", new String[]{"True", "False"}, "True");
        QuizQuestion Q44 = new QuizQuestion(44, "IS 44 = 44?", new String[]{"True", "False"}, "True");
        QuizQuestion Q45 = new QuizQuestion(45, "IS 45 = 45?", new String[]{"True", "False"}, "True");
        QuizQuestion Q46 = new QuizQuestion(46, "IS 46 = 46?", new String[]{"True", "False"}, "True");
        QuizQuestion Q47 = new QuizQuestion(47, "IS 47 = 47?", new String[]{"True", "False"}, "True");
        QuizQuestion Q48 = new QuizQuestion(48, "IS 48 = 48?", new String[]{"True", "False"}, "True");
        QuizQuestion Q49 = new QuizQuestion(49, "IS 49 = 49?", new String[]{"True", "False"}, "True");
        QuizQuestion Q50 = new QuizQuestion(50, "IS 50 = 50?", new String[]{"True", "False"}, "True");


        QQ.add(Q1);
        QQ.add(Q2);
        QQ.add(Q3);
        QQ.add(Q4);
        QQ.add(Q5);
        QQ.add(Q6);
        QQ.add(Q7);
        QQ.add(Q8);
        QQ.add(Q9);
        QQ.add(Q10);
        QQ.add(Q11);
        QQ.add(Q12);
        QQ.add(Q13);
        QQ.add(Q14);
        QQ.add(Q15);
        QQ.add(Q16);
        QQ.add(Q17);
        QQ.add(Q18);
        QQ.add(Q19);
        QQ.add(Q20);
        QQ.add(Q21);
        QQ.add(Q22);
        QQ.add(Q23);
        QQ.add(Q24);
        QQ.add(Q25);
        QQ.add(Q26);
        QQ.add(Q27);
        QQ.add(Q28);
        QQ.add(Q29);
        QQ.add(Q30);
        QQ.add(Q31);
        QQ.add(Q32);
        QQ.add(Q33);
        QQ.add(Q34);
        QQ.add(Q35);
        QQ.add(Q36);
        QQ.add(Q37);
        QQ.add(Q38);
        QQ.add(Q39);
        QQ.add(Q40);
        QQ.add(Q41);
        QQ.add(Q42);
        QQ.add(Q43);
        QQ.add(Q44);
        QQ.add(Q45);
        QQ.add(Q46);
        QQ.add(Q47);
        QQ.add(Q48);
        QQ.add(Q49);
        QQ.add(Q50);


        QQDBH.AddInfoToDatabase(Q1, db);
        QQDBH.AddInfoToDatabase(Q2, db);
        QQDBH.AddInfoToDatabase(Q3, db);
        QQDBH.AddInfoToDatabase(Q4, db);
        QQDBH.AddInfoToDatabase(Q5, db);
        QQDBH.AddInfoToDatabase(Q6, db);
        QQDBH.AddInfoToDatabase(Q7, db);
        QQDBH.AddInfoToDatabase(Q8, db);
        QQDBH.AddInfoToDatabase(Q9, db);
        QQDBH.AddInfoToDatabase(Q10, db);
        QQDBH.AddInfoToDatabase(Q11, db);
        QQDBH.AddInfoToDatabase(Q12, db);
        QQDBH.AddInfoToDatabase(Q13, db);
        QQDBH.AddInfoToDatabase(Q14, db);
        QQDBH.AddInfoToDatabase(Q15, db);
        QQDBH.AddInfoToDatabase(Q16, db);
        QQDBH.AddInfoToDatabase(Q17, db);
        QQDBH.AddInfoToDatabase(Q18, db);
        QQDBH.AddInfoToDatabase(Q19, db);
        QQDBH.AddInfoToDatabase(Q20, db);
        QQDBH.AddInfoToDatabase(Q21, db);
        QQDBH.AddInfoToDatabase(Q22, db);
        QQDBH.AddInfoToDatabase(Q23, db);
        QQDBH.AddInfoToDatabase(Q24, db);
        QQDBH.AddInfoToDatabase(Q25, db);
        QQDBH.AddInfoToDatabase(Q26, db);
        QQDBH.AddInfoToDatabase(Q27, db);
        QQDBH.AddInfoToDatabase(Q28, db);
        QQDBH.AddInfoToDatabase(Q29, db);
        QQDBH.AddInfoToDatabase(Q30, db);
        QQDBH.AddInfoToDatabase(Q31, db);
        QQDBH.AddInfoToDatabase(Q32, db);
        QQDBH.AddInfoToDatabase(Q33, db);
        QQDBH.AddInfoToDatabase(Q34, db);
        QQDBH.AddInfoToDatabase(Q35, db);
        QQDBH.AddInfoToDatabase(Q36, db);
        QQDBH.AddInfoToDatabase(Q37, db);
        QQDBH.AddInfoToDatabase(Q38, db);
        QQDBH.AddInfoToDatabase(Q39, db);
        QQDBH.AddInfoToDatabase(Q40, db);
        QQDBH.AddInfoToDatabase(Q41, db);
        QQDBH.AddInfoToDatabase(Q42, db);
        QQDBH.AddInfoToDatabase(Q43, db);
        QQDBH.AddInfoToDatabase(Q44, db);
        QQDBH.AddInfoToDatabase(Q45, db);
        QQDBH.AddInfoToDatabase(Q46, db);
        QQDBH.AddInfoToDatabase(Q47, db);
        QQDBH.AddInfoToDatabase(Q48, db);
        QQDBH.AddInfoToDatabase(Q49, db);
        QQDBH.AddInfoToDatabase(Q50, db);

        db.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_questions_list_display, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        rv_layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(rv_layoutManager);
        //recyclerView.setHasFixedSize(true);
        rv_adapter = new RecyclerViewAdapter(QQ);
        recyclerView.setAdapter(rv_adapter);

        return view;
    }

}

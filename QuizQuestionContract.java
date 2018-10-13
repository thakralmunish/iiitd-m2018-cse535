package com.example.thakr.quizapp_test1;

import android.provider.BaseColumns;

public class QuizQuestionContract {

    private QuizQuestionContract() {

    }

    public static class TableEntry implements BaseColumns {
        public static String TABLE_NAME = "LIST_OF_QUESTIONS";
        public static String QUESTION_NUMBER = "QUESTION_NUMBER";
        public static String QUESTION = "QUESTION";
        public static String OPTIONS = "OPTIONS";
        public static String QUESTION_ANSWER = "QUESTION_ANSWER";
        public static String USER_ANSWER = "USER_ANSWER";
    }

}

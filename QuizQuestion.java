package com.example.thakr.quizapp_test1;

public class QuizQuestion {
    public int Number;
    public String Question;
    public String[] Options;
    public String QuestionAnswer;
    public String UserAnswered;

    public QuizQuestion() {

    }

    public QuizQuestion(int N, String Q, String[] O, String A) {
        Number = N;
        Question = Q;
        Options = O;
        QuestionAnswer = A;
    }

    public int getNumber() {
        return Number;
    }

    public String getQuestionNumber() {
        return Integer.toString(Number);
    }

    public void setNumber(int number) {
        Number = number;
    }

    public void setNumber(String number) {
        Number = Integer.parseInt(number);
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String[] getOptions() {
        return Options;
    }

    public void setOptions(String[] options) {
        Options = options;
    }

    public String getQuestionAnswer() {
        return QuestionAnswer;
    }

    public void setQuestionAnswer(String answer) {
        QuestionAnswer = answer;
    }

    public String getUserAnswered() {
        if (UserAnswered == null) {
            return "";
        }
        else {
            return UserAnswered;
        }
    }

    public void setUserAnswered(String answer) {
        UserAnswered = answer;
    }

}

package com.example.thakr.courseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class EnterDetails_A1_2016167 extends AppCompatActivity {

    EditText NameInput, RollNoInput, BranchInput, CoursesInput1, CoursesInput2, CoursesInput3, CoursesInput4;
    Button ClearButton, SubmitButton;

    public static String NameToSend = "";
    public static String RollNoToSend = "";
    public static String BranchToSend = "";
    public static String CourseToSend1 = "";
    public static String CourseToSend2 = "";
    public static String CourseToSend3 = "";
    public static String CourseToSend4 = "";

    public static String PreviousState = "";
    public static String PresentState = "";

    public static String ToastText = "";

    public static String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreviousState = PresentState;
        PresentState = "Created";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details__a1_2016167);

        NameInput = (EditText) findViewById(R.id.NameInput);
        RollNoInput = (EditText) findViewById(R.id.RollNoInput);
        BranchInput = (EditText) findViewById(R.id.BranchInput);
        CoursesInput1 = (EditText) findViewById(R.id.CoursesInput1);
        CoursesInput2 = (EditText) findViewById(R.id.CoursesInput2);
        CoursesInput3 = (EditText) findViewById(R.id.CoursesInput3);
        CoursesInput4 = (EditText) findViewById(R.id.CoursesInput4);

        ClearButton = (Button) findViewById(R.id.ClearButton);
        SubmitButton = (Button) findViewById(R.id.SubmitButton);

        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameInput.setText("");
                RollNoInput.setText("");
                BranchInput.setText("");
                CoursesInput1.setText("");
                CoursesInput2.setText("");
                CoursesInput3.setText("");
                CoursesInput4.setText("");
            }
        });

        if (PreviousState.equals("")) {
            ToastText = "State of Activity 'Enter Details' changed to " + PresentState;
        }
        else {
            ToastText = "State of Activity 'Enter Details' changed from " + PreviousState + " to " + PresentState;
        }

        Log.i(TAG, ToastText);
        Toast.makeText(getApplicationContext(),ToastText,Toast.LENGTH_LONG).show();
    }

    protected void onStart() {
        PreviousState = PresentState;
        PresentState = "Started";

        if (PreviousState.equals("")) {
            ToastText = "State of Activity 'Enter Details' changed to " + PresentState;
        }
        else {
            ToastText = "State of Activity 'Enter Details' changed from " + PreviousState + " to " + PresentState;
        }

        Log.i(TAG, ToastText);
        Toast.makeText(getApplicationContext(),ToastText,Toast.LENGTH_LONG).show();

        super.onStart();
    }

    protected void onResume() {
        PreviousState = PresentState;
        PresentState = "Resumed";

        if (PreviousState.equals("")) {
            ToastText = "State of Activity 'Enter Details' changed to " + PresentState;
        }
        else {
            ToastText = "State of Activity 'Enter Details' changed from " + PreviousState + " to " + PresentState;
        }

        Log.i(TAG, ToastText);
        Toast.makeText(getApplicationContext(),ToastText,Toast.LENGTH_LONG).show();

        super.onResume();
    }


    protected void onStop() {
        PreviousState = PresentState;
        PresentState = "Stopped";

        if (PreviousState.equals("")) {
            ToastText = "State of Activity 'Enter Details' changed to " + PresentState;
        }
        else {
            ToastText = "State of Activity 'Enter Details' changed from " + PreviousState + " to " + PresentState;
        }

        Log.i(TAG, ToastText);
        Toast.makeText(getApplicationContext(),ToastText,Toast.LENGTH_LONG).show();
        super.onStop();
    }

    protected void onPause() {
        PreviousState = PresentState;
        PresentState = "Paused";

        if (PreviousState.equals("")) {
            ToastText = "State of Activity 'Enter Details' changed to " + PresentState;
        }
        else {
            ToastText = "State of Activity 'Enter Details' changed from " + PreviousState + " to " + PresentState;
        }

        Log.i(TAG, ToastText);
        Toast.makeText(getApplicationContext(),ToastText,Toast.LENGTH_LONG).show();

        super.onPause();
    }

    protected void onDestroy() {
        PreviousState = PresentState;
        PresentState = "Destroyed";

        if (PreviousState.equals("")) {
            ToastText = "State of Activity 'Enter Details' changed to " + PresentState;
        }
        else {
            ToastText = "State of Activity 'Enter Details' changed from " + PreviousState + " to " + PresentState;
        }

        Log.i(TAG, ToastText);
        Toast.makeText(getApplicationContext(),ToastText,Toast.LENGTH_LONG).show();

        super.onDestroy();
    }

    public void SubmitDetails (View V) {
        Intent SubmissionIntent = new Intent(this, DisplayDetails_A1_2016167.class);

        NameToSend = NameInput.getText().toString();
        RollNoToSend = RollNoInput.getText().toString();
        BranchToSend = BranchInput.getText().toString();
        CourseToSend1 = CoursesInput1.getText().toString();
        CourseToSend2 = CoursesInput2.getText().toString();
        CourseToSend3 = CoursesInput3.getText().toString();
        CourseToSend4 = CoursesInput4.getText().toString();

        SubmissionIntent.putExtra(NameToSend, NameToSend);
        SubmissionIntent.putExtra(RollNoToSend,RollNoToSend);
        SubmissionIntent.putExtra(BranchToSend,BranchToSend);
        SubmissionIntent.putExtra(CourseToSend1,CourseToSend1);
        SubmissionIntent.putExtra(CourseToSend2,CourseToSend2);
        SubmissionIntent.putExtra(CourseToSend3,CourseToSend3);
        SubmissionIntent.putExtra(CourseToSend4,CourseToSend4);

        startActivity(SubmissionIntent);
    }
}

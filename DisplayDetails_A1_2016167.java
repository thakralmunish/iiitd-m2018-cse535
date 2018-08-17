package com.example.thakr.courseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayDetails_A1_2016167 extends AppCompatActivity {

    TextView NameDisplay, RollNoDisplay, BranchDisplay, CoursesDisplay1, CoursesDisplay2, CoursesDisplay3, CoursesDisplay4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details__a1_2016167);

        Intent I1 = getIntent();
        String NameReceived = I1.getStringExtra(EnterDetails_A1_2016167.NameToSend);
        if (NameReceived.equals("")) {
            NameReceived = "<NAME NOT ENTERED>";
        }
        String RollNoReceived = I1.getStringExtra(EnterDetails_A1_2016167.RollNoToSend);
        if (RollNoReceived.equals("")) {
            RollNoReceived = "<ROLL NO. NOT ENTERED>";
        }
        String BranchReceived = I1.getStringExtra(EnterDetails_A1_2016167.BranchToSend);
        if (BranchReceived.equals("")) {
            BranchReceived = "<BRANCH NOT ENTERED>";
        }
        String CoursesReceived1 = I1.getStringExtra(EnterDetails_A1_2016167.CourseToSend1);
        if (CoursesReceived1.equals("")) {
            CoursesReceived1 = "<COURSE DETAIL NOT ENTERED>";
        }
        String CoursesReceived2 = I1.getStringExtra(EnterDetails_A1_2016167.CourseToSend2);
        if (CoursesReceived2.equals("")) {
            CoursesReceived2 = "<COURSE DETAIL NOT ENTERED>";
        }
        String CoursesReceived3 = I1.getStringExtra(EnterDetails_A1_2016167.CourseToSend3);
        if (CoursesReceived3.equals("")) {
            CoursesReceived3 = "<COURSE DETAIL NOT ENTERED>";
        }
        String CoursesReceived4 = I1.getStringExtra(EnterDetails_A1_2016167.CourseToSend4);
        if (CoursesReceived4.equals("")) {
            CoursesReceived4 = "<COURSE DETAIL NOT ENTERED>";
        }

        NameDisplay = (TextView) findViewById(R.id.NameDisplay);
        RollNoDisplay = (TextView) findViewById(R.id.RollNoDisplay);
        BranchDisplay = (TextView) findViewById(R.id.BranchDisplay);
        CoursesDisplay1 = (TextView) findViewById(R.id.CoursesDisplay1);
        CoursesDisplay2 = (TextView) findViewById(R.id.CoursesDisplay2);
        CoursesDisplay3 = (TextView) findViewById(R.id.CoursesDisplay3);
        CoursesDisplay4 = (TextView) findViewById(R.id.CoursesDisplay4);

        NameDisplay.setText(NameReceived);
        RollNoDisplay.setText(RollNoReceived);
        BranchDisplay.setText(BranchReceived);
        CoursesDisplay1.setText(CoursesReceived1);
        CoursesDisplay2.setText(CoursesReceived2);
        CoursesDisplay3.setText(CoursesReceived3);
        CoursesDisplay4.setText(CoursesReceived4);
    }
}

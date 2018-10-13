package com.example.thakr.quizapp_test1;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Button SubmitButton;

    public static String FileName = "QUESTIONS_DB.csv";
    public static String DirectoryName = "QUESTIONS_DIRECTORY";

    public static FragmentManager fragmentManager;
    public static boolean Back = true;

    private int PERMISSION_REQUEST_1 = 1;
    private int PERMISSION_REQUEST_2 = 1;
    private int PERMISSION_REQUEST_3 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_2);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_3);
        }



        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.MainFrame, new QuestionsListDisplay()).commit();

        SubmitButton = (Button) findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File root = new File(Environment.getExternalStorageDirectory(), DirectoryName);
                if (!root.exists()) {
                    root.mkdirs();
                }
                File file = new File(root,FileName);

                FileOutputStream outputStream;

                try {
                    outputStream = new FileOutputStream(file, false);
                    outputStream.write((QuizQuestionContract.TableEntry.QUESTION_NUMBER + "," +
                                        QuizQuestionContract.TableEntry.QUESTION + "," +
                                        QuizQuestionContract.TableEntry.QUESTION_ANSWER + "," +
                                        QuizQuestionContract.TableEntry.USER_ANSWER + "\n").getBytes());
                    QuizQuestion Q = new QuizQuestion();
                    for (int i = 0; i < QuestionsListDisplay.QQ.size(); i++) {
                        Q = QuestionsListDisplay.QQ.get(i);
                        outputStream.write((Q.getQuestionNumber() + "," +
                                            Q.getQuestion() + "," +
                                            Q.getQuestionAnswer() + "," +
                                            Q.getUserAnswered() + "\n").getBytes());
                    }
                    outputStream.close();
                    Log.d("ABC", "0");
                    //Toast.makeText(getApplicationContext(), "EXPORTED TO CSV FILE", Toast.LENGTH_SHORT).show();
                    Log.d("ABC", "1");
                    FileUploader fileUploader = new FileUploader(MainActivity.this);
                    Log.d("ABC", "2");

                    ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo ActiveNetwork = CM.getActiveNetworkInfo();
                    boolean IsConnected = (ActiveNetwork != null) && (ActiveNetwork.isConnected());
                    if (IsConnected) {
                        fileUploader.execute(new String[]{FileName});
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Not Connected to the Internet", Toast.LENGTH_SHORT).show();
                    }

                    Log.d("ABC", "3");
                    //Toast.makeText(getApplicationContext(), "EXPORTED TO CSV FILE", Toast.LENGTH_SHORT).show();
                    Log.d("ABC", "4");
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "FAILED EXPORT TO CSV FILE", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (Back) {
            super.onBackPressed();
        }
        else {
            Toast.makeText(getApplicationContext(), "PLEASE SAVE ANSWER", Toast.LENGTH_SHORT).show();
        }

    }
}

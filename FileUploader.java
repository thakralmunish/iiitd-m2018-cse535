package com.example.thakr.quizapp_test1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUploader extends AsyncTask <String, Integer, String> {
    String ServerUploaderFile = "http://192.168.0.8/UploadToServer.php";
    int BytesAvailable;
    int MaxBufferSize = 1048576;

    String Uploaded = "Uploaded";
    String Failed = "Failed";




    public ProgressDialog progressDialog;

    public FileUploader(Activity A) {
        progressDialog = new ProgressDialog(A);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("UPLOADING THE CSV FILE");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        String FileName = strings[0];
        File root = new File(Environment.getExternalStorageDirectory(), MainActivity.DirectoryName);
        File file = new File(root, strings[0]);

        if(file.exists() == false) {
            // FILE DOES NOT EXIST
            return "FILE DOES NOT EXIST";
        }


        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            URL url = new URL(ServerUploaderFile);
            HttpURLConnection Connection = (HttpURLConnection) url.openConnection();

            String FiveAsterisks = "*****";

            if (Connection == null) {
                Log.d("ABC", "1");
            }

            Connection.setUseCaches(false);

            Connection.setDoInput(true);
            Connection.setDoOutput(true);

            Connection.setRequestProperty("ENCTYPE", "multipart/form-data");
            Connection.setRequestProperty("Connection", "Keep-Alive");
            Connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + FiveAsterisks);
            Connection.setRequestProperty("uploaded_file", FileName);

            Connection.setRequestMethod("POST");

            OutputStream outputStream = Connection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeBytes("--*****\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""+ FileName + "\"\r\n");
            dataOutputStream.writeBytes("\r\n");

            BytesAvailable = fileInputStream.available();

            int BufferSize = MaxBufferSize;

            if (BytesAvailable < MaxBufferSize) {
                BufferSize = BytesAvailable;
            }
            else {
                // NOTHING
            }

            byte[] BufferByteArray = new byte[BufferSize];

            int UploadedProgress = 0;

            while (true) {
                int BytesRead = fileInputStream.read(BufferByteArray, 0, BufferSize);
                dataOutputStream.write(BufferByteArray, 0, BufferSize);

                BytesAvailable = fileInputStream.available();
                if (BytesAvailable < MaxBufferSize) {
                    BufferSize = BytesAvailable;
                }
                else {
                    BufferSize = MaxBufferSize;
                }


                UploadedProgress = UploadedProgress + BytesRead;
                publishProgress(UploadedProgress);

                if (BytesRead > 0) {
                    continue;
                }
                else {
                    break;
                }

            }

            fileInputStream.close();

            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.writeBytes("--*****--\r\n");
            dataOutputStream.flush();
            dataOutputStream.close();

            int ResponseCode = Connection.getResponseCode();

            if(ResponseCode == 200){
                return Uploaded;
            }

        }

        catch(Exception e) {
            Log.d("ABC", "40");
            Log.d("ABC", e.toString());
            e.printStackTrace();
            return Failed;
        }

        return Failed;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
    }
}

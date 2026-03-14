package com.example.androidlabs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ImageView catImageView;
    ProgressBar progressBar;
    CatImages catTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catImageView = findViewById(R.id.catImageView);
        progressBar = findViewById(R.id.progressBar);

        catTask = new CatImages();
        catTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (catTask != null) {
            catTask.cancel(true);
        }
    }

    class CatImages extends AsyncTask<String, Integer, String> {

        Bitmap currentBitmap;
        boolean hasNewImage = false;

        @Override
        protected String doInBackground(String... strings) {
            while (!isCancelled()) {
                try {
                    URL imageUrl = new URL("https://cataas.com/cat");
                    HttpURLConnection imageConnection = (HttpURLConnection) imageUrl.openConnection();
                    imageConnection.setRequestMethod("GET");
                    imageConnection.connect();

                    InputStream imageStream = imageConnection.getInputStream();
                    currentBitmap = BitmapFactory.decodeStream(imageStream);

                    imageStream.close();
                    imageConnection.disconnect();

                    publishProgress(0);

                    for (int i = 0; i < 100; i++) {
                        publishProgress(i);
                        Thread.sleep(30);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "done";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);

            if (currentBitmap != null) {
                catImageView.setImageBitmap(currentBitmap);
            }
        }

    }
}
package com.example.androidlabs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Bundle data = getIntent().getExtras();

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(data);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailsFrame, fragment)
                .commit();
    }
}

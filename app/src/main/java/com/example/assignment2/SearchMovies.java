package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        getSupportActionBar().setTitle("");
    }
}
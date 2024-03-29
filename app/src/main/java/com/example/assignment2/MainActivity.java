package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Button addMoviesBtn = findViewById(R.id.main_button_add);
        addMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovies.class);
                startActivity(intent);
            }
        });

        Button searchForMoviesBtn = findViewById(R.id.main_button_search_movies);
        searchForMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, SearchMovies.class);
                startActivity(intent);
            }
        });

        Button searchForActorsBtn = findViewById(R.id.main_button_search_actors);
        searchForActorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActors.class);
                startActivity(intent);
            }
        });

        Button advancedBtn = findViewById(R.id.main_button_advanced);
        advancedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Advanced.class);
                startActivity(intent);
            }
        });
    }
}
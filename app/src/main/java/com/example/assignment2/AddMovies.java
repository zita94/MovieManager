package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class AddMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movies);

        getSupportActionBar().setTitle("");

        EditText title = findViewById(R.id.add_et_title);
        EditText year = findViewById(R.id.add_et_year);
        EditText rated = findViewById(R.id.add_et_rated);
        EditText released = findViewById(R.id.add_et_released);
        EditText runtime = findViewById(R.id.add_et_runtime);
        EditText genre = findViewById(R.id.add_et_genre);
        EditText director = findViewById(R.id.add_et_director);
        EditText writer = findViewById(R.id.add_et_writer);
        EditText actors = findViewById(R.id.add_et_actors);
        EditText plot = findViewById(R.id.add_et_plot);
    }
}
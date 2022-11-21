package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SearchActors extends AppCompatActivity {
    MovieDatabase movieDatabase;
    MovieDao movieDao;
    EditText name_et;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_actors);

        getSupportActionBar().setTitle("");

        movieDatabase = MovieDatabase.getMovieDatabase(getApplicationContext());
        movieDao = movieDatabase.movieDao();

        name_et = findViewById(R.id.searchactors_actors_et);
        results = findViewById(R.id.searchactors_results);
    }

    public void searchOnClick(View view) {
        String name = name_et.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Movie> movies = movieDao.getMoviesByActorName(name);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String result = "";
                        for (Movie movie : movies) {
                            String row = movie.getTitle() + " - " + movie.getActors() + "\n";
                            result += row;
                        }
                        results.setText(result);
                        Toast.makeText(SearchActors.this, "count: " + movies.size(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();

    }
}
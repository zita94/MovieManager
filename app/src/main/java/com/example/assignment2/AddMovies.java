package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddMovies extends AppCompatActivity {
    MovieDatabase movieDatabase;
    MovieDao movieDao;

    EditText title;
    EditText year;
    EditText rated;
    EditText released;
    EditText runtime;
    EditText genre;
    EditText director; 
    EditText writer;
    EditText actors;
    EditText plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movies);

        getSupportActionBar().setTitle("");

        movieDatabase = MovieDatabase.getMovieDatabase(getApplicationContext());
        movieDao = movieDatabase.movieDao();

        title = findViewById(R.id.add_et_title);
        year = findViewById(R.id.add_et_year);
        rated = findViewById(R.id.add_et_rated);
        released = findViewById(R.id.add_et_released);
        runtime = findViewById(R.id.add_et_runtime);
        genre = findViewById(R.id.add_et_genre);
        director = findViewById(R.id.add_et_director);
        writer = findViewById(R.id.add_et_writer);
        actors = findViewById(R.id.add_et_actors);
        plot = findViewById(R.id.add_et_plot);
    }

    public void saveOnClick(View view) {
        // validation of form
        
        Movie movie = new Movie();
        movie.setTitle(title.getText().toString());

        int intYear = Integer.parseInt(year.getText().toString());
        movie.setYear(intYear);

        movie.setRated(rated.getText().toString());
        movie.setReleased(released.getText().toString());
        movie.setRuntime(runtime.getText().toString());
        movie.setGenre(genre.getText().toString());
        movie.setDirector(director.getText().toString());
        movie.setWriter(writer.getText().toString());
        movie.setActors(actors.getText().toString());
        movie.setPlot(plot.getText().toString());
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                
                movieDao.addMovie(movie);
                
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddMovies.this, "Movie added", Toast.LENGTH_SHORT).show();
                        TextView tv = findViewById(R.id.textView);
                        //List<Movie> movieList = movieDao.getAllMovies();
                        //String movies = movieList.toString();
                        //tv.setText(movies);
                    }
                });
            }
        }).start();
    }
}
package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovies extends AppCompatActivity {
    MovieDatabase movieDatabase;
    MovieDao movieDao;

    EditText title_et;
    EditText year_et;
    EditText rated_et;
    EditText released_et;
    EditText runtime_et;
    EditText genre_et;
    EditText director_et;
    EditText writer_et;
    EditText actors_et;
    EditText plot_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movies);

        getSupportActionBar().setTitle("");

        movieDatabase = MovieDatabase.getMovieDatabase(getApplicationContext());
        movieDao = movieDatabase.movieDao();

        title_et = findViewById(R.id.add_et_title);
        year_et = findViewById(R.id.add_et_year);
        rated_et = findViewById(R.id.add_et_rated);
        released_et = findViewById(R.id.add_et_released);
        runtime_et = findViewById(R.id.add_et_runtime);
        genre_et = findViewById(R.id.add_et_genre);
        director_et = findViewById(R.id.add_et_director);
        writer_et = findViewById(R.id.add_et_writer);
        actors_et = findViewById(R.id.add_et_actors);
        plot_et = findViewById(R.id.add_et_plot);
    }

    public void saveOnClick(View view) {
        String title = title_et.getText().toString();
        String year = year_et.getText().toString();
        String rated = rated_et.getText().toString();
        String released = released_et.getText().toString();
        String runtime = runtime_et.getText().toString();
        String genre = genre_et.getText().toString();
        String director = director_et.getText().toString();
        String writer = writer_et.getText().toString();
        String actors = actors_et.getText().toString();
        String plot = plot_et.getText().toString();

        // form validation
        if (TextUtils.isEmpty(title)) {
            title_et.setError("Title cannot be empty");
            title_et.requestFocus();
            return;
        }

        int intYear = -1;
        try {
            if (TextUtils.isEmpty(year.trim())) {
                year_et.setError("Year cannot be empty");
                year_et.requestFocus();
                return;
            }
            intYear = Integer.parseInt(year);
        } catch (Exception e) {
            year_et.setError("Year must be an integer");
            year_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(rated)) {
            rated_et.setError("Rated cannot be empty");
            rated_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(released)) {
            released_et.setError("Released cannot be empty");
            released_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(runtime)) {
            runtime_et.setError("Runtime cannot be empty");
            runtime_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(genre)) {
            genre_et.setError("Genre cannot be empty");
            genre_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(director)) {
            director_et.setError("Director cannot be empty");
            director_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(writer)) {
            writer_et.setError("Writer cannot be empty");
            writer_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(actors)) {
            actors_et.setError("Actors cannot be empty");
            actors_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(plot)) {
            plot_et.setError("Plot cannot be empty");
            plot_et.requestFocus();
            return;
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(intYear);
        movie.setRated(rated);
        movie.setReleased(released);
        movie.setRuntime(runtime);
        movie.setGenre(genre);
        movie.setDirector(director);
        movie.setWriter(writer);
        movie.setActors(actors);
        movie.setPlot(plot);

        new Thread(new Runnable() {
            @Override
            public void run() {

                movieDao.addMovie(movie);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddMovies.this, movie.title + " has been saved", Toast.LENGTH_LONG).show();
                        resetForm();
                    }
                });
            }
        }).start();
    }

    private void resetForm() {
        title_et.setText("");
        year_et.setText("");
        rated_et.setText("");
        released_et.setText("");
        runtime_et.setText("");
        genre_et.setText("");
        director_et.setText("");
        writer_et.setText("");
        actors_et.setText("");
        plot_et.setText("");
    }
}
package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchMovies extends AppCompatActivity {
    EditText movieTitleEt;
    TextView movieDetails;
    String url;
    MovieDatabase movieDatabase;
    MovieDao movieDao;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        getSupportActionBar().setTitle("");

        movieDetails = findViewById(R.id.searchmovies_movieDetails);

        movieDatabase = MovieDatabase.getMovieDatabase(getApplicationContext());
        movieDao = movieDatabase.movieDao();
        movie = new Movie();
    }

    public void retrieveMovieOnClick(View view) {
        movieTitleEt = findViewById(R.id.searchmovie_et_movieName);
        String movieTitle = movieTitleEt.getText().toString();
        url = "https://www.omdbapi.com/?t=" + movieTitle + "&apikey=" + getResources().getString(R.string.API_key);
        getMovieData(url);
    }

    public void saveMovieOnClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                movieDao.addMovie(movie);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SearchMovies.this, movie.title +
                                " has been saved", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }

    private void getMovieData(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String title = response.getString("Title");
                    String year = response.getString("Year");
                    String rated = response.getString("Rated");
                    String released = response.getString("Released");
                    String runtime = response.getString("Runtime");
                    String genre = response.getString("Genre");
                    String director = response.getString("Director");
                    String writer = response.getString("Writer");
                    String actors = response.getString("Actors");
                    String plot = response.getString("Plot");

                    String output = "Title: " + title + "\nYear: " + year + "\nRated: " + rated +
                            "\nReleased: " + released + "\nRuntime: " + runtime + "\nGenre: " +
                            genre + "\nDirector: " + director + "\nWriter: " + writer +
                            "\nActors: " + actors + "\nPlot: " + plot;

                    movieDetails.setText(output);

                    System.out.println("Title= " + title);
                    System.out.println(response);

                    movie.setTitle(title);

                    int intYear = 0;
                    try {
                        intYear = Integer.parseInt(year);
                    } catch (Exception e) {
                        System.out.println("Unable to parse year");
                    }

                    movie.setYear(intYear);
                    movie.setRated(rated);
                    movie.setReleased(released);
                    movie.setRuntime(runtime);
                    movie.setGenre(genre);
                    movie.setDirector(director);
                    movie.setWriter(writer);
                    movie.setActors(actors);
                    movie.setPlot(plot);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error Response", error.getMessage(), error);
            }
        });
        queue.add(jsonObjectRequest);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("details", movieDetails.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("details")) {
            movieDetails.setText(savedInstanceState.getString("details"));
        }
    }
}
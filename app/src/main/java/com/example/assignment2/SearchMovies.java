package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        getSupportActionBar().setTitle("");

        movieDetails = findViewById(R.id.searchmovies_movieDetails);
    }

    public void retrieveMovieOnClick(View view) {
        movieTitleEt = findViewById(R.id.searchmovie_et_movieName);
        String movieTitle = movieTitleEt.getText().toString();
        url = "https://www.omdbapi.com/?t=" + movieTitle + "&apikey=" + getResources().getString(R.string.API_key);
        getMovieData(url);
    }

    public void saveMovieOnClick(View view) {

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
                            "\nReleased: " + released + "\nRuntime: " + runtime + "\nGenre" +
                            genre + "\nDirector: " + director + "\nWriter: " + writer +
                            "\nActors: " + actors + "\nPlot: " + plot;

                    movieDetails.setText(output);

                    System.out.println("Title= " + title);
                    System.out.println(response);
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
}
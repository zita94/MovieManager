package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Advanced extends AppCompatActivity implements iMovieTitleOnClickListener {
    EditText titleEt;
    RecyclerView recyclerView;
    CellAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        getSupportActionBar().setTitle("");

        titleEt = findViewById(R.id.advanced_enterTitle);
        recyclerView = findViewById(R.id.advanced_recycler);
    }

    public void searchOnClick(View view) {
        String movieTitle = titleEt.getText().toString();

        if (TextUtils.isEmpty(movieTitle)) {
            Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://www.omdbapi.com/?s=*" + movieTitle + "*&apikey=" + getResources().getString(R.string.API_key);
        getMovieData(url);
    }

    private void getMovieData(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("Search");
                    List<String> titles = new ArrayList<>();
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movie = results.getJSONObject(i);
                        String title = movie.getString("Title");
                        System.out.println("Title: " + title);
                        titles.add(title);
                    }

                    String total = response.getString("totalResults");
                    System.out.println("Total " + total);

                    setTitlesInRecycler(titles);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (e.getMessage().equals("No value for Search")) {
                        Toast.makeText(Advanced.this, "Zero results match your current search",
                                Toast.LENGTH_SHORT).show();

                        adapter.emptyList();
                    }
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

    private void setTitlesInRecycler(List<String> titles) {
        adapter = new CellAdapter(this, titles, this::onTitleClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onTitleClick() {
        // on movie title click logic for getting movie poster
    }
}
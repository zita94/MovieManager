package com.example.assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Advanced extends AppCompatActivity implements iMovieTitleOnClickListener {
    EditText titleEt;
    RecyclerView recyclerView;
    CellAdapter adapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        getSupportActionBar().setTitle("");

        titleEt = findViewById(R.id.advanced_enterTitle);
        recyclerView = findViewById(R.id.advanced_recycler);
        imageView = findViewById(R.id.advanced_image);
    }

    public void searchOnClick(View view) {
        String movieTitle = titleEt.getText().toString().trim();

        if (TextUtils.isEmpty(movieTitle)) {
            Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = getString(R.string.base_url) + "?s=*" + movieTitle + "*&apikey=" + getResources().getString(R.string.API_key);
        getMovieData(url);
    }

    private void getMovieData(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, response -> {
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
                }, error -> Log.d("Volley Error Response", error.getMessage(), error));
        queue.add(jsonObjectRequest);
    }

    private void setTitlesInRecycler(List<String> titles) {
        adapter = new CellAdapter(this, titles, this::onTitleClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (recyclerView.getVisibility() == View.INVISIBLE) {
            imageView.setImageDrawable(null);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onTitleClick(String title) {
        // on movie title click logic for getting movie poster
        String url = getString(R.string.base_url) + "?t=" + title + "&apikey=" + getResources().getString(R.string.API_key);
        getMoviePoster(url);
    }

    private void getMoviePoster(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, response -> {
                    try {
                        String posterUrl = response.getString("Poster");
                        System.out.println("Poster: " + posterUrl);

                        // opening URL connection on worker thread to not hang the UI
                        Thread decodeImage = new Thread(() -> {
                            try {
                                URL url1 = new URL(posterUrl);
                                Bitmap imageBitmap = BitmapFactory.decodeStream(url1.openConnection()
                                        .getInputStream());

                                recyclerView.setVisibility(View.INVISIBLE);
                                runOnUiThread(() -> imageView.setImageBitmap(imageBitmap));
                            } catch(IOException e) {
                                e.printStackTrace();
                            }
                        });
                        decodeImage.start();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.d("Volley Error Response", error.getMessage(), error));

        queue.add(jsonObjectRequest);
    }

    public void imageViewOnClick(View view) {
        // hide image view when clicked
        imageView.setImageDrawable(null);

        // show recycler when image is hidden
        recyclerView.setVisibility(View.VISIBLE);
    }
}
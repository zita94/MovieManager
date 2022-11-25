package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CellAdapter extends RecyclerView.Adapter<CellHolder> {
    private Context context;
    List<String> list;

    public CellAdapter(Context context, List<String> titles){
        this.context = context;
        this.list = titles;
    }

    @NonNull
    @Override
    public CellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false);
        return new CellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellHolder holder, int position) {
        holder.title.setText(list.get(position));

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // perform API call to get movie image
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

package com.example.assignment2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CellHolder extends RecyclerView.ViewHolder {
    TextView title;

    public CellHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.cell_title);
    }
}

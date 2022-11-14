package com.example.assignment2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert void addMovie(Movie movie);
}

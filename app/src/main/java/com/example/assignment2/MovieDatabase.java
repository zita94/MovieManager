package com.example.assignment2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static final String dbName = "Movie";
    private static MovieDatabase movieDatabase;

    public static synchronized MovieDatabase getMovieDatabase(Context context) {
        if (movieDatabase == null) {
            movieDatabase = Room.databaseBuilder(context, MovieDatabase.class, dbName)
                    .fallbackToDestructiveMigration().build();
        }

        return movieDatabase;
    }

    public abstract MovieDao movieDao();
}

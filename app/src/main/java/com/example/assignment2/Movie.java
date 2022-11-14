package com.example.assignment2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movie")
public class Movie {
    @PrimaryKey(autoGenerate = true) Integer movieID;
    @ColumnInfo(name = "title") String title;
    @ColumnInfo(name = "year") Integer year;
    @ColumnInfo(name = "rated") String rated;
    @ColumnInfo(name = "released") String released;
    @ColumnInfo(name = "runtime") String runtime;
    @ColumnInfo(name = "genre") String genre;
    @ColumnInfo(name = "director") String director;
    @ColumnInfo(name = "writer") String writer;
    @ColumnInfo(name = "actors") String actors;
    @ColumnInfo(name = "plot") String plot;

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}

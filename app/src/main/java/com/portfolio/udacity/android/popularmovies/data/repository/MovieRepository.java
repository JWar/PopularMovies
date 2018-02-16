package com.portfolio.udacity.android.popularmovies.data.repository;

import android.support.annotation.NonNull;

import com.portfolio.udacity.android.popularmovies.data.model.Movie;

import java.util.List;

/**
 * Created by JonGaming on 16/02/2018.
 * For ensuring Main and Detail Activity can access same data set.
 */

public class MovieRepository {
    private static MovieRepository sInstance;
    private List<Movie> mMovies;

    public static MovieRepository getInstance() {
        if (sInstance==null) {
            sInstance = new MovieRepository();
        }
        return sInstance;
    }
    private MovieRepository() {}
    public void setMovies(List<Movie> aMovies) {
        mMovies = aMovies;
    }
    public List<Movie> getMovies() {
        return mMovies;
    }
    public void destroyInstance() {
        sInstance=null;
    }
}

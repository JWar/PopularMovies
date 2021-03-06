package com.portfolio.udacity.android.popularmovies.data.model;

/**
 * Created by JonGaming on 16/02/2018.
 * Quick and dirty object to represent Movie.
 * Most fields are in string format, due to ease of use.
 * Avoiding getters/setters for time sake
 */

public class Movie {

    //JSON keys
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String RELEASE_DATE = "release_date";
    public static final String POSTER_PATH = "poster_path";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String OVERVIEW = "overview";

    public int mId;
    public String mTitle;
    public String mReleaseDate;
    public String mPoster;
    public String mVoteAverage;
    public String mSynopsis;

    public Movie() {}

    public Movie(int aId,
                 String aTitle,
                 String aReleaseDate,
                 String aPoster,
                 String aVoteAverage,
                 String aSynopsis) {
        mId = aId;
        mTitle = aTitle;
        mReleaseDate = aReleaseDate;
        mPoster = aPoster;
        mVoteAverage = aVoteAverage;
        mSynopsis = aSynopsis;
    }
}

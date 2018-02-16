package com.portfolio.udacity.android.popularmovies;

import android.net.Uri;
import android.util.Log;

import com.portfolio.udacity.android.popularmovies.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by JonGaming on 16/02/2018.
 * Handles network stuff...
 */

public class NetworkUtils {
    private static final String TAG = "PopularMovies";
    private static final String DATA_URL = "http://api.themoviedb.org/3/movie/";
    private static final String API_KEY_QUERY = "api_key";

    public static final String TOP_RATED = "top_rated?";
    public static final String POPULAR = "popular?";

    //Default search by particular size of w185. Of course can put this as an append if needs be...
    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    //TODO: Insert your key here. Also dont forget to avoid including this in a commit...
    public static final String API_KEY = "";

    public static synchronized List<Movie> getMoviesOrderBy(String aOrderBy) {
        try {
            ArrayList<Movie> toReturn = new ArrayList<>();
            String result = getResponseFromHttpUrl(buildUrlWithSortOrder(aOrderBy));
            JSONArray results = new JSONObject(result).getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject movieObj = results.getJSONObject(i);
                Movie movie = new Movie(
                        movieObj.getInt("id"),
                        movieObj.getString("title"),
                        movieObj.getString("release_date"),
                        movieObj.getString("poster_path"),
                        movieObj.getString("vote_average"),
                        movieObj.getString("overview")
                );
                toReturn.add(movie);
            }
            return toReturn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static URL buildUrlWithSortOrder(String aOrderBy) {
        String urlToParse = DATA_URL + aOrderBy;
        Uri uri = Uri.parse(urlToParse).buildUpon()
                .appendQueryParameter(API_KEY_QUERY, API_KEY)
                .build();
        try {
            URL url = new URL(uri.toString());
            Log.i(TAG, "URL: " + url);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            Log.i(TAG, "HttpUrl response: " + response);
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }
}

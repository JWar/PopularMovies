package com.portfolio.udacity.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.portfolio.udacity.android.popularmovies.data.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by JonGaming on 16/02/2018.
 *
 */

public class GridAdapter extends ArrayAdapter<Movie> {
    private static final String LOG_TAG = GridAdapter.class.getSimpleName();
    public GridAdapter(Context aContext, List<Movie> aMovies) {
        super(aContext,0,aMovies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movies_item, parent, false
            );
        }
        ImageView imageView = convertView.findViewById(R.id.movies_item_poster_iv);
        String url = "http://image.tmdb.org/t/p/w185/";
        if (movie!=null) {
            url += movie.mPoster;
            Picasso.with(getContext()).load(url).into(imageView);
            imageView.setTag(position);
            //Hmm should onclick be set here?
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View aView) {
                    //Start DetailActivity.... using position of movie selected. This will require repository
                    DetailActivity.start(getContext(),(int)aView.getTag());
                }
            });
        }
        return convertView;
    }

}

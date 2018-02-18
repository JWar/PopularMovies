package com.portfolio.udacity.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.portfolio.udacity.android.popularmovies.data.model.Movie;
import com.portfolio.udacity.android.popularmovies.data.repository.MovieRepository;
import com.squareup.picasso.Picasso;

/**
 * For the moment I am simply getting the data from the MovieRepo and using the
 * arguments position to get the correct movie in the list. Naturally this isnt how you'd properly design
 * it. But that is stage two!
 */
public class DetailActivity extends AppCompatActivity {

    private static final String POS = "pos";
    private int mPosition;

    public static void start(Context aContext,int aPosition) {
        Intent intent = new Intent(aContext,DetailActivity.class);
        intent.putExtra(POS,aPosition);
        aContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState!=null) {
            mPosition = savedInstanceState.getInt(POS,-1);
        } else {
            if (getIntent().hasExtra(POS)) {
                mPosition = getIntent().getIntExtra(POS,-1);
            }
        }
        setViews();
    }
    private void setViews() {
        MovieRepository movieRepository = MovieRepository.getInstance();
        if (movieRepository.getMovies()!=null) {
            Movie movie = movieRepository.getMovies().get(mPosition);
            TextView title = findViewById(R.id.activity_detail_title_tv);
            title.setText(movie.mTitle);
            TextView releaseDate = findViewById(R.id.activity_detail_release_date_tv);
            releaseDate.setText(movie.mReleaseDate);
            TextView voteAverage = findViewById(R.id.activity_detail_vote_average_tv);
            voteAverage.setText(movie.mVoteAverage);
            TextView synopsis = findViewById(R.id.activity_detail_synopsis_tv);
            synopsis.setText(movie.mSynopsis);
            ImageView poster = findViewById(R.id.activity_detail_poster_iv);
            //Load image last.
            String url = NetworkUtils.IMAGE_URL;
            url += movie.mPoster;
            Picasso.with(this)
                    .load(url)
                    .placeholder(R.drawable.ic_image_black_48px)
                    .error(R.drawable.ic_error_black_48px)
                    .resize(getResources().getDimensionPixelSize(R.dimen.movie_detail_poster_size),
                            getResources().getDimensionPixelSize(R.dimen.movie_detail_poster_size))
                    .into(poster);
        } else {
            Toast.makeText(this, "Problem finding Movie data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POS,mPosition);
    }
}

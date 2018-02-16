package com.portfolio.udacity.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.portfolio.udacity.android.popularmovies.data.repository.MovieRepository;

public class MainActivity extends AppCompatActivity {

    private MovieRepository mMovieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Async task to get movies???
        mMovieRepository = MovieRepository.getInstance();
        GridAdapter gridAdapter = new GridAdapter(this,mMovieRepository.getMovies());
        GridView gridView = findViewById(R.id.main_activity_gv);
        gridView.setAdapter(gridAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order_by_most_popular:
                //Make new call to search by popular
                return true;
            case R.id.action_order_by_highest_rated:
                //Make new call to search by rated
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMovieRepository!=null) {
            mMovieRepository.destroyInstance();
        }
    }


}

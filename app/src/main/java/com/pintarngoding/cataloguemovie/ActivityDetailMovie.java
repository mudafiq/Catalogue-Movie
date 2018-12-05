package com.pintarngoding.cataloguemovie;

import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ActivityDetailMovie extends AppCompatActivity {

    ImageView imageViewBackdrop;
    TextView textViewJudul, textViewRating, textViewReleaseDate, textViewOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        imageViewBackdrop = findViewById(R.id.imageViewBackdrop);
        textViewJudul = findViewById(R.id.textViewJudulDetail);
        textViewOverview = findViewById(R.id.textViewOverviewDetail);
        textViewRating = findViewById(R.id.textViewRatingDetail);
        textViewReleaseDate = findViewById(R.id.textViewReleaseDateDetail);

        Movie movie = getIntent().getParcelableExtra(MainActivity.EXTRA_DETAIL_MOVIE);
        textViewJudul.setText(movie.getTitle());
        Glide.with(this)
                .load(movie.getBackdrop())
                .into(imageViewBackdrop);
        textViewReleaseDate.setText(movie.getRelease_date());
        textViewRating.setText(movie.getRating());
        textViewOverview.setText(movie.getOverview());
    }
}

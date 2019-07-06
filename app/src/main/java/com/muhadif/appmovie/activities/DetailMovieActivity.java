package com.muhadif.appmovie.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.muhadif.appmovie.R;
import com.muhadif.appmovie.data.models.DetailMovie;
import com.muhadif.appmovie.data.models.Genre;
import com.muhadif.appmovie.data.models.Movie;
import com.muhadif.appmovie.presenters.DetailMoviePresenter;
import com.muhadif.appmovie.views.DetailView;

public class DetailMovieActivity extends AppCompatActivity implements DetailView {

    public static final String EXTRA_MOVIE = "extra_movie";

    CircularImageView poster;
    TextView title, tagline, rating, language, runtime, overview, genre, release;
    DetailMoviePresenter presenter;
    SwipeRefreshLayout refreshLayout;
    LinearLayout contentDetailMovie;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        poster = findViewById(R.id.image_detail_movie);
        title = findViewById(R.id.tv_detail_title_movie);
        tagline = findViewById(R.id.tv_detail_tagline_movie);
        rating = findViewById(R.id.tv_rating);
        language = findViewById(R.id.tv_language);
        runtime = findViewById(R.id.tv_runtime);
        overview = findViewById(R.id.tv_movie_overview_detail);
        genre = findViewById(R.id.tv_genre);
        release = findViewById(R.id.tv_release);
        refreshLayout = findViewById(R.id.swipe_refresh_layout_detail);
        contentDetailMovie = findViewById(R.id.content_detail_movie);

        presenter = new DetailMoviePresenter(this);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        presenter.getData(movie.getId());

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData(movie.getId());
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void showLoad() {
        refreshLayout.setRefreshing(true);
        contentDetailMovie.setVisibility(View.GONE);
        refreshLayout.setRefreshing(true);
        this.setTitle(movie.getTitle());
        title.setText(movie.getTitle());
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w780" + movie.getPosterPath())
                .into(poster);

    }

    @Override
    public void finishLoad() {
        refreshLayout.setRefreshing(false);
        contentDetailMovie.setVisibility(View.VISIBLE);

    }

    @Override
    public void showData(DetailMovie detailMovie) {
        tagline.setText(detailMovie.getTagline());
        rating.setText(detailMovie.getVote_average() + "");
        language.setText(detailMovie.getOriginal_language());
        runtime.setText(detailMovie.getRuntime() + "");
        overview.setText(detailMovie.getOverview());
        release.setText(detailMovie.getRelease_date());
        genre.setText("");
        for (Genre d : detailMovie.getGenres()) {
            genre.setText(genre.getText() + d.getName() + " ");
        }

    }
}

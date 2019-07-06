package com.muhadif.appmovie.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muhadif.appmovie.R;
import com.muhadif.appmovie.activities.DetailMovieActivity;
import com.muhadif.appmovie.data.models.Movie;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    List<Movie> results;

    public MainAdapter(Context context, List<Movie> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.movies_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.Binding(results.get(position), context);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView overview;
        ImageView poster;
        View itemView;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.tv_movie_title);
            poster = view.findViewById(R.id.img_poster);
            overview = view.findViewById(R.id.tv_movie_overview);
            itemView = view;
        }

        void Binding(final Movie movie, final Context context) {
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());

            Log.d("Poster Path", movie.getPosterPath());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w92" + movie.getPosterPath())
                    .into(poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent moveDetailMovieActivity = new Intent(context, DetailMovieActivity.class);
                    moveDetailMovieActivity.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
                    moveDetailMovieActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(moveDetailMovieActivity);
                }
            });

        }
    }
}

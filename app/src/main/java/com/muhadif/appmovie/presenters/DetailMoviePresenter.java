package com.muhadif.appmovie.presenters;

import android.util.Log;

import com.muhadif.appmovie.data.models.DetailMovie;
import com.muhadif.appmovie.data.repositories.RetroClient;
import com.muhadif.appmovie.views.DetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMoviePresenter {

    DetailView view;

    public DetailMoviePresenter(DetailView view) {
        this.view = view;
    }

    public void getData(int id) {
        view.showLoad();
        Call<DetailMovie> detailMovieCall = RetroClient.getService().getDetailMovie(id);

        detailMovieCall.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                Log.d("Detail Movie", response.body().getOverview());
                view.showData(response.body());
                view.finishLoad();
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Log.d("Detail Movie", t.toString());
            }
        });

    }


}

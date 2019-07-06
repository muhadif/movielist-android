package com.muhadif.appmovie.presenters;

import android.util.Log;

import com.muhadif.appmovie.data.models.ResultModel;
import com.muhadif.appmovie.data.repositories.RetroClient;
import com.muhadif.appmovie.views.MainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getData() {
        view.showLoad();
        Call<ResultModel> listCall = RetroClient.getService().getNowPlaying();

        listCall.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                Log.d("MovieList", response.body().getResult().size() + "");
                view.finishLoad();
                view.showData(response.body().getResult());
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Log.d("MovMovieListie", t.getMessage());
            }
        });


    }

    public void searchMovie(String src) {
        view.showLoad();
        Call<ResultModel> listCall = RetroClient.getService().getSearchMovie(src);

        listCall.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                Log.d("SearchList", response.body().getResult().size() + "");
                view.finishLoad();
                view.showData(response.body().getResult());

            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Log.d("SearchList", t.getMessage().toString());
            }
        });
    }
}

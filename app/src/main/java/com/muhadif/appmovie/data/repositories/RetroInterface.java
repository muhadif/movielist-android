package com.muhadif.appmovie.data.repositories;

import com.muhadif.appmovie.data.models.DetailMovie;
import com.muhadif.appmovie.data.models.ResultModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("3/movie/now_playing?api_key=1ad5cc49dbf80491b5f6265e9df1b5e6&page=1")
    public Call<ResultModel> getNowPlaying();

    @GET("3/search/movie?api_key=1ad5cc49dbf80491b5f6265e9df1b5e6&language=en-US")
    public Call<ResultModel> getSearchMovie(@Query("query") String src);

    @GET("3/movie/{id}?api_key=1ad5cc49dbf80491b5f6265e9df1b5e6&language=en-US")
    public Call<DetailMovie> getDetailMovie(@Path("id") int id);
}

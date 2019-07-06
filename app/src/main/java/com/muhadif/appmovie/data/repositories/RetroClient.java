package com.muhadif.appmovie.data.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String BASE_URL = "https://api.themoviedb.org/";

    public static RetroInterface getService() {
        return getRetrofit().create(RetroInterface.class);
    }

    public static Retrofit.Builder builder() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static Retrofit getRetrofit() {
        return builder().build();
    }

}

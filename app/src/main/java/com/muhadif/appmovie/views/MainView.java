package com.muhadif.appmovie.views;

import com.muhadif.appmovie.data.models.Movie;

import java.util.List;

public interface MainView {

    void showLoad();

    void finishLoad();

    void showData(List<Movie> data);
}

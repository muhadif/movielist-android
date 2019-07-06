package com.muhadif.appmovie.views;

import com.muhadif.appmovie.data.models.DetailMovie;


public interface DetailView {

    void showLoad();

    void finishLoad();

    void showData(DetailMovie detailMovie);
}

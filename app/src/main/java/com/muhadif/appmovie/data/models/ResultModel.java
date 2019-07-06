package com.muhadif.appmovie.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModel {
    @SerializedName("results")
   private List<Movie> result;

    public List<Movie> getResult() {
        return result;
    }

    public void setResult(List<Movie> result) {
        this.result = result;
    }
}

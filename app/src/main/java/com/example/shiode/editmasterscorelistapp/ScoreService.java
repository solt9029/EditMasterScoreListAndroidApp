package com.example.shiode.editmasterscorelistapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface ScoreService {
    String BASE_URL = "http://editmasterapi.solt9029.com";

    @GET("/scores")
    Call<ScoreList> getScoreList();

    @GET("scores/{id}")
    Call<Score> getScore(@Path("id") Integer id);

}